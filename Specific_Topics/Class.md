# Class 

= blueprint for creating objects

Object = instance of a class

no memory is allocated until an object of that class is created

Attributes = Variables of a class represening the properties of an object

Methods = Functions within a class defining actions that an object can perform

public = accessible from anywhere in the program

private = accessible only within class where defined


### Exercise : University Management System

this exercise was inspired by a similar task  I had to do in high school once 
```java
import java.util.ArrayList; // flexible array 
import java.util.List;  

// Abstract Class Person
//
abstract class Person {
    private String name;
    private int age;
    private String email;

    public Person(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public abstract void displayInfo(); // abstract method : must be implemented by any subclass
}

// Class student 
// Student extends Person
class Student extends Person {
    private List<Course> courses; // list of courses the student is enrolled in

    public Student(String name, int age, String email) {
        super(name, age, email);
        this.courses = new ArrayList<>();
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void enrollCourse(Course course) { //add course to the student's list if not already enrolled and call  addStudent method of Course class
        if (!courses.contains(course)) {
            courses.add(course);
            course.addStudent(this);
        } else {
            System.out.println("The student is already enrolled in this course.");
        }
    }

    @Override
    public void displayInfo() {
        System.out.println("Student Name: " + getName());
        System.out.println("Age: " + getAge());
        System.out.println("Email: " + getEmail());
    }
}
// Interface Teachable
// Every class implementing this interface must have an implementation for this method
interface Teachable {
    void teachCourse(Course course);
}

// Class Professor
// Professor extends Person
// implements Teachable interface
class Professor extends Person implements Teachable {
    private List<Course> courses; // list of courses the professor teaches

    public Professor(String name, int age, String email) {
        super(name, age, email);
        this.courses = new ArrayList<>();
    }

    public List<Course> getCourses() {
        return courses;
    }

    @Override
    public void teachCourse(Course course) { // add a course to the professor's list if not already there
        if (!courses.contains(course)) {
            courses.add(course);
        } else {
            System.out.println("The professor is already teaching this course.");
        }
    }

    @Override
    public void displayInfo() {
        System.out.println("Professor Name: " + getName());
        System.out.println("Age: " + getAge());
        System.out.println("Email: " + getEmail());
     }
  }

  // Class Course

  class Course {
    private String courseName;
    private String courseNumber;
    private List<Student> students;

    public Course(String courseName, String courseNumber) {
        this.courseName = courseName;
        this.courseNumber = courseNumber;
        this.students = new ArrayList<>();
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) { //add a student to the course if not already enrolled
        if (!students.contains(student)) {
            students.add(student);
        } else {
            System.out.println("Student is already enrolled in this course.");
        }
    }
}

// Main Class 
public class Main {
    public static void main(String[] args) {
        // Create a professor
        Professor prof1 = new Professor("Dr. Dr", 50, "drdr@gmail.com");

        // Create a course
        Course course1 = new Course("Programming", "CS101");

        // Create a student
        Student student1 = new Student("Student", 19, "student@gmail.com");

        // Professor teaches course1
        prof1.teachCourse(course1);

        // Student enrolls in this course
        student1.enrollCourse(course1);

        // Display professor information
        prof1.displayInfo();

        // Display course information
        System.out.println("Course Name: " + course1.getCourseName());
        System.out.println("Course Number: " + course1.getCourseNumber());
        System.out.println("Enrolled Students: " + course1.getStudents().size());

        // Display student information
        student1.displayInfo();
    }
}

```



