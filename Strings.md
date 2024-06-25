# Strings 

= a sequence of characters , for text 

Strings are immutable -> once a string object is created, its value cannot be changed 
StringBuilder is a  mutable alternative

## create new string 

using new keyword 

``` java
String greeting = new String("Hello, World!");
```
## Basic String operations 

### Concatenation 
As in joining two strings together
``` java
String firstName = "Nicole";
String lastName = "Tiokhin";
String fullName = firstName + " " + lastName; 
``` 
Or using the concat Method 
``` java
String fullName = firstName.concat(" ").concat(lastName);
```

### Length 
Finding the length of a string
```java
int length = fullName.length(); 
```
### Substring 
Get part of a string

```java
String firstName = fullName.substring(0, 6); //Nicole
```
### Replace 
Replace parts in a string
```java
String modifiedName = fullName.replace('N', 'n'); // "nicole Tiokhin"
```

### Upper and Lower Case
Convert the case of the string

```java
String upperCaseName = fullName.toUpperCase(); // "NICOLE TIOKHIN"
String lowerCaseName = fullName.toLowerCase(); // "nicole tiokhin"
```

### Compare two Strings 

```java
String anotherName = "Nicole Tiokhin";
boolean isEqual = fullName.equals(anotherName); // true
```

Or equalsIgnoreCase to ignore whether lowercase or uppercase 
```java
boolean isName = fullName.equalsIgnoreCase("nicole tiokhin"); // true
```

## Basic String Methods 

### contains
Checks if a sequence is present in the string
```java
boolean containsWord = fullName.contains("Nicole"); // true
```

### indexOf
Finds the index of the first occurrence of a substring or character 
```java
int index = fullName.indexOf("Tiokhin"); 
```
## Strings : Some exercises 

### Palindrome Check
Palindrome = reads the same backward as forward
```java
import java.util.Scanner;

public class PalindromeChecker {

    public static boolean isPalindrome(String str) {
        str = str.toLowerCase();
        
        int left = 0;
        int right = str.length() - 1;
        
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true; 
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Please enter a string to check if it is a palindrome: ");
        String input = scanner.nextLine();
        
        if (isPalindrome(input)) {
            System.out.println("'" + input + "' is a palindrome.");
        } else {
            System.out.println("'" + input + "' is not a palindrome.");
        }
        
        scanner.close();
    }
}

```

### Anagram Check

### Reverse String 


