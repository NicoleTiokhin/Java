# Memory  Usage 

managed by the Java Virtual Machine (JVM)

Heap Memory: 
<ul>
  <li>allocate memory for objects and class instances</li>
  <li>managed automatically by the Garbage Collector, which cleans up unused objects to free memory</li>
  <li>dynamic, long-term storage</li>
  <li>every time a new object is created (new keyword) , memory is allocated</li>
  <li>Garbage collection to automatically free up memory in the heap that is no longer needed</li>
</ul>


Stack Memory: 
<ul>
  <li>short-term storage for local variables defined within the method, method parameters,where to return after the method completes</li>
  <li>smaller compared to the heap</li>
  <li> Last-In-First-Out (LIFO) </li>
  <li>when a method finishes executing, its stack frame is popped off the stack</li>
  <li> usually fixed and determined at the start of the program</li>
  <li>If a program uses too much stack memory:StackOverflowError</li>
</ul>

```java
public class Addition {
    public static void main(String[] args) {
        int result = add(2, 2); // Stored in stack
        System.out.println("Result: " + result);
    }

    public static int add(int a, int b) {
        int sum = a + b; // Stored in stack
        return sum;
// Once add completes, its stack frame is removed

    }
}

```

```java
public class HeapExample {
    public static void main(String[] args) {
       // references person1 and person2 stored in stack frame
        Person person1 = new Person("Nicole", 18); // Object in heap
        Person person2 = new Person("LegalAgeUS", 21); // Object in heap
        // accessing the person1 and person2 objects in the heap
        person1.printInfo();
        person2.printInfo();
    }
}

class Person {
    String name; // Reference to String object in heap
    int age; // Stored in the heap
    // constructor parameters name and age stored in stack frame
    // this.name and this.age assigned in the heap
    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

}

```
