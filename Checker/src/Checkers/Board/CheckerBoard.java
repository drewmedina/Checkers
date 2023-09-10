package Checkers.Board;

import Checkers.CheckerType.CheckerType;
import Checkers.CheckerType.Man;

import java.util.ArrayList;
import java.util.List;

import Checkers.CheckerType.PieceColor;
import Checkers.Player.BlackPlayer;
import Checkers.Player.Player;
import Checkers.Player.RedPlayer;
import org.apache.commons.collections4.map.MultiKeyMap;

import static Checkers.CheckerType.PieceColor.BLACK;


public class CheckerBoard {

    private static Square[][] gameboard = new Square[8][8];
    private static List<CheckerType> reds;
    private static List<CheckerType> blacks;
    private final RedPlayer redPlayer;
    private final BlackPlayer blackPlayer;
    private final Player currentPlayer;

    private final MultiKeyMap<Integer, CheckerType> checkerBoardConfig;

    private CheckerBoard(Builder builder) {
        this.checkerBoardConfig = builder.checkerBoardConfig;
        this.gameboard = createGameBoard(builder);
        this.reds = activePieces(this.gameboard, PieceColor.RED);
        this.blacks = activePieces(this.gameboard, BLACK);
        final List<Move> redNormalLegalMoves = calculateLegalMoves(this.reds);
        final List<Move> blackNormalLegalMoves = calculateLegalMoves(this.blacks);
        this.redPlayer = new RedPlayer(this, redNormalLegalMoves, blackNormalLegalMoves);
        this.blackPlayer = new BlackPlayer(this, blackNormalLegalMoves, redNormalLegalMoves);
        this.currentPlayer = builder.nextMoveMaker.choosePlayer(this.redPlayer, this.blackPlayer);

    }



    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                final String squareText = this.gameboard[j][i].toString();
                builder.append(String.format("%3s", squareText));
                if (j == 7) {
                    builder.append("\n");
                }
            }
        }
        return builder.toString();
    }

    private List<Move> calculateLegalMoves(List<CheckerType> checkers) {
        final List<Move> legalMoves = new ArrayList<>();

        for (final CheckerType checker : checkers) {
            legalMoves.addAll(checker.getLegalMoves(this));
        }

        return legalMoves;
    }

    public static Square getSquare(final int xCoordinate, final int yCoordinate) {
        return gameboard[xCoordinate][yCoordinate];
    }

    public static List<CheckerType> activePieces(final Square[][] gameboard, final PieceColor pieceColor) {
        final List<CheckerType> checkers = new ArrayList<>();


        for (final Square squares[] : gameboard) {
            for (final Square square : squares)
                if (square.isSquareOccupied()) {
                    final CheckerType checker = square.getCheckerType();
                    if (checker.getCheckerColor() == pieceColor) {
                        checkers.add(checker);
                    }
                }
        }
        return checkers;
    }

    private static Square[][] createGameBoard(final Builder builder) {
        final Square[][] squares = new Square[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares[j][i] = Square.createSquare(j, i, builder.checkerBoardConfig.get(j, i));
            }
        }
        return squares;
    }

    public CheckerType getChecker(final int xCoordinate, final int yCoordinate) {
        return this.checkerBoardConfig.get(xCoordinate, yCoordinate);
    }

    public static CheckerBoard createNewCheckerBoard() {

        final Builder builder = new Builder();
        //RedPieces
        builder.setChecker(new Man(1, 0, PieceColor.RED));
        builder.setChecker(new Man(3, 0, PieceColor.RED));
        builder.setChecker(new Man(5, 0, PieceColor.RED));
        builder.setChecker(new Man(7, 0, PieceColor.RED));
        builder.setChecker(new Man(0, 1, PieceColor.RED));
        builder.setChecker(new Man(2, 1, PieceColor.RED));
        builder.setChecker(new Man(4, 1, PieceColor.RED));
        builder.setChecker(new Man(6, 1, PieceColor.RED));
        builder.setChecker(new Man(1, 2, PieceColor.RED));
        builder.setChecker(new Man(3, 2, PieceColor.RED));
        builder.setChecker(new Man(5, 2, PieceColor.RED));
        builder.setChecker(new Man(7, 2, PieceColor.RED));
        //BlackPieces
        builder.setChecker(new Man(0, 7, BLACK));
        builder.setChecker(new Man(2, 7, BLACK));
        builder.setChecker(new Man(4, 7, BLACK));
        builder.setChecker(new Man(6, 7, BLACK));
        builder.setChecker(new Man(1, 6, BLACK));
        builder.setChecker(new Man(3, 6, BLACK));
        builder.setChecker(new Man(5, 6, BLACK));
        builder.setChecker(new Man(7, 6, BLACK));
        builder.setChecker(new Man(0, 5, BLACK));
        builder.setChecker(new Man(2, 5, BLACK));
        builder.setChecker(new Man(4, 5, BLACK));
        builder.setChecker(new Man(6, 5, BLACK));

        builder.setMoveMaker(BLACK);

        return builder.build();

    }

    public List<Move> getAllLegalMoves() {
        List<Move> allLegalMoves = new ArrayList<>();
        allLegalMoves.addAll(this.calculateLegalMoves(blacks));
        allLegalMoves.addAll(this.calculateLegalMoves(reds));


        return allLegalMoves;
    }

    public static class Builder {
        MultiKeyMap<Integer, CheckerType> checkerBoardConfig;
        PieceColor nextMoveMaker;

        Move transitionMove;

        public Builder() {
            this.checkerBoardConfig = new MultiKeyMap<>();
        }

        public Builder setChecker(final CheckerType checkerType) {
            this.checkerBoardConfig.put(checkerType.getCheckerXCoordinate(), checkerType.getCheckerYCoordinate(), checkerType);
            return this;
        }

        public Builder setMoveMaker(final PieceColor nextMoveMaker) {
            this.nextMoveMaker = nextMoveMaker;
            return this;
        }

        public Builder setMoveTransition(final Move transitionMove) {
            this.transitionMove = transitionMove;
            return this;
        }

        public CheckerBoard build() {

            return new CheckerBoard(this);
        }
    }

    public List<CheckerType> getBlackCheckers() {
        return this.blacks;
    }

    public List<CheckerType> getRedCheckers() {
        return this.reds;
    }

    public Player blackPlayer() {
        return this.blackPlayer;
    }

    public Player redPlayer() {
        return this.redPlayer;
    }

    public Player currentPlayer() {
        return this.currentPlayer;
    }





}
