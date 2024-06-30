package com.example.memorygame;

public class Tile {
    private int number;
    private boolean isFlipped;

    public Tile(int number) {
        this.number = number;
        this.isFlipped = false;
    }

    public int getNumber() {
        return number;
    }

    public boolean isFlipped() {
        return isFlipped;
    }

    public void flip() {
        isFlipped = !isFlipped;
    }

    public void printTile() {
        if (isFlipped) {
            System.out.print(number + " ");
        } else {
            System.out.print("X ");
        }
    }
}
