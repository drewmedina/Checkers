package Checkers.Board;

import Checkers.CheckerType.CheckerType;

public abstract class Move {
    final Checkers.Board.CheckerBoard checkerBoard;
    final CheckerType checker;
    final int newXCoordinate;
    final int newYCoordinate;


    public boolean isAttack() {
        return false;
    }

    public CheckerType getAttackedChecker() {
        return null;
    }
    public Checkers.Board.CheckerBoard getCheckerBoard(){
        return this.checkerBoard;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;


        result = prime * result + this.newXCoordinate + this.newYCoordinate;
        result = prime * result + this.checker.hashCode();
        return result;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Move)) {
            return false;
        }

        final Move otherMove = (Move) other;
        return getNewXCoordinate() == otherMove.getNewXCoordinate() && getNewYCoordinate() == otherMove.getNewYCoordinate()
                && getMovedChecker().equals(otherMove.getMovedChecker());
    }


    public Move(Checkers.Board.CheckerBoard checkerBoard, CheckerType checker, int newXCoordinate, int newYCoordinate) {
        this.checkerBoard = checkerBoard;
        this.checker = checker;
        this.newXCoordinate = newXCoordinate;
        this.newYCoordinate = newYCoordinate;
    }

    public int getCurrentXCoordinate() {
        return this.getMovedChecker().getCheckerXCoordinate();
    }

    public int getCurrentYCoordinate() {
        return this.getMovedChecker().getCheckerYCoordinate();
    }

    public Checkers.Board.CheckerBoard execute() {
        final Checkers.Board.CheckerBoard.Builder builder = new Checkers.Board.CheckerBoard.Builder();
        for (final CheckerType checkerType : this.checkerBoard.currentPlayer().getActiveCheckers()) {
            if (!this.checker.equals(checkerType)) {
                builder.setChecker(checkerType);
            }
        }
        for (final CheckerType checkerType : this.checkerBoard.currentPlayer().getOpponent().getActiveCheckers()) {
            builder.setChecker(checkerType);
        }
        builder.setChecker(this.checker.moveChecker(this));
        builder.setMoveMaker(this.checkerBoard.currentPlayer().getOpponent().getPieceColor());






        return builder.build();


    }

    public CheckerType getMovedChecker() {
        return this.checker;
    }

    public int getNewXCoordinate() {
        return newXCoordinate;
    }

    public int getNewYCoordinate() {
        return newYCoordinate;
    }

}
