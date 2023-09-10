package Checkers.Player;

import Checkers.Board.CheckerBoard;
import Checkers.Board.Move;
import Checkers.CheckerType.CheckerType;
import Checkers.CheckerType.PieceColor;

import java.util.List;

public class BlackPlayer extends Player{
    public BlackPlayer(CheckerBoard checkerBoard, List<Move> blackNormalLegalMoves, List<Move> redNormalLegalMoves) {
        super(checkerBoard,blackNormalLegalMoves,redNormalLegalMoves);
    }

    @Override
    public List<CheckerType> getActiveCheckers() {
        return this.checkerBoard.getBlackCheckers();
    }
    @Override
    public PieceColor getPieceColor(){
        return PieceColor.BLACK;
    }
    @Override
    public Player getOpponent(){
        return this.checkerBoard.redPlayer();
    }
}
