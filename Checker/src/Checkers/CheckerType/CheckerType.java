package Checkers.CheckerType;

import Checkers.Board.Move;
import Checkers.Board.CheckerBoard;
import java.util.List;
import java.util.Objects;

public abstract class CheckerType {

    protected final Checkers.CheckerType.CheckerSymbol checkerSymbol;
    protected final int checkerXCoordinate;
    protected final int checkerYCoordinate;
    protected final PieceColor checkerPieceColor;


    protected CheckerType(Checkers.CheckerType.CheckerSymbol checkerSymbol, int checkerXCoordinate, int checkerYCoordinate, PieceColor checkerPieceColor) {
        this.checkerSymbol = checkerSymbol;
        this.checkerXCoordinate = checkerXCoordinate;
        this.checkerYCoordinate = checkerYCoordinate;
        this.checkerPieceColor = checkerPieceColor;
    }

    @Override
    public boolean equals(final Object other){
        if(this == other){
            return true;
        }
        if(!(other instanceof CheckerType)){
            return false;
        }
        final CheckerType otherChecker = (CheckerType) other;
        return ((checkerXCoordinate == otherChecker.getCheckerXCoordinate())
                && checkerYCoordinate == otherChecker.getCheckerYCoordinate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(checkerSymbol, checkerXCoordinate, checkerYCoordinate, checkerPieceColor);
    }


    public PieceColor getCheckerColor(){
        return this.checkerPieceColor;
    }
    public abstract List<Move> getLegalMoves(final CheckerBoard board);

    public abstract CheckerType moveChecker(Move move);

    public Checkers.CheckerType.CheckerSymbol getCheckerSymbol(){
        return checkerSymbol;
    }

    public int getCheckerXCoordinate(){
        return this.checkerXCoordinate;
    }
    public int getCheckerYCoordinate(){
        return this.checkerYCoordinate;
    }

    public abstract CheckerType getNewKing();
}

