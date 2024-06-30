package com.example.memorygame;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class MemoryGame {
    private Board board;
    private int size;
    private Tile firstTile;
    private Tile secondTile;
    private Timer timer;
    private boolean timeUp;
    private int timeRemaining;
    private int moveCount;

    public MemoryGame(int size) {
        this.size = size;
        board = new Board(size);
        timeUp = false;
        timer = new Timer();
        timeRemaining = 120;
        moveCount = 0;
    }

    public void startTimer() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (timeRemaining > 0) {
                    timeRemaining--;
                } else {
                    timeUp = true;
                    System.out.println("Time's up! Game over.");
                    System.out.println("You needed a total of: " + moveCount + " moves");
                    displayFinalBoard();
                    System.exit(0);
                }
            }
        }, 1000, 1000);
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("You have " + timeRemaining + " seconds to solve the memory game.");
        startTimer();

        while (!timeUp) {
            board.displayBoard();

            firstTile = selectTile(scanner, "first");
            firstTile.flip();
            board.displayBoard();

            secondTile = selectTile(scanner, "second");
            secondTile.flip();
            board.displayBoard();
            
            moveCount++;

            if (firstTile.getNumber() == secondTile.getNumber()) {
                System.out.println("It's a match!");
            } else {
                System.out.println("Not a match. Try again.");
                firstTile.flip();
                secondTile.flip();
            }

            if (isGameOver()) {
                System.out.println("Congratulations! You've found all the pairs.");
                break;
            }

            System.out.println("Time remaining: " + timeRemaining + " seconds");
        }
        timer.cancel();
        scanner.close();
    }

    private Tile selectTile(Scanner scanner, String tileOrder) {
        int row, col;
        while (true) {
            System.out.println("Enter the coordinates of the " + tileOrder + " tile to flip (row col):");
            row = scanner.nextInt();
            col = scanner.nextInt();
            if (isValid(row, col) && !board.getTile(row, col).isFlipped()) {
                return board.getTile(row, col);
            } else {
                System.out.println("Invalid coordinates or tile already flipped. Please try again.");
                System.out.println("Time remaining: " + timeRemaining + " seconds");
            }
        }
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size;
    }

    private boolean isGameOver() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!board.getTile(i, j).isFlipped()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void displayFinalBoard() {
        board.displayBoard();
    }
}

