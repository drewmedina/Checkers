package Checkers.Player;

import Checkers.Board.CheckerBoard;
import Checkers.Board.Move;
import Checkers.CheckerType.CheckerType;
import Checkers.CheckerType.PieceColor;

import java.util.List;

public abstract class Player {
    protected final CheckerBoard checkerBoard;
    protected final List<Move> legalMoves;

    Player(final CheckerBoard checkerBoard,final List<Move> legalMoves, final List<Move> opponentMoves){
        this.checkerBoard = checkerBoard;
        this.legalMoves = legalMoves;
    }

    public boolean isMoveLegal(Move move){
        return this.legalMoves.contains(move);
    }
    public Checkers.Player.MoveSwitch makeMove(final Move move){
        if(!isMoveLegal(move)){
            return new Checkers.Player.MoveSwitch(this.checkerBoard, move, Checkers.Player.MoveStatus.ILLEGAL_MOVE);
        }
        final CheckerBoard transitionBoard = move.execute();

        return new Checkers.Player.MoveSwitch(transitionBoard,move, Checkers.Player.MoveStatus.DONE);


    }
    public abstract List<CheckerType> getActiveCheckers();
    public abstract PieceColor getPieceColor();
    public abstract Player getOpponent();



}
