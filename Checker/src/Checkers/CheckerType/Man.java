package Checkers.CheckerType;


import Checkers.Board.*;

import java.util.ArrayList;
import java.util.List;

import static Checkers.CheckerType.PieceColor.BLACK;
import static Checkers.CheckerType.PieceColor.RED;

//Man is the name for normal pieces in checker
public class Man extends Checkers.CheckerType.CheckerType {

    public Man(int xCoordinate, int yCoordinate, PieceColor pieceColor) {
        super(Checkers.CheckerType.CheckerSymbol.MAN,
                xCoordinate,
                yCoordinate,
                pieceColor);
    }

    @Override
    public final List<Checkers.Board.Move> getLegalMoves(Checkers.Board.CheckerBoard board) {
        List<Checkers.Board.Move> legalMoves = new ArrayList<Checkers.Board.Move>();

        for (int j = -1; j <= 1; j += 2) {
            final int possibleXCoordinate = checkerXCoordinate + j;
            final int possibleYCoordinate = checkerYCoordinate + (this.getCheckerColor().getDirection() * 1);
            if ((possibleXCoordinate < 0 || possibleXCoordinate > 7) || (possibleYCoordinate < 0 || possibleYCoordinate > 7)) {
                continue;
            }
            final Checkers.Board.Square possibleMove = Checkers.Board.CheckerBoard.getSquare(possibleXCoordinate, possibleYCoordinate);
            if (!possibleMove.isSquareOccupied()) {
                if ((this.getCheckerColor() == RED && possibleYCoordinate == 7) ||
                        (this.getCheckerColor() == BLACK && possibleYCoordinate == 0)) {
                    legalMoves.add(new Checkers.Board.PromotionMove(new Checkers.Board.NormalMove(board, this, possibleXCoordinate, possibleYCoordinate)));
                } else {
                    legalMoves.add(new Checkers.Board.NormalMove(board, this, possibleXCoordinate, possibleYCoordinate));

                }


            } else {
                if (possibleMove.getCheckerType().getCheckerColor() != this.getCheckerColor()) {
                    if (j == -1 && ((possibleXCoordinate - 1) >= 0
                            && ((possibleYCoordinate + 1) <= 7 && (possibleYCoordinate - 1) >= 0))) {
                        final Checkers.Board.Square possibleJump = Checkers.Board.CheckerBoard.getSquare(possibleXCoordinate + j,
                                possibleYCoordinate + (1 * this.getCheckerColor().getDirection()));
                        if (((this.getCheckerColor() == RED && possibleYCoordinate + 1 == 7) ||
                                (this.getCheckerColor() == BLACK && possibleYCoordinate - 1== 0)) && (!possibleJump.isSquareOccupied())) {
                            final Checkers.CheckerType.CheckerType attackedChecker = board.getChecker(possibleXCoordinate, possibleYCoordinate);
                            legalMoves.add(new Checkers.Board.PromotionJumpMove(new Checkers.Board.AttackingMove(board, this, possibleXCoordinate + j, possibleYCoordinate + (this.getCheckerColor().getDirection() * 1), attackedChecker)));

                        } else if (!possibleJump.isSquareOccupied()) {
                            final Checkers.CheckerType.CheckerType attackedChecker = board.getChecker(possibleXCoordinate, possibleYCoordinate);
                            legalMoves.add(new Checkers.Board.AttackingMove(board, this, possibleXCoordinate + j, possibleYCoordinate + (this.getCheckerColor().getDirection() * 1), attackedChecker));
                        }
                    }
                    if (j == 1 && ((possibleXCoordinate + 1) <= 7 && (possibleXCoordinate - 1) >= 0)
                            && ((possibleYCoordinate + 1) <= 7 && (possibleYCoordinate - 1) >= 0)) {
                        final Checkers.Board.Square possibleJump2 = Checkers.Board.CheckerBoard.getSquare((possibleXCoordinate + j),
                                possibleYCoordinate + (this.getCheckerColor().getDirection() * 1));
                        if (((this.getCheckerColor() == RED && possibleYCoordinate + 1 == 7) ||
                                (this.getCheckerColor() == BLACK && possibleYCoordinate - 1== 0)) && (!possibleJump2.isSquareOccupied())) {
                            final Checkers.CheckerType.CheckerType attackedChecker = board.getChecker(possibleXCoordinate, possibleYCoordinate);
                            legalMoves.add(new Checkers.Board.PromotionJumpMove(new Checkers.Board.AttackingMove(board, this, possibleXCoordinate + j, possibleYCoordinate + (this.getCheckerColor().getDirection() * 1), attackedChecker)));

                        }  else if (!possibleJump2.isSquareOccupied()) {
                            final Checkers.CheckerType.CheckerType attackedChecker = board.getChecker(possibleXCoordinate, possibleYCoordinate);
                            legalMoves.add(new Checkers.Board.AttackingMove(board, this, possibleXCoordinate + j, possibleYCoordinate + (this.getCheckerColor().getDirection() * 1), attackedChecker));
                        }
                    }
                }


            }
        }


        return legalMoves;
    }

    @Override
    public Checkers.CheckerType.CheckerType moveChecker(Checkers.Board.Move move) {
        return new Man(move.getNewXCoordinate(), move.getNewYCoordinate(), move.getMovedChecker().getCheckerColor());
    }

    @Override
    public String toString() {
        return Checkers.CheckerType.CheckerSymbol.MAN.toString();
    }

    public Checkers.CheckerType.CheckerType getNewKing(){
        return new Checkers.CheckerType.King(this.checkerXCoordinate,this.checkerYCoordinate,this.checkerPieceColor);
    }
}
