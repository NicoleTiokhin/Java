package com.example.tictactoe;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TicTacToe extends JFrame implements ActionListener {
    private JButton[] buttons = new JButton[9];
    private boolean isXTurn = true;

    public TicTacToe() {
        setTitle("Tic Tac Toe Game");
        setSize(500, 500);
        setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 60));
            buttons[i].addActionListener(this);
            add(buttons[i]);
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        int index = 0;

        while (index < 9 && clickedButton != buttons[index]) {
            index++;
        }

        if (index >= 9 || !buttons[index].getText().equals("")) {
            JOptionPane.showMessageDialog(this, "This cell is already occupied. Please choose another one.");
            return;
        }

        if (isXTurn) {
            buttons[index].setText("X");
        } else {
            buttons[index].setText("O");
        }

        // Toggle turn
        isXTurn = !isXTurn;

        checkForWinner();
    }

    private void checkForWinner() {
        if (checkRowCol(0, 1, 2) || checkRowCol(3, 4, 5) || checkRowCol(6, 7, 8) || 
            checkRowCol(0, 3, 6) || checkRowCol(1, 4, 7) || checkRowCol(2, 5, 8) || 
            checkRowCol(0, 4, 8) || checkRowCol(2, 4, 6)) {                       
            announceWinner();
        } else if (isBoardFull()) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetBoard();
        }
    }

    private boolean checkRowCol(int b1, int b2, int b3) {
        return buttons[b1].getText().equals(buttons[b2].getText()) &&
               buttons[b2].getText().equals(buttons[b3].getText()) &&
               !buttons[b1].getText().equals("");
    }

    private void announceWinner() {
        String winner = isXTurn ? "O" : "X";
        JOptionPane.showMessageDialog(this, "Player " + winner + " wins!");
        resetBoard();
    }

    private boolean isBoardFull() {
        for (JButton button : buttons) {
            if (button.getText().equals("")) {
                return false;
            }
        }
        return true;
    }

    private void resetBoard() {
        for (JButton button : buttons) {
            button.setText("");
        }
        isXTurn = true;
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}