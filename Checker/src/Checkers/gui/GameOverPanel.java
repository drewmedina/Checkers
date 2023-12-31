package Checkers.gui;

import Checkers.CheckerType.PieceColor;
import Checkers.gui.Table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverPanel extends JPanel {
    private JLabel gameOverMessage;
    private PieceColor color;

    public GameOverPanel(final Table table) {
        setLayout(new GridLayout(100, 100));
        setBackground(Color.decode("#B6977D"));

        gameOverMessage = new JLabel("GAME OVER " + color + " WINS");
        gameOverMessage.setFont(new Font("Times New Roman", Font.BOLD, 50));

        add(gameOverMessage);

        JButton restartButton = new JButton("New Game");
        gameOverMessage.setFont(new Font("Helvetica Neue", Font.BOLD, 35));
        restartButton.setBackground(Color.decode("#140a07"));
        restartButton.setForeground(Color.WHITE);
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table.restartGame();
            }
        });


        add(restartButton);
    }

    public void setColor(PieceColor color) {
        this.color = color;

        gameOverMessage.setText("GAME OVER\n " + color + "\n WINS");
    }
}
