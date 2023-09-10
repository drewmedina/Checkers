package Checkers.Board;

import Checkers.CheckerType.CheckerType;
import Checkers.Board.CheckerBoard.Builder;

public class AttackingMove extends Checkers.Board.Move {

    final CheckerType attackedChecker;

    public AttackingMove(Checkers.Board.CheckerBoard checkerBoard, CheckerType checker, int newXCoordinate, int newYCoordinate, final CheckerType attackedChecker) {
        super(checkerBoard, checker, newXCoordinate, newYCoordinate);
        this.attackedChecker = attackedChecker;
    }

    @Override
    public int hashCode() {
        return this.attackedChecker.hashCode() + super.hashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AttackingMove)) {
            return false;
        }
        final AttackingMove otherAttackMove = (AttackingMove) other;
        return super.equals(otherAttackMove) && getAttackedChecker().equals(otherAttackMove.getAttackedChecker());
    }
    @Override
    public Checkers.Board.CheckerBoard execute() {
        final Builder builder = new Builder();
        for (final CheckerType checkerType : this.checkerBoard.currentPlayer().getActiveCheckers()) {
            if (!this.checker.equals(checkerType)) {
                builder.setChecker(checkerType);
            }
        }
        for (final CheckerType checkerType : checkerBoard.currentPlayer().getOpponent().getActiveCheckers()) {
            if (!checkerType.equals(this.getAttackedChecker())) {
                builder.setChecker(checkerType);
            }
        }
        builder.setChecker(getMovedChecker().moveChecker(this));

        for (int j = -1; j <= 1; j += 2) {
            if (getMovedChecker().getLegalMoves(this.checkerBoard).contains(new AttackingMove(this.checkerBoard, getMovedChecker(), getMovedChecker().getCheckerXCoordinate() + j, getMovedChecker().getCheckerYCoordinate() + (getMovedChecker().getCheckerColor().getDirection() * 1), this.checkerBoard.getChecker(getMovedChecker().getCheckerXCoordinate() + j*2, getMovedChecker().getCheckerYCoordinate() + (getMovedChecker().getCheckerColor().getDirection() * 2))))) {
                builder.setMoveMaker(this.checkerBoard.currentPlayer().getPieceColor());
            } else
                builder.setMoveMaker(this.checkerBoard.currentPlayer().getOpponent().getPieceColor());



        }
        return builder.build();
    }

    @Override
    public boolean isAttack() {
        return true;
    }

    @Override
    public CheckerType getAttackedChecker() {
        return this.attackedChecker;
    }


}
