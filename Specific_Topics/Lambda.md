# Lambda 

short block of code that takes in parameters and returns a value <br>
write less code <br>
easier to read<br>
<br>
 <br>
Syntax : <br>
(parameters) -> expression<br>
(parameter1, parameter2) -> expression<br>
(parameters) -> { statements; }<br>
<br>
Example :<br>

```java
class Greeting {
    void sayHello() {
        System.out.println("Hello from Greeting!");
    }
}
@FunctionalInterface //interfaces with a single abstract method
interface GreetingFunction {
    void sayHello();
}
GreetingFunction greetingFunction = () -> {
    System.out.println("Hello from lambda expression!");
};

greetingFunction.sayHello();
```
-> lambda expression here : () -> { System.out.println("Hello from lambda expression!"); }<br>
    -> () means that the method takes no parameters<br>
    -> -> is the lambda operator<br>
    -> { System.out.println("Hello from lambda expression!"); } is the body of the lambda expression<br>

## Exercise 
source : https://www.w3resource.com/java-exercises/lambda/index.php<br>
Write a Java program to implement a lambda expression to check if a list of strings are all uppercase or all lowercase or mixedcase<br>
<br>
Create a List of Strings
```java
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class LambdaCaseCheck {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("HELLO", "world" , "Hi" );

```
Lambda Expressions to check if each string in the list is: all uppercase , all lowercase , mixed case

```java
        //  functional interface in the java.util.function package , used for evaluating conditions
        Predicate<String> isUpperCase = s -> s.equals(s.toUpperCase());

        Predicate<String> isLowerCase = s -> s.equals(s.toLowerCase());
```
Check each string in the list and print the result

```java
for (String str : strings) {
            if (isUpperCase.test(str)) {
                System.out.println(str + " is all uppercase");
            } else if (isLowerCase.test(str)) {
                System.out.println(str + " is all lowercase");
            } else {
                System.out.println(str + " is mixed case");
            }
```
full code : 

```java
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class LambdaCaseCheck {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("HELLO", "world", "Hi");

        Predicate<String> isUpperCase = s -> s.equals(s.toUpperCase());
        Predicate<String> isLowerCase = s -> s.equals(s.toLowerCase());

        for (String str : strings) {
            if (isUpperCase.test(str)) {
                System.out.println(str + " is all uppercase");
            } else if (isLowerCase.test(str)) {
                System.out.println(str + " is all lowercase");
            } else {
                System.out.println(str + " is mixed case");
            }
        }
    }
}

```
