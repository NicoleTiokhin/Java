# Complex Datastructure 

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

## Graphs 
In a directed graph, edges have a direction <br>
In an undirected graph, edges don't have a direction <br>
In a weighted graph, edges have weights  <br>
In an unweighted graph, edges don't have weights <br>

```java
// directed graph
import java.util.*;

class DirectedGraph {
    private Map<String, List<String>> adjVertices;

    DirectedGraph() {
        adjVertices = new HashMap<>();
    }
    // add a vertex to the graph
    void addVertex(String label) {
        adjVertices.putIfAbsent(label, new ArrayList<>());
    }

    // create a directed edge from A to B
    void addEdge(String source, String destination) {
        adjVertices.get(source).add(destination);
    }
    // the list of adjacent vertices for the given vertex
    List<String> getAdjVertices(String label) {
        return adjVertices.get(label);
    }

    public static void main(String[] args) {
        DirectedGraph graph = new DirectedGraph();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        // Adding directed edges
        // A -> B
        graph.addEdge("A", "B");
        // A -> C
        graph.addEdge("A", "C");
        // B -> C
        graph.addEdge("B", "C");

    }
}
```


```java
// Undirected Graph
import java.util.*;

class UndirectedGraph {
    private Map<String, List<String>> adjVertices;

    UndirectedGraph() {
        adjVertices = new HashMap<>();
    }
   // add a new vertex with an empty list of adjacent vertices
    void addVertex(String label) {
        adjVertices.putIfAbsent(label, new ArrayList<>());
    }

    // add an undirected edge between source and destination
    void addEdge(String source, String destination) {
        adjVertices.get(source).add(destination);
        adjVertices.get(destination).add(source);
    }
    // return the list of adjacent vertices for the given vertex
    List<String> getAdjVertices(String label) {
        return adjVertices.get(label);
    }

    public static void main(String[] args) {
        UndirectedGraph graph = new UndirectedGraph();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        // Adding undirected edges
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "C");

    }
}

```

```java
//Weighted Graph
import java.util.*;

class WeightedGraph {
    private Map<String, List<Edge>> adjVertices;
    //edge with a destination and weight
    static class Edge {
        String destination;
        int weight;

        Edge(String destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return destination + "(" + weight + ")";
        }
    }

    WeightedGraph() {
        adjVertices = new HashMap<>();
    }
   //add a new vertex with an empty list of adjacent edges
    void addVertex(String label) {
        adjVertices.putIfAbsent(label, new ArrayList<>());
    }

    // add a weighted edge from source to destination
    void addEdge(String source, String destination, int weight) {
        adjVertices.get(source).add(new Edge(destination, weight));
    }

    List<Edge> getAdjVertices(String label) {
        return adjVertices.get(label);
    }

    public static void main(String[] args) {
        WeightedGraph graph = new WeightedGraph();
        graph.addVertex("A"); 
        graph.addVertex("B"); 
        graph.addVertex("C");

        // Adding weighted edges
        graph.addEdge("A", "B", 5); // A -> B (weight 5)
        graph.addEdge("A", "C", 10); // A -> C (weight 10)
        graph.addEdge("B", "C", 3); // B -> C (weight 3)

    }
}

```

```java
// Unweighted Graph
import java.util.*;

class UnweightedGraph {
    private Map<String, List<String>> adjVertices;

    UnweightedGraph() {
        adjVertices = new HashMap<>();
    }
    // add a new vertex with an empty list of adjacent vertices
    void addVertex(String label) {
        adjVertices.putIfAbsent(label, new ArrayList<>());
    }

    // add an edge from source to destination
    void addEdge(String source, String destination) {
        adjVertices.get(source).add(destination);
        adjVertices.get(destination).add(source); // For undirected graph
    }

    List<String> getAdjVertices(String label) {
        return adjVertices.get(label);
    }

    public static void main(String[] args) {
        UnweightedGraph graph = new UnweightedGraph();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");

        // Adding edges
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "C");

    }
}

```

## Trees 
hierarchical data structure with a root node and child nodes -> parent-child relationship  <br>
Binary Tree: Each node has at most two children (left and right) <br>
Binary Search Tree (BST): A binary tree , where the left child contains only nodes with values less than the parent node, and the right child contains  only nodes with values greater than the parent node <br>

```java
// Binary Tree
class TreeNode {
    int value; // Value of the node
    TreeNode left, right; // Left and right children

    TreeNode(int value) {
        this.value = value;
        left = right = null;
    }
}

// Class to represent a binary tree
class BinaryTree {
    TreeNode root; // Root of the binary tree

    BinaryTree() {
        root = null;
    }

    // Method to insert a new node into binary tree
    void insert(int value) {
        root = insertRec(root, value);
    }

    // Recursive method to insert a new node
    TreeNode insertRec(TreeNode root, int value) {
        // If the tree is empty, create a new node
        if (root == null) {
            root = new TreeNode(value);
            return root;
        }

        // Otherwise, traverse down the tree to find the correct position
        if (value < root.value) {
            root.left = insertRec(root.left, value);
        } else if (value > root.value) {
            root.right = insertRec(root.right, value);
        }

        return root;
    }

    // Method to print the binary tree in-order
    void inOrder() {
        inOrderRec(root);
    }

    // Recursive method to print the binary tree in-order (left, root, right)
    void inOrderRec(TreeNode root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.print(root.value + " ");
            inOrderRec(root.right);
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        // Insert nodes
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);

    }
}

```


