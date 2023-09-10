package Checkers.Board;

import Checkers.CheckerType.CheckerType;

public class NormalMove extends Checkers.Board.Move {


    public NormalMove(Checkers.Board.CheckerBoard checkerBoard, CheckerType checker, int newXCoordinate, int newYCoordinate) {
        super(checkerBoard, checker, newXCoordinate, newYCoordinate);
    }

}
