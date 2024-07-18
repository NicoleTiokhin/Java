```java
package com.example.memorygame;

public class App {
    public static void main(String[] args) {
        MemoryGame game = new MemoryGame(3); 
        game.play();
    }
}

```

```java
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

```

```java
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


```


```java
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

```

.
├── dependency-reduced-pom.xml
├── out
│   └── com
│       └── example
│           └── memorygame
│               ├── App.class
│               ├── Board.class
│               ├── MemoryGame$1.class
│               ├── MemoryGame.class
│               └── Tile.class
├── pom.xml
├── src
│   ├── main
│   │   └── java
│   │       └── com
│   │           └── example
│   │               └── memorygame
│   │                   ├── App.java
│   │                   ├── Board.java
│   │                   ├── MemoryGame.java
│   │                   └── Tile.java
│   └── test
│       └── java
└── target
    ├── classes
    │   └── com
    │       └── example
    │           └── memorygame
    │               ├── App.class
    │               ├── Board.class
    │               ├── MemoryGame$1.class
    │               ├── MemoryGame.class
    │               └── Tile.class
    ├── maven-archiver
    │   └── pom.properties
    ├── maven-status
    │   └── maven-compiler-plugin
    │       ├── compile
    │       │   └── default-compile
    │       │       ├── createdFiles.lst
    │       │       └── inputFiles.lst
    │       └── testCompile
    │           └── default-testCompile
    │               ├── createdFiles.lst
    │               └── inputFiles.lst
    ├── memorygame-project-1.0-SNAPSHOT.jar
    └── original-memorygame-project-1.0-SNAPSHOT.jar


```java
```
