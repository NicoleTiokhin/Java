# Tic Tac Toe Game 

use  Java Swing to implement the GUI (graphical user interface)
take into account : tictactoe always has a 3x3 grid 
use buttons as cells in the game grid 
Only one class : Tic Tac toe class

## Imports 
Import everything from the Swing library (buttons, labels, text fields,etc.)(create buttons and the frame <- JFrame ,  JButton <br>
Import everything from the AWT library ( GridLayout to arrange the Layout )  <br>
Import the ActionEvent class (handle button clicks,etc.) (When an action event occurs, an ActionEvent object is created and passed to the relevant event listener method )   <br>
Import the ActionListener interface <br>


## Implementation : set up initial board 
- TicTacToe is a subclass of JFrame (create Window)
- implements the ActionListener interface to handle button clicks using the ActionPerformed method
- create an array of 9 buttons for the 9 cells of the game grid
- isXTurn boolean variable for whose turn it is -> True when Player with X Turn , False otherwise
- initialize buttons and board details like size
  
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
    }

```


#### ActionPerformed Method
method is called when a button is clicked
get the buttin that was clicked 
while loop to find index of button by iterating the buttons array ; iterate through buttin array until either the button is found or index reaches 9 
if condition to check if index is out of bounds or if text on clicked button not empty ergo sum cell already occupied , show a message and exit method 
if condition to set text on chosen button to either X or O depending on whose turn it is 
toggle turn 
call the checkForWinner() method to check if the game has a winner after this turn 
override , because actionPerformed is a method defined in the ActionListener interface 

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
for loop to check rows by calling the checkRowCol() method
if winner found (checkRowCol() true ) exits call announceWinner() (felt a bit fancy when namin the methods here ;) ) 


```java
```



```java
```
