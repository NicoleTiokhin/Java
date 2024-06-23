# Concepts of Object-Oriented Programming 

## Classes and Objects

Class : blueprint for creating objects
```java
public class Car {
    // Fields (attributes)
    private String model;
    private int year;

    // Constructor
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
public class Main {
    public static void main(String[] args) {
        // Creating an object of the Car class
        Car myCar = new Car("Toyota", 2021);
        myCar.displayInfo(); // Output: Model: Toyota, Year: 2021
    }
}
```

## Inheritance 

One class (child class) inherits (e.g.:) methods of another class (parent class)

```java
// Parent class
public class Vehicle {
    private String brand;

    public Vehicle(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }
}

// Child class
public class Car extends Vehicle {
    private int year;

    public Car(String brand, int year) {
        super(brand); // Call parent constructor
        this.year = year;
    }

    public void displayInfo() {
        System.out.println("Brand: " + getBrand() + ", Year: " + year);
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        Car myCar = new Car("GenericCarBrand", 2024);
        myCar.honk(); // Inherited method
        myCar.displayInfo(); // Output: Brand: GenericCarBrand, Year: 2021
    }
}
```
## Encapsulation 

wrap up variables and methods into a single unit class


## Polymorphism 

= many forms 
allows objects to be treated as instances of their parent class instead of than their actual class

Compile-time Polymorphism (Method Overloading): Same method name but different parameters.
Runtime Polymorphism (Method Overriding): A child class provides a specific implementation of a method that is already  
defined in its parent class.

## Abstraction
hide the detailed implementation details and show only the essential features of the object using  abstract classes


