# Operators 

## Relational Operators
<img src="https://miro.medium.com/v2/resize:fit:1292/format:webp/1*GO_QwcYGTBY9KoJduqhbZw.png" alt=" Relational Operators Table" width="500" />

## Logical Operators
<img src="https://miro.medium.com/v2/resize:fit:1382/format:webp/1*yUDkLkzKRNZdiw-OZoSLFw.png" alt="Logical Operators Table" width="500" />

## Arithmetic Operators
<img src="https://miro.medium.com/v2/resize:fit:1204/format:webp/1*8ZokTsNaV9n59uwUOO5J0Q.jpeg" alt="Arithmetic Operators Table" width="500" />

## Assignment Operators
<img src="https://beginnersbook.com/wp-content/uploads/2022/09/Assignment_Operators_List.jpg" alt="Assignment Operators Table" width="500" />

## Increment and Decrement Operators
<img src="https://i.ytimg.com/vi/BuMbVAN0_-8/maxresdefault.jpg" alt="Increment and Decrement Operators" width="500" />

## Conditional (Ternary) Operator
<img src="https://media.geeksforgeeks.org/wp-content/uploads/20191122171059/Conditional-or-Ternary-Operator-__-in-Java.jpg" alt="Conditional or Ternary Operator in Java" width="500" />
 
##  Operators
<img src="https://www.startertutorials.com/corejava/wp-content/uploads/2014/10/Bitwise-operators.jpg" alt="Bitwise Operators Table" width="500" />

## Operators :  some exercises 

### 01 
find the maximum of two numbers using the ternary operator

solution by me : 

``` java
public class TernaryOperator {
    public static void main(String[] args) {
        int a = 3;
        int b = 6;

        int max = (a > b) ? a : b;
        System.out.println("The maximum number is " + max);
    }
}
```

### 02 
this one was found here : https://docs.oracle.com/javase/tutorial/java/nutsandbolts/QandE/questions_operators.html

In the following program, explain why the value "6" is printed twice in a row:
```
class PrePostDemo {
    public static void main(String[] args){
        int i = 3;
        i++;
        System.out.println(i);    
        ++i;                     
        System.out.println(i);    
        System.out.println(++i);  
        System.out.println(i++);  
        System.out.println(i);    
    }
}
```
explanation : i initialized as 3 -> i++ -> i=4 -> ++i -> i=5 -> System.out.println(++i) -> i=6 with i being incremented before the print  -> System.out.println(i++) -> i = 6 , as in printed value is 6 as it is incremented by one after printing 

### 03 
this one was found here : https://redi-school.github.io/intro-java/lesson3/exercise2.html

Given the following program defining two variables
```
class Main {
    public static void main(String[] args) {
        int a = 16;
        int b = 8;
        
    }
}
```
How would you : 
<ul>
 <li> implement a check (using boolean operators and number operators) that tells you if both a and b are greater than 10 (hint: implement and then rerun program with different values assigned to a and b to check that it is correct)</li>
 <li>implement a check (using boolean operators and number operators) that tells you if either a or b are smaller than 10 (hint: implement and then rerun program with different values assigned to a and b to check that it is correct)</li>
 <li>implement a check (using boolean operators and number operators) that tells you if a is at least two times bigger than b (hint: implement and then rerun program with different values assigned to a and b to check that it is correct), i.e. for a = 16 and b = 8 your check should return true (as a is two times b)</li>
</ul>

``` java
// Check if both a and b are greater than 10
boolean Check = (a > 10) && (b > 10);
System.out.println(" Are both a and b are greater than 10 ?: " + Check);
```
``` java
// Check if either a or b is smaller than 10
boolean Check = (a < 10) || (b < 10);
System.out.println("Is a or b smaller than 10 ?: " + Check);
```
``` java
// Check if a is at least two times bigger than b
boolean Check = a >= 2 * b;
System.out.println("Is a at least two times bigger than b ?: " + Check);
```
