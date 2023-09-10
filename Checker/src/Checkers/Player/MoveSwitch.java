package Checkers.Player;

import Checkers.Board.CheckerBoard;
import Checkers.Board.Move;


public class MoveSwitch {

    private final CheckerBoard transitionBoard;
    private final Move move;
    private final Checkers.Player.MoveStatus moveStatus;

    public MoveSwitch(final CheckerBoard transitionBoard, final Move move, final Checkers.Player.MoveStatus moveStatus) {
        this.transitionBoard = transitionBoard;
        this.move = move;
        this.moveStatus = moveStatus;
    }

    public Checkers.Player.MoveStatus getMoveStatus() {
        return this.moveStatus;
    }

    public CheckerBoard getTransitionBoard() {
        return this.transitionBoard;
    }
}
