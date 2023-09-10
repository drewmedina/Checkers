package Checkers.Board;

public class MoveCreator {

    public static Checkers.Board.Move createMove(final Checkers.Board.CheckerBoard checkerBoard,
                                                 final int currentXCoordinate,
                                                 final int currentYCoordinate,
                                                 final int newXCoordinate,
                                                 final int newYCoordinate){
        for(final Checkers.Board.Move move: checkerBoard.getAllLegalMoves()) {
            if (move.getCurrentXCoordinate() == currentXCoordinate && move.getCurrentYCoordinate() == currentYCoordinate
                    && move.getNewXCoordinate() == newXCoordinate && move.getNewYCoordinate() == newYCoordinate) {

                return move;


            }

        }
        return null;
    }
}
