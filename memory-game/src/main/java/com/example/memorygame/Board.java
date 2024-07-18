package com.example.memorygame;

import java.util.ArrayList;
import java.util.Collections;

public class Board {
    private Tile[][] tiles;
    private int size;

    public Board(int size) {
        this.size = size;
        tiles = new Tile[size][size];
        initializeBoard();
    }

    private void initializeBoard() {
        ArrayList<Integer> numbers = new ArrayList<>();
        int numberOfTiles = size * size;

        for (int i = 0; i < numberOfTiles / 2; i++) {
            numbers.add(i);
            numbers.add(i);
        }

        if (numberOfTiles % 2 != 0) {
            numbers.add(numberOfTiles / 2);
        }

        Collections.shuffle(numbers);

        int index = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tiles[i][j] = new Tile(numbers.get(index++));
            }
        }
    }

    public void displayBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tiles[i][j].printTile();
            }
            System.out.println();
        }
    }

    public Tile getTile(int row, int col) {
        return tiles[row][col];
    }
}
