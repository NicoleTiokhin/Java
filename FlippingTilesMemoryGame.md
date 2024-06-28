# Flipping Tiles Memory Game 

## Game Components 

- Tiles : have a number and state (flipped/not flipped), Tile class
- Board : Game board made up of all tiles , Board class
- Game Rules : flio tiles , check for matches , MemoryGame class

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
## MemoryGame 

- Imports : Scanner (to read input from the user)
- Attributes : board of type Board (reps Game Board) , size of type int , firstTile and secondTile (Tiles chosen by Users during their turn)
- Initialize the game  board of the specified size
- play Method :  Initialize Scanner ; Game loop ; Close Scanner
- Game loop : runs until all pairs are found ; Display the Board -> Prompt  player to enter the row and column of the first tile to flip -> Retrieves the tile and display current board with flipped tile ->Prompts the player to enter the row and column of the second tile to flip -> Retrieves the tile and display current board with flipped tile -> Check for Match -> If the tiles don´t match flip both tiles back to their hidden state -> If all pairs have been found the loop is over
- GameOver Method : true if the game is over (all tile pairs collected and false otherwise)
- Main Method : Entry point of the program , Initialize a MemoryGame instance with a 3x3 board and start the game by calling the play() method


 ```java
import java.util.Scanner;

public class MemoryGame {
    private Board board;
    private int size;
    private Tile firstTile;
    private Tile secondTile;

    public MemoryGame(int size) {
        this.size = size;
        board = new Board(size);
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            board.displayBoard();
            System.out.println("Enter the coordinates of the first tile to flip (row col):");
            int row1 = scanner.nextInt();
            int col1 = scanner.nextInt();
            firstTile = board.getTile(row1, col1);
            firstTile.flip();
            board.displayBoard();

            System.out.println("Enter the coordinates of the second tile to flip (row col):");
            int row2 = scanner.nextInt();
            int col2 = scanner.nextInt();
            secondTile = board.getTile(row2, col2);
            secondTile.flip();
            board.displayBoard();

            if (firstTile.getNumber() == secondTile.getNumber()) {
                System.out.println("It's a match!");
            } else {
                System.out.println("Not a match. Try again.");
                firstTile.flip();
                secondTile.flip();
            }

            if (GameOver()) {
                System.out.println("Congratulations! You've found all the pairs.");
                break;
            }
        }
        scanner.close();
    }

    private boolean GameOver() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!board.getTile(i, j).isFlipped()) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        MemoryGame game = new MemoryGame(3); // Create a 3x3 board
        game.play();
    }
}

 ```
## Example of how the game would work

1. Game starts ; Board is displayed ; all tiles are hidden <br>
X X X <br>
X X X <br>
X X X <br>
Enter the coordinates of the first tile to flip (row col):<br>
2. User input : 0 0
3. Output : <br>
1 X X <br>
X X X <br>
X X X <br>
Enter the coordinates of the second tile to flip (row col):<br>
4. User input : 1 1
5. Output: <br>
1 X X <br>
X 2 X <br>
X X X <br>
Not a match. Try again.<br>
X X X <br>
X X X <br>
X X X <br>
Enter the coordinates of the first tile to flip (row col):<br>
6. User input : 1 1
7. Output :<br>
X 2 X <br>
X 2 X <br>
X X X <br>
It's a match!<br>
X 2 X <br>
X 2 X <br>
X X X <br>
Enter the coordinates of the first tile to flip (row col):<br>
8. several steps later
9. game ends :<br>
10. 1 X 3 <br>
1 2 X <br>
1 2 3 <br>
It's a match!<br>
1 X 3 <br>
1 2 X <br>
1 2 3 <br>
Congratulations! You've found all the pairs.<br>

## Ideas on how to Elaborate Game a bit 

  Let user decide how many tiles there should be (Different Board Sizes) <br>
 ~~Time Limit~~ <br>
  Count how many number of moves or time taken ?<br>
  Levels of Difficulty<br>
  GUI<br>
</ul>

## Add a Time limit 

- Add a Timer to MemoryGame class
- TimerTask to create a countdown
- End game when time is up

### Updated MemoryGame class

imports : 

```java
import java.util.Timer; //for scheduling tasks
import java.util.TimerTask; //define tasks for Timer
```

new attributes : 

```java
private Timer timer; // timer for time limit 
private boolean timeUp; // indicates when time is up 
```

new method : startTimer -> Starts a timer
A task will run after the given time 
Set the timeUp flag to true when the timer runs out and print a message and display the final board state and terminate the program

```java
    public void startTimer(int seconds) {
        timer.schedule(new TimerTask() {
            @Override // we are overriding the TimerTasks instance run method
            public void run() { // What task should be executed when timer runs out ?
                timeUp = true;  
                System.out.println("Time's up! Game over.");
                displayFinalBoard();
                System.exit(0);  
            }
        }, seconds * 1000);  // Convert seconds to milliseconds -> Timer.schedule method expects milliseconds

    }

```

Modified play method :

set Timer to 3 minutes
Continue the game loop until the time is up
Cancel the timer if the game ends before time is up
```java
public void play() {
    Scanner scanner = new Scanner(System.in);
    startTimer(360); 

    while (!timeUp) {  
        board.displayBoard();
        firstTile = selectTile(scanner, "first");
        firstTile.flip();
        board.displayBoard();

        secondTile = selectTile(scanner, "second");
        secondTile.flip();
        board.displayBoard();

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
    }
    timer.cancel();  
    scanner.close();
}

```

### Snipped from Example Game play 

X 2 X <br> 
X 0 X <br> 
X X X <br> 
Not a match. Try again.<br> 
X X X <br> 
X X X <br> 
X X X <br> 
Enter the coordinates of the first tile to flip (row col):<br> 
Time's up! Game over.<br> 
X X X <br> 
X X X <br> 
X X X <br> 

-> In future changes : Show timer after every turn 

## Show timer after every turn
