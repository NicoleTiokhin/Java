# Concepts of Object-Oriented Programming 

uses objects and classes to structure software -> good for large, complex, and regularely updated software

Concepts of  Object-Oriented Programming  are : 
<ul>
    <li>Classes and Objects</li>
    <li>Inheritance</li>
    <li>Polymorphism</li>
    <li>Encapsulation</li>
    <li>Abstraction</li>
</ul>

## Classes and Objects

Class :
blueprint for creating objects <br>
represents the set of properties (attributes) or methods (functions)(define object behavior) that are common to all objects of one type <br>
```java
public class Car {
    // Properties (attributes)
    private String model;
    private int year;

    // Constructor :  special method, initializes the model and year properties of the Car object when it is created
    public Car(String model, int year) {
        this.model = model;
        this.year = year;
    }

    // Method 
    public void displayInfo() {
        System.out.println("Model: " + model + ", Year: " + year);
    }
}
```

Object :  Instance of a class . Memory is only allocated when an object of that class is created.

```java
// Create objects
public class Main {
    public static void main(String[] args) {
        // Creating an object of the Car class : new keyword and then class constructor.
        Car myCar = new Car("Toyota", 2021);
       // Access object properties
        myCar.displayInfo(); // Output: Model: Toyota, Year: 2021
    }
}
```

## Inheritance 

One class (child class) inherits (e.g.:) methods of another class (parent class) <br>
in other wordsone class is allowed to inherit the features (fields and methods) of another class <br>
->  code reusability <br>
Superclass : class whose features are inherited (also called the base or parent class) <br>
Subclass :  The class that inherits from another class (also called the derived, extended, or child class). It can also have additional fields and methods <br>

example using Car class as a Superclass 
ElectricCar is a Subclass in this case 
```java
public class ElectricCar extends Car { // extends keyword
    // Additional property for ElectricCar
    private int batteryLife;

    // Constructor
    public ElectricCar(String model, int year, int batteryLife) {
        super(model, year); // Call the constructor of the superclass (Car)
        this.batteryLife = batteryLife;
    }

    // Method
    public void displayInfo() {
        super.displayInfo(); // Call the displayInfo method of the superclass (Car)
        System.out.println("Battery Life: " + batteryLife + " hours");
    }
}

```
## Encapsulation 

concept of wrapping data (variables) and methods (functions) into a single unit called a class <br>
Variables in a class are declared as private to restrict direct access. <br>
->prevent data from being accessed directly from outside the class <br>
Public getter and setter methods are provided to allow controlled access to the private variables <br>
```
public class Car {
    // Private variables 
    private String model;
    private int year;

    // Constructor to initialize the variables
    public Car(String model, int year) {
        this.model = model;
        this.year = year;
    }

    // Public getter method to access the private variable model
    public String getModel() {
        return model;
    }

    // Public setter method to change the private variable model
    public void setModel(String model) {
        this.model = model;
    }

    // Public getter method to access the private variable year
    public int getYear() {
        return year;
    }

    // Public setter method to change the private variable year
    public void setYear(int year) {
        this.year = year;
    }

    // Method to display car information
    public void displayInfo() {
        System.out.println("Model: " + model + ", Year: " + year);
    }
}

public class Main {
    public static void main(String[] args) {
        // Create a Car object
        Car myCar = new Car("Toyota Camry", 2020);
        
        // Access and modify private variables using public methods
        System.out.println("Model: " + myCar.getModel());
        myCar.setModel("Honda Accord");
        System.out.println("Updated Model: " + myCar.getModel());

        myCar.displayInfo(); // Display car information
    }
}

```

## Polymorphism 

= many forms -> ability of a single function, method, or object to take on multiple forms <br>
allows objects to be treated as instances of their parent class instead of than their actual class <br>
->  allows methods to perform different tasks based on the object that invokes them <br>

Compile-time Polymorphism (Method Overloading): Same method name but different parameters. <br>
Runtime Polymorphism (Method Overriding): A child class provides a specific implementation of a method that is already <br>  
defined in its parent class. <br>
Example for Method Overloading :
```java
public class Car {
    // Method to display basic car info
    public void displayInfo(String model) {
        System.out.println("Model: " + model);
    }

    // Overloaded method to display detailed car info
    public void displayInfo(String model, int year) {
        System.out.println("Model: " + model + ", Year: " + year);
    }
}

public class Main {
    public static void main(String[] args) {
        Car myCar = new Car();
        
        // Call the overloaded methods
        myCar.displayInfo("Toyota Camry"); // Calls the first method
        myCar.displayInfo("Honda Accord", 2020); // Calls the second method
    }
}

```
Example for Method Overriding
```java
class Car {
    // Method to display car info
    public void displayInfo() {
        System.out.println("This is a car.");
    }
}

class ElectricCar extends Car {
    // Overridden method to display electric car info ; provide a specific implementation of the displayInfo method
    @Override
    public void displayInfo() {
        System.out.println("This is an electric car.");
    }
}

public class Main {
    public static void main(String[] args) {
        Car myCar = new Car();
        Car myElectricCar = new ElectricCar();
        
        // Call the overridden method
        myCar.displayInfo(); // Calls the method in Car class
        myElectricCar.displayInfo(); // Calls the overridden method in ElectricCar class
    }
}

```
## Abstraction
concept of hiding the complex implementation details and showing only the essential features of an object <br>
->  focus on what an object does rather than how it does it <br>
in Java done using : abstract classes and interfaces <br>

Example for abstract classes 

```java
abstract class Car {
    // Abstract method (no implementation) ; Subclasses of Car must provide the implementation for this method
    public abstract void start();

    // Concrete method ; All subclasses of Car can use this method as it is or override it if needed
    public void displayInfo() {
        System.out.println("This is a car.");
    }
}

class ElectricCar extends Car {
    // Implement the abstract method ; A subclass that extends an abstract class must implement all the abstract methods of the abstract class
    @Override
    public void start() {
        System.out.println("Electric car is starting...");
    }
}

public class Main {
    public static void main(String[] args) {
        Car myCar = new ElectricCar();
        myCar.start(); // Calls the implemented method in ElectricCar
        myCar.displayInfo(); // Calls the concrete method in Car
    }
}

```
Exmaple for of Abstraction with Interfaces

```Java
interface Car {
    // Abstract method (no implementation) ; must be implemented by any class that implements the Car interface
    void start();
    void displayInfo();
}

class ElectricCar implements Car {
    // Implement the abstract methods / must provide implementations for all the abstract methods defined in the interface
    @Override
    public void start() {
        System.out.println("Electric car is starting...");
    }

    @Override
    public void displayInfo() {
        System.out.println("This is an electric car.");
    }
}

public class Main {
    public static void main(String[] args) {
        Car myCar = new ElectricCar();
        myCar.start(); // Calls the implemented method in ElectricCar
        myCar.displayInfo(); // Calls the implemented method in ElectricCar
    }
}
```
Differences Between Abstract Class and Interface : <br>
<ul>
    <li>Abstract Cass : like a blueprint for other classes : Interface : contract that classes can sign up to </li>
    <li>Abstract Class: Can have methods and concrete methods</li>
    <li>Interface: Can only have constants(a variable whose value cannot be changed once it has been assigned)</li>
    <li>Abstract Class: A class can inherit only one abstract class ; Interface: A class can implement multiple interfaces</li>
</ul>
Use cases : 
Abstract class : reating a hierarchy of related classes
Interface :  across unrelated classes

