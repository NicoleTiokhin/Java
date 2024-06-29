# Polymorphism

Theoretical part of what that is and how it works in Concepts_Object-Oriented_Programming

found on : https://www.w3resource.com/java-exercises/index-polymorphism.php

## Exercise 1 
Java program to create a base class Animal (Animal Family) with a method called Sound().<br>
two subclasses Bird and Cat.<br>
Override the Sound() method in each subclass to make a specific sound for each animal.<br>

```java
// Base class Animal 
class Animal {
    // Method sound 
    public void sound() {
        System.out.println("*Animal sounds*");
    }
}

// Subclass Bird
class Bird extends Animal {
    // Override the sound() method for Bird-specific sounds
    @Override
    public void sound() {
        System.out.println("chirp chirp");
    }
}

// Subclass Cat
class Cat extends Animal {
    // Override the sound() method for Cat-specific sounds
    @Override
    public void sound() {
        System.out.println("meow meow purr purr");
    }
}

// Main class 
public class AnimalFamily {
    public static void main(String[] args) {
        // Create instances of Bird and Cat
        Animal myBird = new Bird();
        Animal myCat = new Cat();

        // Call the sound() method on each instance
        myBird.sound(); //  output: chirp chirp
        myCat.sound();  // output : meow meow purr purr
    }
}

```

## Exercise 2
Write a Java program to create a base class Shape with methods draw() and calculateArea(). <br>
Create two subclasses Circle and Cylinder. <br>
Override the draw() method in each subclass to draw the respective shape.<br>
In addition, override the calculateArea() method in the Cylinder subclass to calculate and return the total surface area of the cylinder <br>

```java
// Base class Shape
class Shape {
    // Method draw
    public void draw() {
        System.out.println("Let´s draw a shape!");
    }

    // Method calculate area
    public double calculateArea() {
        return 0; 
    }
}

// Subclass Circle
class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    // Override draw() method to draw Circle
    @Override
    public void draw() {
        System.out.println("A Circle has been drawn ");
    }

    // Override calculateArea() method to calculate the area 
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

// Subclass Cylinder
class Cylinder extends Shape {
    private double radius;
    private double height;

    public Cylinder(double radius, double height) {
        this.radius = radius;
        this.height = height;
    }

    // Override draw() method to draw Cylinder
    @Override
    public void draw() {
        System.out.println("A Cylinder has been drawn ");
    }

    // Overriding calculateArea() method to calculate area 
    @Override
    public double calculateArea() {
        return 2 * Math.PI * radius * (radius + height);
    }
}

// Main class 
public class Main {
    public static void main(String[] args) {
        // Create instances of Circle and Cylinder
        Shape myCircle = new Circle(10.0);
        Shape myCylinder = new Cylinder(4.0, 10.0);

        // Draw shapes
        myCircle.draw(); //output: A Circle has been drawn
        myCylinder.draw(); // output: A Cylinder has been drawn

        // Calculate and print area of the shapes
        System.out.println("Area of Circle: " + myCircle.calculateArea()); 
        System.out.println("Area of Cylinder: " + myCylinder.calculateArea()); 
    }
}

```

## Exercise 3
Write a Java program to create a class Vehicle with a method called speedUp(). <br>
Create two subclasses Car and Bicycle. <br>
Override the speedUp() method in each subclass to increase the vehicle's speed differently. <br>

```java
// Base class Vehicle 
class Vehicle {
    protected int speed;

    public Vehicle() {
        this.speed = 0; 
    }

    // Method speedUp
    public void speedUp() {
        System.out.println("More speed!");
    }
}

// Subclass Car
class Car extends Vehicle {
    // Override the speedUp() method 
    @Override
    public void speedUp() {
        speed += 10; 
        System.out.println("Car speed increased by 10");
    }
}

// Subclass Scooter
class Scooter extends Vehicle {
    // Override the speedUp() method
    @Override
    public void speedUp() {
        speed += 5; 
        System.out.println("Scooter speed increased by 5");
    }
}

// Main class 
public class Main {
    public static void main(String[] args) {
        // Create instances of Car and Bicycle
        Vehicle carCar = new Car();
        Vehicle scooterScooter = new Scooter();

        // Increase speed of the Car
        carCar.speedUp(); // output : Car speed increased by 10

        // Increase speed of the Scooter
        scooterScooter.speedUp(); // output : Scooter speed increased by 5
    }
}

```

## Exercise 4
Write a Java program to create a base class Sports with a method called play().  <br>
Create three subclasses: Football, Basketball, and Rugby.<br>
Override the play() method in each subclass to play a specific statement for each sport.<br>

```java
// Base class Sports 
class Sports {
    // method play
    public void play() {
        System.out.println("Let´s play!");
    }
}

// Subclass Football
class Football extends Sports {
    // Override play method 
    @Override
    public void play() {
        System.out.println("Let´s play Football !");
    }
}

// Subclass Basketball
class Basketball extends Sports {
    // Override play method
    @Override
    public void play() {
        System.out.println("Let´s play Basketball !");
    }
}

// Subclass Rugby
class Rugby extends Sports {
    // Override play method
    @Override
    public void play() {
        System.out.println("Let´s play Rugby !");
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        // Create instances of Football, Basketball, and Rugby
        Sports football = new Football();
        Sports basketball = new Basketball();
        Sports rugby = new Rugby();

        // Playing each sport
        football.play();     // output: Let´s play Football !
        basketball.play();   // output: Let´s play Basketball !
        rugby.play();        // output: Let´s play Rugby !
    }
}

```

