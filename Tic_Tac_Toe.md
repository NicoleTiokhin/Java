# Tic Tac Toe Game 

use  Java Swing to implement the GUI (graphical user interface) <br>
take into account : tictactoe always has a 3x3 grid <br>
use buttons as cells in the game grid <br>
Only one class : Tic Tac toe class <br>

## Imports 
Import everything from the Swing library (buttons, labels, text fields,etc.)(create buttons and the frame <- JFrame ,  JButton <br>
Import everything from the AWT library ( GridLayout to arrange the Layout )  <br>
Import the ActionEvent class (handle button clicks,etc.) (When an action event occurs, an ActionEvent object is created and passed to the relevant event listener method )   <br>
Import the ActionListener interface <br>


## Implementation : set up initial board 
- TicTacToe is a subclass of JFrame (create Window) <br>
- implements the ActionListener interface to handle button clicks using the ActionPerformed method <br>
- create an array of 9 buttons for the 9 cells of the game grid <br>
- isXTurn boolean variable for whose turn it is -> True when Player with X Turn , False otherwise <br>
- initialize buttons and board details like size <br>
  
  <br>

```java
public class TicTacToe extends JFrame implements ActionListener {
    private JButton[] buttons = new JButton[9];
    private boolean isXTurn = true;

    public TicTacToe() {
        setTitle("Tic Tac Toe Game"); // title of window 
        setSize(500, 500);// size of the window : 500x500 pixels
        setLayout(new GridLayout(3, 3)); //  3 rows and 3 columns
    // initialize empty buttons wirh bold Arial font and add an action listener to handle button clicks and then add buttons to the window
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton(""); 
            buttons[i].setFont(new Font("Arial", Font.BOLD, 60)); 
            buttons[i].addActionListener(this); 
            add(buttons[i]); 
        }
        setVisible(true); // make the window visible
    }

```


#### ActionPerformed Method
method is called when a button is clicked <br>
get the button that was clicked  <br>
while loop to find index of button by iterating the buttons array ; iterate through buttin array until either the button is found or index reaches 9  <br>
if condition to check if index is out of bounds or if text on clicked button not empty ergo sum cell already occupied , show a message and exit method <br>
if condition to set text on chosen button to either X or O depending on whose turn it is <br>
toggle turn <br>
call the checkForWinner() method to check if the game has a winner after this turn <br>
override , because actionPerformed is a method defined in the ActionListener interface <br>

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

    // Toggle turn
    isXTurn = !isXTurn;

    checkForWinner();
}

```

### checkForWinner Method
for loop to check rows by calling the checkRowCol() method <br>
if winner found (checkRowCol() true ) exits call announceWinner()  <br>
check if the buttons there have the same non-empty text <br>
else if all buttons are occupied print message and resets the board for a new game <br>

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
check if three buttons have the same non-empty text -> completed row  <br>
are b1 and b2 same and b2 and b3 and is b1 not empty ? -> return true <br>
```java
private boolean checkRowCol(int b1, int b2, int b3) {
    return buttons[b1].getText().equals(buttons[b2].getText()) &&
           buttons[b2].getText().equals(buttons[b3].getText()) &&
           !buttons[b1].getText().equals("");
}

```

### announceWinner
announces winner and resets game for a new game <br>
because isXTurn is toggled after each move, the current value of isXTurn is the next player, so the previous player is the winner. <br>
```java
private void announceWinner() {
    String winner = isXTurn ? "O" : "X"; 
    JOptionPane.showMessageDialog(this, "Player " + winner + " is the winner !");
    resetBoard();
}

```
### isBoardFull
checks if all the buttons are occupied  <br>
for loop through buttons array , if an empty button is encountered flase is returned    <br>
if true -> no winner -> draw   <br>

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

### resetBoard Method 
clear the game and reset the turn player X  <br>
for loop through  buttons array and set every button´s text to an empty string   <br>
```java
private void resetBoard() {
    for (JButton button : buttons) {
        button.setText("");
    }
    isXTurn = true;
}

```


### Main method 
Create an instance of TicTacToe  <br>
```java
    public static void main(String[] args) {
        new TicTacToe();
    }
```




