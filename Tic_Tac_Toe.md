# Tic Tac Toe Game 

use  Java Swing to implement the GUI (graphical user interface) <br>
take into account : tictactoe always has a 3x3 grid <br>
use buttons as cells in the game grid <br>
Only one class : Tic Tac toe class <br>

## Imports 

```java
import javax.swing.*; //JFrame, JButton for example
import java.awt.*; // layout and fonts
import java.awt.event.ActionEvent; //  handling events like button clicks
import java.awt.event.ActionListener; //  handling events like button clicks

```

## Tic Tac Toe class 
TicTacToe extends JFrame (a window) and implements ActionListener (to handle button clicks)<br>
array of 9 buttons <- 3x3 grid<br>
track turn using boolean (Is it Xs turn ?)<br>
initialize  window title, size, and layout <br>
initialize all the buttons by looping , set up as empty at first , set up font (Arial bold ) , add an action listener to handle clicks and add button to the window<br>
make window visible<br>

```java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        setVisible(true);
    }

```

### Action Listener Method

called when a button is clicked<br>
finds which button was clicked by comparing the source of the event with the buttons array.<br>
if the button is already occupied, show a message <br>
set the button text to "X" or "O" depending on whose turn it is <br>
toggle the turn and check for a winner<br>



```java
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

        isXTurn = !isXTurn;

        checkForWinner();
    }


```
###  checkForWinner Method
check all possible winning combinations 
announce winner 
check if it is a draw (board is full)

```java
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
```

### checkRowCol Method 
check if three buttons (by rules of tictactoe) have the same text and are not empty

```java
    private boolean checkRowCol(int b1, int b2, int b3) {
        return buttons[b1].getText().equals(buttons[b2].getText()) &&
               buttons[b2].getText().equals(buttons[b3].getText()) &&
               !buttons[b1].getText().equals("");
    }
```


### announceWinner Method 
winner is opposite of isXTurn because the turn is toggled after the move.

```java
 private void announceWinner() {
        String winner = isXTurn ? "O" : "X";
        JOptionPane.showMessageDialog(this, "Player " + winner + " wins!");
        resetBoard();
    }
```

### isBoardFull Method
checks if all buttons are filled , ergo sum there are no empty buttons 

```java
private boolean isBoardFull() {
    for (JButton button : buttons) {
        if (button.getText().equals("")) {
            return false;
        }
    }
    return true;
}

```







```java
```









```java
```







```java
```
