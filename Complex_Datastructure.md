# Complex Datasets 

## Arrays

holds values of same type <br>
length of an array is established when the array is created and cannot be changed <br>
elements can be accessed using their index, starting from 0 <br>

Example : 

```java
int[] numbers = new int[6]; // Created an array of 6 integers
numbers[0] = 1; // Set integer at index 0 to 1 
numbers[1] = 2;
```

## ArrayLists

an array whose size can grow

Example : 

```java
import java.util.ArrayList; // import the ArrayList
ArrayList<Integer> list = new ArrayList<>(); // declared an array list named list with integers
list.add(1); // append integer 1 to ArrayList 
list.add(2);

```

## LinkedLists

each element points to the next element -> sequence
can change in size 

Types : 
Singly LinkedList: every element points to the next element
Doubly LinkedList: every element points to the next and previous elements

Example :

```java
class Example {
    int data;          //  data type 
    Example next;      // Pointer to the next element in the list

    Example(int data) {  // Constructor to initialize the element with data
        this.data = data;
        this.next = null;  //  no next element
    }
}
public class Main {
    public static void main(String[] args) {
        // first element (so called head) , value of 1
        Example head = new Example(1);

        // Create the next node , value of 2
        head.next = new Example(2);

        // Create the next node , value of 3
        head.next.next = new Example(3);

        // Print the elements in the list
        Example current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
// output : 1 2 3 
        }
    }
}

```


## HashSets

no duplicate elements , will ignore if duplicate added
unordered : order of  addition is not equal to order of iteration
```java
import java.util.HashSet; //import HashSet

HashSet<String> hash = new HashSet<>();  // create a HashSet named hash with string elements
hash.add("Hello"); // add 
hash.add("World");

hash.contains("Hello"); // true
hash.remove("World"); // removes 

```

## HashMaps 

maps keys to values

similar to hashsets no duplicate keys

```java
// import HashMap
import java.util.HashMap;

HashMap<String, Integer> map = new HashMap<>(); // create a HashMap named map with keys of type String and values of type Integer
map.put("Nicole", 18); // Add element with key "Nicole" and value 18 

int age = map.get("Nicole"); // 18 
map.remove("Nicole"); // remove key and value pair 


```

## Queues

First-In-First-Out (FIFO) principle

```java
import java.util.LinkedList;
import java.util.Queue;

Queue<Integer> queue = new LinkedList<>(); // Create a new Queue storing Integer elements ; let queue use LinkedList methods for queues
queue.add(10);// add to end of queue
queue.add(20);

queue.remove();  // remove first element added
queue.peek(); // show first element 

```

## Stacks 

Last-In-First-Out (LIFO) principle

```java
import java.util.Stack;

Stack<Integer> stack = new Stack<>(); // create a stack named stack that will store integers

stack.push(1); // add 1 onto stack 
stack.push(2); // add 2 onto stack
// 2 is on top of 1

stack.pop(); // remove top element of stack ( here : 2)
stack.peek(); // get top element of stack

```


## Exercise 1 
found on : https://www.w3resource.com/java-exercises/collection/index.php

```java
```


## Exercise 2 

```java
```
