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
TicTacToe extends JFrame (a window) and implements ActionListener (to handle button clicks)
array of 9 buttons <- 3x3 grid
track turn using boolean (Is it Xs turn ?)
initialize  window title, size, and layout 
initialize all the buttons by looping , set up as empty at first , set up font (Arial bold ) , add an action listener to handle clicks and add button to the window
make window visible

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

called when a button is clicked
finds which button was clicked by comparing the source of the event with the buttons array.



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




```java
```
