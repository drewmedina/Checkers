package Checkers.Player;


import Checkers.Board.CheckerBoard;
import Checkers.Board.Move;
import Checkers.CheckerType.CheckerType;
import Checkers.CheckerType.PieceColor;

import java.util.List;

public class RedPlayer extends Checkers.Player.Player {
    public RedPlayer(CheckerBoard checkerBoard, List<Move> redNormalLegalMoves, List<Move> blackNormalLegalMoves) {
        super(checkerBoard,redNormalLegalMoves,blackNormalLegalMoves);
    }

    @Override
    public List<CheckerType> getActiveCheckers() {
        return this.checkerBoard.getRedCheckers();
    }
    @Override
    public PieceColor getPieceColor(){
        return PieceColor.RED;
    }
    @Override
    public Checkers.Player.Player getOpponent(){
        return this.checkerBoard.blackPlayer();
    }
}
