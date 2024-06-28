# Inheritance 
Theoretical part of what that is and how it works in Concepts_Object-Oriented_Programming

here I have two exercises specifically for Inheritance found on : https://www.w3resource.com/java-exercises/index-inheritance.php

## Exercise 1 
Write a Java program to create a class known as Person with methods called getFirstName() and getLastName(). <br>
Create a subclass called Employee that adds a new method named getEmployeeId() and overrides the getLastName() method to include the employee's job title

1. Create the Person Class (Superclass)
   
```java
public class Person {
    private String firstName;
    private String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getter method for firstName
    public String getFirstName() {
        return firstName;
    }

    // Getter method for lastName
    public String getLastName() {
        return lastName;
    }
}

```

2. Create the Employee class (subclass)
  ```java
public class Employee extends Person { // extends keyword 
    private String employeeId; // Subclasses own attributes
    private String jobTitle; // Subclasses own attributes

    public Employee(String firstName, String lastName, String employeeId, String jobTitle) {
        super(firstName, lastName); // Call the superclass (Person) constructor
        this.employeeId = employeeId;
        this.jobTitle = jobTitle;
    }

    // Method to get employeeId
    public String getEmployeeId() {
        return employeeId;
    }

    // Override the getLastName method to include the job title
    @Override
    public String getLastName() {
        return super.getLastName() + ", " + jobTitle;
    }
}

```

## Exercise 2 

Write a Java program that creates a class hierarchy for employees of a company. <br>
The base class should be Employee, with subclasses Manager, Developer, and Programmer.<br>
Each subclass should have properties such as name, address, salary, and job title.<br>
Implement methods for calculating bonuses, generating performance reports, and managing projects.

1. Employee Base Class <br>

```java
public class Employee {
    protected String name;
    protected String address;
    protected double salary;
    protected String jobTitle;

    public Employee(String name, String address, double salary, String jobTitle) {
        this.name = name;
        this.address = address;
        this.salary = salary;
        this.jobTitle = jobTitle;
    }

    // Method to calculate bonus
    public double calculateBonus() {
        return salary * 0.05; //  bonus is 5% of the salary
    }

    // Method to generate performance report
    public String generatePerformanceReport() {
        return "Performance report for " + name + ", Job Title: " + jobTitle;
    }

    // Method to manage projects (to be overridden by subclasses if needed)
    public void manageProjects() {
        System.out.println(name + " is managing projects.");
    }

    // Method to display employee information
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Address: " + address);
        System.out.println("Salary:" + salary);
        System.out.println("Job Title: " + jobTitle);
    }
}

```

2. Manager class
   
```java
   public class Manager extends Employee {
    public Manager(String name, String address, double salary, String jobTitle) {
        super(name, address, salary, jobTitle);
    }

    @Override
    public void manageProjects() {
        System.out.println(name + " is managing several projects.");
    }
}
```
3. Developer class

 ```java
public class Developer extends Employee {
    public Developer(String name, String address, double salary, String jobTitle) {
        super(name, address, salary, jobTitle);
    }

    @Override
    public void manageProjects() {
        System.out.println(name + " is managing software development projects.");
    }
}

```

4. Programmer class
   
```java
    public class Programmer extends Employee {
    public Programmer(String name, String address, double salary, String jobTitle) {
        super(name, address, salary, jobTitle);
    }

    @Override
    public void manageProjects() {
        System.out.println(name + " is managing coding and tasks.");
    }
}

```

5. Main class

 ```java
   public class Main {
    public static void main(String[] args) {
        Employee manager = new Manager("Mathew", "123 Street", XY, "Manager");
        Employee developer = new Developer("Dylan", "123 Street", XX, "Developer");
        Employee programmer = new Programmer("Paige", "123 Street", YY, "Programmer");

        // Display information
        manager.displayInfo();
        System.out.println("Bonus:" + manager.calculateBonus());
        System.out.println(manager.generatePerformanceReport());
        manager.manageProjects();

        System.out.println();

        developer.displayInfo();
        System.out.println("Bonus:" + developer.calculateBonus());
        System.out.println(developer.generatePerformanceReport());
        developer.manageProjects();

        System.out.println();

        programmer.displayInfo();
        System.out.println("Bonus:" + programmer.calculateBonus());
        System.out.println(programmer.generatePerformanceReport());
        programmer.manageProjects();
    }
}
```
