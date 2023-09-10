package Checkers.gui;

import Checkers.Board.CheckerBoard;
import Checkers.Board.Move;
import Checkers.Board.MoveCreator;
import Checkers.Player.MoveSwitch;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;


import static Checkers.gui.Table.*;
import static javax.swing.SwingUtilities.isLeftMouseButton;
import static javax.swing.SwingUtilities.isRightMouseButton;


public class TilePanel extends JPanel {


    private final int xTileID;
    private final int yTileID;

    private final Checkers.gui.Table table;
    private final Checkers.gui.sidePanel sidepanel;




    TilePanel(final Checkers.gui.Table table, final Checkers.gui.BoardPanel boardPanel,
              final int xTileId, final int yTileID, final Checkers.gui.sidePanel sidepanel) {

        super(new GridBagLayout());
        this.table = table;
        this.xTileID = xTileId;
        this.yTileID = yTileID;
        this.sidepanel = sidepanel;
        setPreferredSize(TILE_PANEL_DIMENSION);
        assignTileColor();
        assignTilePieceIcon(Checkers.gui.Table.getCheckerBoard());


        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                if (isRightMouseButton(e)) {

                    sourceSquare = null;
                    destinationSquare = null;
                    humanMovedChecker = null;
                } else if (isLeftMouseButton(e)) {
                    if (sourceSquare == null) {
                        sourceSquare = checkerBoard.getSquare(xTileID, yTileID);
                        humanMovedChecker = sourceSquare.getCheckerType();
                        if (humanMovedChecker == null) {
                            sourceSquare = null;
                        }
                    } else {
                        System.out.println(sourceSquare.getxCoordinate() + " " + sourceSquare.getyCoordinate());
                        destinationSquare = checkerBoard.getSquare(xTileID, yTileID);
                        final Move move = MoveCreator.createMove(checkerBoard, sourceSquare.getxCoordinate(), sourceSquare.getyCoordinate(), destinationSquare.getxCoordinate(), destinationSquare.getyCoordinate());

                        final MoveSwitch transition = checkerBoard.currentPlayer().makeMove(move);
                        if (transition.getMoveStatus().isDone()) {
                            checkerBoard = transition.getTransitionBoard();
                        }
                        sourceSquare = null;
                        destinationSquare = null;
                        humanMovedChecker = null;

                        sidepanel.updateCurrentPlayerLabel(table.checkerBoard.currentPlayer().getPieceColor());
                        sidepanel.updateCapturedCount(12 -checkerBoard.redPlayer().getActiveCheckers().size(),12 - checkerBoard.blackPlayer().getActiveCheckers().size() );

                        if ((table.isGameOver())) {

                            table.showGameOverScreen();
                        }




                    }


                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            boardPanel.drawBoard(checkerBoard);

                        }
                    });
                }


            }


            @Override
            public void mousePressed(final MouseEvent e) {

            }

            @Override
            public void mouseReleased(final MouseEvent e) {

            }

            @Override
            public void mouseEntered(final MouseEvent e) {

            }

            @Override
            public void mouseExited(final MouseEvent e) {

            }
        });
        validate();

    }

    public void drawTile(final CheckerBoard board) {
        assignTileColor();
        assignTilePieceIcon(board);
        highlightLegals(board);
        validate();
        repaint();

    }

    private void assignTileColor() {
        boolean isLight = (xTileID + yTileID) % 2 == 0;
        setBackground(isLight ? lightTileColor : darkTileColor);
    }

    private void assignTilePieceIcon(final CheckerBoard board) {
        this.removeAll();
        if (board.getSquare(this.xTileID, this.yTileID).isSquareOccupied()) {
            try {

                final BufferedImage image =
                        ImageIO.read(new File(checkerBoardImages + board.getSquare(this.xTileID, this.yTileID).getCheckerType().getCheckerColor().toString().substring(0, 1) +
                                board.getSquare(this.xTileID, this.yTileID).getCheckerType().toString() + ".png"));

                add(new JLabel(new ImageIcon(image)));
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("NO");
            }
        }
    }

    private void highlightLegals(final CheckerBoard board) {
        if (true) {
            for (final Move move : checkerLegalMoves(board)) {
                if (move.getNewXCoordinate() == this.xTileID && move.getNewYCoordinate() == this.yTileID) {
                    try {
                        add(new JLabel(new ImageIcon(ImageIO.read(new File("grey.png")))));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private Collection<Move> checkerLegalMoves(final CheckerBoard board) {
        if (humanMovedChecker != null && humanMovedChecker.getCheckerColor() == board.currentPlayer().getPieceColor()) {
            return humanMovedChecker.getLegalMoves(board);
        }
        return Collections.emptyList();
    }

}
