package Checkers.Board;

import Checkers.CheckerType.CheckerType;
import Checkers.CheckerType.Man;

public class PromotionMove extends Checkers.Board.Move {

    private Checkers.Board.Move decoratedMove;
    private final CheckerType kingedChecker;

    public PromotionMove(final Checkers.Board.Move decoratedMove) {
        super(decoratedMove.getCheckerBoard(), decoratedMove.getMovedChecker(), decoratedMove.getNewXCoordinate(), decoratedMove.getNewYCoordinate());
        this.decoratedMove = decoratedMove;
        this.kingedChecker = (Man)decoratedMove.getMovedChecker();
    }

    @Override
    public Checkers.Board.CheckerBoard execute() {
        final Checkers.Board.CheckerBoard checkerMovedBoard = this.decoratedMove.execute();
        final Checkers.Board.CheckerBoard.Builder builder = new Checkers.Board.CheckerBoard.Builder();
        for(final CheckerType checker: checkerMovedBoard.currentPlayer().getActiveCheckers()){
            if(!this.kingedChecker.equals(checker)){
                builder.setChecker(checker);
            }
        }
        for(final CheckerType checker: checkerMovedBoard.currentPlayer().getOpponent().getActiveCheckers()){
            builder.setChecker(checker);
        }
        builder.setChecker(this.kingedChecker.getNewKing().moveChecker(this));
        builder.setMoveMaker(checkerMovedBoard.currentPlayer().getPieceColor());
        return builder.build();
    }
    @Override
    public int hashCode(){
        return decoratedMove.hashCode() + (31 * kingedChecker.hashCode());
    }
    @Override
    public boolean equals(final Object other){
        return this == other || other instanceof  PromotionMove && (super.equals(other));
    }
}

