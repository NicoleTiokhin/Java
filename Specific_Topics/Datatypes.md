# Java Datatypes 
## So called primitive data types 
byte : 8 bit integer , minimum value : -128 maximum value : 127 <br>
 ``` byte Number = 10; ```<br>
 short :  16-bit integer , minimum value : -32,768  maximum value : 32,767<br>
 ``` short Number = 10; ```<br>
 -> good for save memory in large arrays<br>
int : 32-bit integer , minimum value : -2^31 maximum value : 2^31-1<br>
 ``` int Number = 10; ```<br>
 long : 64-bit integer , minimum value : -2^63  maximum value :  2^63-1<br>
  ``` long Number = 10; ```<br>
  float : 32-bit float(as in decimals) <br>
   ``` float Decimal = 10.0; ```<br>
   double :  64-bit float<br>
   ``` double Decimal = 10.0; ```<br>
   char : any character<br>
    ``` char Char = 'A'; ```<br>
    boolean : true or false 
     ```  boolean Boolean = true;```

  ## Reference Data Types
  String : stores a sequence of characters <br>
    ``` String HelloWorld = "Hello, World!"; ```  <br> 
  Array : collection of variables of the same type<br>
  ``` int[] myArray = {1, 2, 3, 4, 5};```<br>
  Class : "blueprint" for objects<br>
```public class Main {
    public static void main(String[] args) {
        Person person = new Person();
        person.name = "Alice ";
        person.age = 18;
        System.out.println("Name: " + person.name + ", Age: " + person.age);
    }
}

class Person {
    String name;
    int age;
}
```<br>



``````
   
