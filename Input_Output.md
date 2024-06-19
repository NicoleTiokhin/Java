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

