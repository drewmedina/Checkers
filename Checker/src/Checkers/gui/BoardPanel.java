package Checkers.gui;

import Checkers.Board.CheckerBoard;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static Checkers.gui.Table.BOARD_PANEL_DIMENSION;

public class BoardPanel extends JPanel {
    final List<TilePanel> boardTiles;

    BoardPanel(Table table, sidePanel sidepanel) {
        super(new GridLayout(8, 8));
        this.boardTiles = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                final TilePanel tilePanel = new TilePanel(table,this, j,i,sidepanel);
                this.boardTiles.add(tilePanel);
                add(tilePanel);

            }
            setPreferredSize(BOARD_PANEL_DIMENSION);
            validate();

        }
    }

    public void drawBoard(final CheckerBoard board) {
        removeAll();
        for(final TilePanel tilePanel : boardTiles){
            tilePanel.drawTile(board);
            add(tilePanel);

        }
        validate();
        repaint();
    }

}
