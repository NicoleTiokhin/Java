# Tic Tac Toe Game 

use  Java Swing to implement the GUI (graphical user interface)
take into account : tictactoe always has a 3x3 grid 
use buttons as cells in the game grid 

## Imports 
Import everything from the Swing library (buttons, labels, text fields,etc.)(create buttons and the frame <- JFrame ,  JButton <br>
Import everything from the AWT library ( GridLayout to arrange the Layout )  <br>
Import the ActionEvent class (handle button clicks,etc.) (When an action event occurs, an ActionEvent object is created and passed to the relevant event listener method )   <br>
Import the ActionListener interface <br>

## TicTacToe Class 
- TicTacToe is a subclass of JFrame (create Window)
- implements the ActionListener interface
- create an array of 9 buttons for the 9 cells of the game grid
- isXTurn boolean variable for whose turn it is -> True when Player with X Turn , False otherwise
  <br>
-   Construct Window : <br>
                        - Title of the window : Tic Tac Toe Game
                        - size of the window to 500x500 pixels
                        - sets the layout of the window to a 3x3 grid
                        - Initialize 9 buttons without text , size = 50 , Arial Font , Bold , 
                        - 

```java
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
            buttons[i].addActionListener(this); // Add an action listener to handle button clicks
            add(buttons[i]); // Add the button to the window
        }
    }

```
#### Main Method

- program starts execution
- GUI is created on the Event Dispatch Thread :
      - typically done like that when using Java Swing
      - handles all actions related to the user interface components
      - ensures cosistency
      - UI stays active for user interactions, with background tasks running
- Create an instance of the TicTacToe class
- Makes the game window visible

                 
```java
public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        TicTacToe game = new TicTacToe();
        game.setVisible(true);
    });
}

```

#### 

```java

```




```java
```



```java
```
