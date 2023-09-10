package Checkers.CheckerType;


import Checkers.Board.*;

import java.util.ArrayList;
import java.util.List;

public class King extends Checkers.CheckerType.CheckerType {

    public King(int xCoordinate, int yCoordinate, PieceColor pieceColor){
        super(Checkers.CheckerType.CheckerSymbol.KING,xCoordinate,yCoordinate, pieceColor);
    }
    @Override
    public List<Checkers.Board.Move> getLegalMoves(Checkers.Board.CheckerBoard board) {
        List<Checkers.Board.Move> legalMoves = new ArrayList<Checkers.Board.Move>();
        for(int i = -1; i<=1;i+=2) {
            for (int j = -1; j <= 1; j += 2) {
                final int possibleXCoordinate = checkerXCoordinate + j;
                final int possibleYCoordinate = checkerYCoordinate + i;
                if ((possibleXCoordinate < 0 || possibleXCoordinate > 7) || (possibleYCoordinate < 0 || possibleYCoordinate > 7)) {
                    continue;
                }
                final Checkers.Board.Square possibleMove = Checkers.Board.CheckerBoard.getSquare(possibleXCoordinate, possibleYCoordinate);
                if (!possibleMove.isSquareOccupied()) {
                    legalMoves.add(new Checkers.Board.NormalMove(board, this, possibleXCoordinate, possibleYCoordinate));


                } else {
                    if (possibleMove.getCheckerType().getCheckerColor() != this.getCheckerColor()) {
                        if ((j == -1 && i == -1) && (possibleXCoordinate + j >= 0 && possibleYCoordinate + i>= 0)) {
                            final Checkers.Board.Square possibleJump = Checkers.Board.CheckerBoard.getSquare(possibleXCoordinate + j,
                                    possibleYCoordinate + i);
                            if (!possibleJump.isSquareOccupied()) {
                                Checkers.CheckerType.CheckerType attackedChecker = board.getChecker(possibleXCoordinate,possibleYCoordinate);
                                legalMoves.add(new Checkers.Board.AttackingMove(board, this, possibleXCoordinate + j, possibleYCoordinate + i, attackedChecker));
                            }
                        }
                        if (j == 1 && i == 1 && (possibleXCoordinate + j <= 7 && possibleYCoordinate + i <= 7)) {
                            final Checkers.Board.Square possibleJump2 = Checkers.Board.CheckerBoard.getSquare(possibleXCoordinate + j,
                                    possibleYCoordinate + i);
                            if (!possibleJump2.isSquareOccupied()) {
                                Checkers.CheckerType.CheckerType attackedChecker2 = board.getChecker(possibleXCoordinate,possibleYCoordinate);
                                legalMoves.add(new Checkers.Board.AttackingMove(board, this, possibleXCoordinate + j, possibleYCoordinate + i,attackedChecker2));
                            }
                        }
                        if (j == -1 && i == 1 && (possibleXCoordinate + j >=0 && possibleYCoordinate + i <= 7)) {
                            final Checkers.Board.Square possibleJump2 = Checkers.Board.CheckerBoard.getSquare(possibleXCoordinate + j,
                                    possibleYCoordinate + i);
                            if (!possibleJump2.isSquareOccupied()) {
                                Checkers.CheckerType.CheckerType attackedChecker3 = board.getChecker(possibleXCoordinate,possibleYCoordinate);
                                legalMoves.add(new Checkers.Board.AttackingMove(board, this, possibleXCoordinate + j, possibleYCoordinate + i,attackedChecker3));
                            }
                        }
                        if (j == 1 && i == -1 && (possibleXCoordinate + j <=7 && possibleYCoordinate + i >= 0)) {
                            final Checkers.Board.Square possibleJump2 = Checkers.Board.CheckerBoard.getSquare(possibleXCoordinate + j,
                                    possibleYCoordinate + i);
                            if (!possibleJump2.isSquareOccupied()) {
                                Checkers.CheckerType.CheckerType attackedChecker4 = board.getChecker(possibleXCoordinate,possibleYCoordinate);
                                legalMoves.add(new Checkers.Board.AttackingMove(board, this, possibleXCoordinate + j, possibleYCoordinate + i,attackedChecker4));
                            }
                        }
                    }


                }
            }
        }
        return legalMoves;
    }

    @Override
    public Checkers.CheckerType.CheckerType moveChecker(Checkers.Board.Move move) {
        return new King(move.getNewXCoordinate(), move.getNewYCoordinate(), move.getMovedChecker().getCheckerColor());
    }

    @Override
    public String toString(){
        return Checkers.CheckerType.CheckerSymbol.KING.toString();
    }

    @Override
    public Checkers.CheckerType.CheckerType getNewKing(){
        return null;
    }
}
