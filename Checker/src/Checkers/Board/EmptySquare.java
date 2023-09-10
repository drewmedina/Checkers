package Checkers.Board;

import Checkers.CheckerType.CheckerType;

public final class EmptySquare extends Square {


    public EmptySquare(int xCoordinate, int yCoordinate){
        super(xCoordinate,yCoordinate);
    }

    @Override
    public boolean isSquareOccupied() {
        return false;
    }

    @Override
    public CheckerType getCheckerType() {
        return null;
    }

    @Override
    public String toString(){
        return "-";
    }
}
