package Checkers.Board;

import Checkers.CheckerType.CheckerType;

public abstract class Square {

    protected final int xCoordinate;
    protected final int yCoordinate;
    private static final Square[][] Empty_Square = createSquares();

    private static Square[][] createSquares(){
        Square[][] emptySquareArray = new Square[8][8];
        for(int i = 0; i< emptySquareArray.length; i++){
            for(int j = 0; j<emptySquareArray[0].length; j++){
                emptySquareArray[i][j] = new Checkers.Board.EmptySquare(i,j);

            }
        }
        return emptySquareArray;
    }

    public static Square createSquare(final int xCoordinate,final int yCoordinate, final CheckerType checker){
        return checker != null ? new Checkers.Board.OccupiedSquare(xCoordinate,yCoordinate,checker): Empty_Square[xCoordinate][yCoordinate];
    }

    protected Square(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }



    public abstract boolean isSquareOccupied();

    public abstract CheckerType getCheckerType();

    public int getxCoordinate(){
        return this.xCoordinate;

    }
    public int getyCoordinate(){
        return this.yCoordinate;
    }





}
