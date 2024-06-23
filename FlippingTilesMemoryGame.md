# Flipping Tiles Memory Game 

## Game Components 

- Tiles : have a number and state (flipped/not flipped)
- Board : Game board made up of all tiles
- Game Rules : flio tiles , check for matches

## Tile class

  - number : Number on Tile
  - isFlipped : boolean to indicate whether the Tile is flipped or not ; initialize as false (not Flipped)
  - Methods for : Return the number on the tile ,Return the current state of the tile , Toggle the state of the tile ,print  tile's state to the console
 
    
  ```java
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

```

## Board Class

- Import : Collections : methods for collections ; ArrayList : methods to change the size of the array
- Attributes : tiles : 2D array of Tile objects -> game board ; size : size of board (e.g.: 3 -> 3x3 board)
- initializeBoard method : initialize empty list to hold tile numbers ; for loop running size * size / 2 times (tile numbers should repeat once to have a pair , add each number twice to list ; shuffle number list for randomization ;Assign Numbers to Tiles
- displayBoard : Print the current state of the board using a nested loop to go through 2D array of tiles 
- getTile : Returns the Tile object at a specific position
- 
 ```java
import java.util.Collections;
import java.util.ArrayList;

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
        for (int i = 0; i < size * size / 2; i++) {
            numbers.add(i);
            numbers.add(i);
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
                System.out.print(tiles[i][j] + " ");
            }
            System.out.println();
        }
    }

    public Tile getTile(int row, int col) {
        return tiles[row][col];
    }
}

 ```

