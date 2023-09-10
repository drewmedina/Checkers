package Checkers.Board;

import Checkers.CheckerType.CheckerType;

import static Checkers.CheckerType.PieceColor.RED;

public final class OccupiedSquare extends Square{

    private final CheckerType checkerOnSquare;

    public OccupiedSquare(int xCoordinate, int yCoordinate, CheckerType checkerOnSquare){
        super(xCoordinate,yCoordinate);
        this.checkerOnSquare = checkerOnSquare;
    }

    @Override
    public boolean isSquareOccupied(){
        return true;
    }
    @Override
    public CheckerType getCheckerType(){
        return checkerOnSquare;
    }
    @Override
    public String toString(){
        if(getCheckerType().getCheckerColor()==RED)
            return getCheckerType().toString().toLowerCase();
        else
            return getCheckerType().toString();

    }


}
