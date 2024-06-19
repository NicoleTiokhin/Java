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
reads an integer from the user and checks if it is even or odd
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

