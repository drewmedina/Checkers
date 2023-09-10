package Checkers;

import Checkers.Board.CheckerBoard;
import Checkers.gui.Table;

public class CheckerMain {
    public static void main(String args[]) {
        CheckerBoard checkerBoard = CheckerBoard.createNewCheckerBoard();

        System.out.println(checkerBoard);

        Table table = new Table();
    }
}
