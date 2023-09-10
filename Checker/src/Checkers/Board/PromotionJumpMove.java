package Checkers.Board;

import Checkers.CheckerType.CheckerType;
import Checkers.CheckerType.Man;

public class PromotionJumpMove extends Checkers.Board.AttackingMove {


    final Checkers.Board.Move decoratedMove;
    final CheckerType kingedChecker;


    public PromotionJumpMove(Checkers.Board.Move decoratedMove) {
        super(decoratedMove.getCheckerBoard(), decoratedMove.getMovedChecker(), decoratedMove.getNewXCoordinate(), decoratedMove.getNewYCoordinate(),decoratedMove.getAttackedChecker());
        this.decoratedMove = decoratedMove;
        this.kingedChecker = (Man)decoratedMove.getMovedChecker();
    }
    @Override
    public Checkers.Board.CheckerBoard execute() {
        final Checkers.Board.CheckerBoard.Builder builder = new Checkers.Board.CheckerBoard.Builder();
        for (final CheckerType checker : this.checkerBoard.currentPlayer().getActiveCheckers()) {
            if (!this.kingedChecker.equals(checker)) {
                builder.setChecker(checker);
            }
        }
        for (final CheckerType checkerType : checkerBoard.currentPlayer().getOpponent().getActiveCheckers()) {
            if (!checkerType.equals(this.getAttackedChecker())) {
                builder.setChecker(checkerType);
            }
        }
        builder.setChecker(this.kingedChecker.getNewKing().moveChecker(this));

        builder.setMoveMaker(this.checkerBoard.currentPlayer().getOpponent().getPieceColor());

        return builder.build();
    }

}
