package Checkers.gui;

import Checkers.Board.CheckerBoard;
import Checkers.Board.Square;
import Checkers.CheckerType.CheckerType;
import Checkers.CheckerType.PieceColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;

public class Table {

    protected static final Color lightTileColor = Color.decode("#D1c0a8");
    protected static final Color darkTileColor = Color.decode("#a0816c");
    private final JFrame gameFrame;
    private final Checkers.gui.BoardPanel boardPanel;

    protected static CheckerBoard checkerBoard;
    protected static String checkerBoardImages = "";

    protected static Square sourceSquare;
    protected static Square destinationSquare;
    public static CheckerType humanMovedChecker;

    private Checkers.gui.GameOverPanel gameOverPanel;

    private Checkers.gui.sidePanel sidePanel;




    private final static Dimension OUTER_FRAME_DIMENSION = new Dimension(900,900);

    final static Dimension TILE_PANEL_DIMENSION = new Dimension(12,12);

    final static Dimension BOARD_PANEL_DIMENSION = new Dimension(500,450);



    public Table() {
        this.gameFrame = new JFrame("Checkers");
        this.gameFrame.setLayout(new BorderLayout());

        this.gameFrame.setSize(OUTER_FRAME_DIMENSION);



        // Create the menu bar
        JMenuBar tableMenuBar = createTableMenuBar();
        this.gameFrame.setJMenuBar(tableMenuBar);
        this.sidePanel = new Checkers.gui.sidePanel();
        this.gameFrame.add(sidePanel, BorderLayout.SOUTH);
        this.boardPanel = new Checkers.gui.BoardPanel(this,sidePanel);
        this.checkerBoard = CheckerBoard.createNewCheckerBoard();
        // Create the game over panel

        this.gameOverPanel = new Checkers.gui.GameOverPanel(this);


        this.gameFrame.add(this.gameOverPanel, BorderLayout.NORTH);
        this.gameFrame.add(this.boardPanel, BorderLayout.CENTER);

        this.gameFrame.setVisible(true);
        this.gameOverPanel.setVisible(false);

    }


    public static CheckerBoard getCheckerBoard() {
        return checkerBoard;
    }


    private JMenuBar createTableMenuBar() {
        final JMenuBar tableMenuBar = new JMenuBar();
        tableMenuBar.add(createFileMenu());
        return tableMenuBar;
    }

    private JMenu createFileMenu() {
        final JMenu fileMenu = new JMenu("File");


        JMenuItem restartMenuItem = new JMenuItem("Restart");
        restartMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the restart action
                restartGame();
            }
        });
        fileMenu.add(restartMenuItem);

        // Add an "Exit" menu item
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the exit action (e.g., call System.exit(0))
                System.exit(0);
            }
        });
        fileMenu.add(exitMenuItem);

        return fileMenu;
    }


    public void showGameOverScreen() {
        if(isGameOver()) {


            gameOverPanel.setColor(this.checkerBoard.currentPlayer().getOpponent().getPieceColor());
            gameOverPanel.setVisible(true);
            System.out.println("GameOverPanel created");
            // Show the game-over panel


            // Repaint the frame to make the changes visible
            gameFrame.revalidate();
            gameFrame.repaint();
        }
    }

    // Create a method to restart the game
    public void restartGame() {

        checkerBoard = CheckerBoard.createNewCheckerBoard();


        gameOverPanel.setVisible(false);
        boardPanel.setVisible(true);
        this.sidePanel = new Checkers.gui.sidePanel();


        boardPanel.drawBoard(checkerBoard);


        // Redraw the board

    }


    public boolean isGameOver() {
        if(checkerBoard.currentPlayer().getActiveCheckers().size() == 0) {
            System.out.println("GameOverScreen");
            return true;
        }
        else {
            System.out.println("check");
            System.out.println(checkerBoard.currentPlayer().getActiveCheckers().size());
            return false;

        }
    }










}
