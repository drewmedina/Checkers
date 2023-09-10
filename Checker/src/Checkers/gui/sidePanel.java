package Checkers.gui;

import Checkers.CheckerType.PieceColor;

import javax.swing.*;
import java.awt.*;

import static Checkers.CheckerType.PieceColor.BLACK;

public class sidePanel extends JPanel {
    private JLabel currentPlayerLabel;
    private JLabel blackCapturedLabel;
    private JLabel redCapturedLabel;

    public sidePanel() {
        setLayout(new GridLayout(3, 1));
        setBackground(Color.decode("#a08a6c"));
        currentPlayerLabel = new JLabel("Current Player: ");

        redCapturedLabel = new JLabel("Black");
        blackCapturedLabel = new JLabel("Red");
        currentPlayerLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        redCapturedLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        blackCapturedLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));


        add(currentPlayerLabel);
        add(blackCapturedLabel);
        add(redCapturedLabel);
    }

    public void updateCurrentPlayerLabel(PieceColor color) {
        currentPlayerLabel.setText("Current Player: " + (color == BLACK ? "Black" : "Red"));
    }

    public void updateCapturedCount(int blackCaptured, int redCaptured) {
        blackCapturedLabel.setText("Black Pieces Captured: " + blackCaptured);
        redCapturedLabel.setText("Red Pieces Captured: " + redCaptured);
    }
}