```java
//Binary Search Tree
class TreeNode {
    int value; // Value of the node
    TreeNode left, right; // Left and right children

    TreeNode(int value) {
        this.value = value;
        left = right = null;
    }
}

// Class to represent a binary search tree
class BinarySearchTree {
    TreeNode root; // Root of the binary search tree

    BinarySearchTree() {
        root = null;
    }

    // Method to insert a new node in the binary search tree
    void insert(int value) {
        root = insertRec(root, value);
    }

    // Recursive method to insert a new node
    TreeNode insertRec(TreeNode root, int value) {
        // If the tree is empty, create a new node
        if (root == null) {
            root = new TreeNode(value);
            return root;
        }

        // Otherwise, traverse down the tree to find the correct position
        if (value < root.value) {
            root.left = insertRec(root.left, value);
        } else if (value > root.value) {
            root.right = insertRec(root.right, value);
        }

        // Return the root node
        return root;
    }

    // Recursive method to print the binary search tree in-order
    void inOrderRec(TreeNode root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.print(root.value + " ");
            inOrderRec(root.right);
        }
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();

        // Inserting nodes
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);

          }
}

```

 
 


## Exercise 1 
found on : https://www.w3resource.com/java-exercises/collection/index.php
Write a Java program to append the specified element to the end of a hash set.
```java
import java.util.HashSet;

public class AppendToHashSet {

    public static void main(String[] args) {
        // create a new HashSet instance set for integers 
        HashSet<Integer> set = new HashSet<>();

        // add 1, 2, and 3 to the HashSet
        set.add(1);
        set.add(2);
        set.add(3);

        //print the HashSet before adding the new element
        System.out.println(set);

        // create an int variable newElement and assign it the value 4
        int newElement = 4;

        //  append newElement (4) to the HashSet
        set.add(newElement);

        //print the HashSet after adding the new element
        System.out.println(set);
    }
}

```


## Exercise 2 
found on : https://www.w3resource.com/java-exercises/collection/index.php
Write a Java program to get a shallow copy of a HashMap instance.
```java
import java.util.HashMap;

public class ShallowCopyExample {
    public static void main(String[] args) {
        // Original HashMap
        HashMap<Integer, String> originalMap = new HashMap<>();
        originalMap.put(1, "one");
        originalMap.put(2, "two");
        originalMap.put(3, "three");

        // Create a shallow copy of the original HashMap
        // Copy all the key-value pairs from originalMap to shallowCopyMap
        HashMap<String, String> shallowCopyMap = new HashMap<>(originalMap);

        // Display both maps 
        System.out.println("Original HashMap: " + originalMap);
        System.out.println("Shallow Copy HashMap: " + shallowCopyMap);

        // Modify the original HashMap
        originalMap.put("4", "Date");

        // Display both maps -> they look different 
        System.out.println("Original HashMap: " + originalMap);
        System.out.println("Shallow Copy HashMap: " + shallowCopyMap);
    }
}

```

## Exercise 3 
found on : https://www.w3resource.com/java-exercises/collection/index.php
Write a Java program to print all the elements of an ArrayList using the elements' position.
```java
import java.util.ArrayList;

public class PrintArrayListElements {
    public static void main(String[] args) {
        // Create an ArrayList and add  numbers
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);

        // iterate through the ArrayList
        // retrieve the element at the current index i
        for (int i = 0; i < numbers.size(); i++) {
            System.out.println("Element at index " + i + ": " + numbers.get(i));
        }
    }
}

```


## Exercise 4
found on : https://www.w3resource.com/java-exercises/collection/index.php


```java
import java.util.LinkedList;

public class ReplaceElementInLinkedList {
    public static void main(String[] args) {
        // Create a LinkedList 
        LinkedList<Integer> numbers = new LinkedList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);

        // Print the original LinkedList
        System.out.println("Original LinkedList: " + numbers);

        // Replace an element at a specific position
        // Replace element at index 2
        int indexToReplace = 2; 
        int newElement = 10;

        //  if the index is within the valid range
        //  replace the element at the specified index with the new element
        if (indexToReplace >= 0 && indexToReplace < numbers.size()) {
            numbers.set(indexToReplace, newElement);
            System.out.println("After replacing element at index " + indexToReplace + " with " + newElement + ": " + numbers);
        } else {
            System.out.println("Index " + indexToReplace + " is out of bounds for the LinkedList.");
        }
    }
}

```




