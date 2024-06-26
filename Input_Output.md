# Input/Output 

## Output

- System.out.print : Prints text to the console
- System.out.println : Prints text to the console with a newline at the end

e.g.: 
```java
System.out.print("Hello");
System.out.println("World!");
System.out.println("Hello World");
// output : HelloWorld!
// output : Hello World
```
## Input 

- import scanner class from java.util
- Create an instance of Scanner , by putting in System.in into the constructor
- Use Scanner methods

```java
// import scanner
import java.util.Scanner;
// Create a Scanner object
Scanner scanner = new Scanner(System.in);
// Read line of text as String
String name = scanner.nextLine();
// Read an integer
int age = scanner.nextInt();
// Close scanner
scanner.close();
```

## Input/Output : some exercises
### 01
reads an integer from the user using nextInt() method of the Scanner object . and checks if it is even or odd using the modulus operator ( If num % 2 == 0, the number is even) 
Outputs result using System.out.println

```java
import java.util.Scanner;
public class EvenOrOddChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 

        System.out.print("Please enter an integer: "); 
        int num = scanner.nextInt(); 

        if (num % 2 == 0) {
            System.out.println(num + " is even."); 
        } else {
            System.out.println(num + " is odd."); 
        }

        scanner.close(); 
    }
}

}

```

### 02
reads a student's points (US Style) and prints the corresponding grade
reads an integer from the user using nextInt() method of the Scanner object.
Uses several if-else statements to assign a grade based on the score and prints right grade using System.out.print to return it  
```java
import java.util.Scanner;

import java.util.Scanner;

public class GradeEvaluation {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter your score (0-100): ");
        int score = scanner.nextInt();
        char grade;

        if (score >= 90 && score <= 100) {
            grade = 'A';
        } else if (score >= 80 && score < 90) {
            grade = 'B';
        } else if (score >= 70 && score < 80) {
            grade = 'C';
        } else if (score >= 60 && score < 70) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        System.out.println("Your grade is: " + grade);
        scanner.close();
    }
}

```
## Other ways 

### BufferedReader
useful for when input is big ;  reads input as strings

required classes to import : 
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
```
create a BufferedReader that uses an InputStreamReader object that reads bytes from System.in (reads from console) and converts them into characters.
```java
BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
```
reads text from console waiting until user presses enter (readLine)
```java
String name = reader.readLine();
```
BufferedReader makes most sense for reading in files , as those often are bigger in size

create a BufferedReader that uses a  FileReader object that read from the file located at the given location 
```java
BufferedReader reader = 
  new BufferedReader(new FileReader("src/main/resources/input.txt"));
```

### System.err

for error messages 
```java
System.err.println("Error!");
```
