# Functions
Called methods in Java 

## Components
<ul>
  <li>Access modifiers  (e.g., public, private)</li>
  <li>Return Type : What value type does the method return? </li>
  <li>Method Name</li>
  <li>Parameters (Optional) : Inputs to the method</li>
  <li>Code that defines what the Method does </li>
</ul>

## Example : Calculate area of a rectangle 
<ul>
  <li>parameters : length , width (both integer) </li>
  <li>Access modifiers : public -> accessible from other classes</li>
  <li>Return Type : integer  </li>
  <li>Call method inside main method </li>
</ul>


```java
public class Rectangle {

    public static void main(String[] args) {
        int length = 2;
        int width = 4;
        int area = calculateArea(length, width);
        System.out.println("The area of the rectangle is: " + area); 
    }

    public static int calculateArea(int length, int width) {
        int area = length * width;
        return area;
    }
}

```
