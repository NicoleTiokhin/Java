# Generic/Templates 

create classes, interfaces, and methods with a placeholder for types that can be specified later <br>
Type parameter : usually sth like T ; placeholder for data type

Example Generic Class : 

```java
public class Box<T> {
    private T content;

    public Box(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}

public class Main {
    public static void main(String[] args) {
        // integer
        Box<Integer> integerBox = new Box<>(123);
        System.out.println("Integer Value: " + integerBox.getContent());
        // string
        Box<String> stringBox = new Box<>("String");
        System.out.println("String Value: " + stringBox.getContent());
    }
}

```

Example for generic Method  
```java
public static <T> void printArray(T[] array) {
    for (T element : array) {
        System.out.println(element);
    }
}

public class Main {
    public static void main(String[] args) {
        Integer[] intArray = {1, 2, 3, 4, 5};
        String[] stringArray = {"one", "two", "three", "four", "five"};

        printArray(intArray);

        printArray(stringArray);
    }
}

```

## Exercise 1 
found on : https://www.geeksforgeeks.org/problems/java-generic-class/1 <br>
Generic Class in java  is a feature. We write code once and use it for any data type including user defined data types. Given an integer or a string as an input.Print the type of input and the given value as output.
<br>
Input: <br>
The first line will contain an integer T (number of test cases). First line of each test case will contain one integer c. Next line will contain a string if c is equal to 1 or will contain a integer if c is equal to 2.<br>
<br>
Output: <br>
Print the type of the given data and the given value in new line.<br>
<br>
Constraints: <br>
1 < = T < = 100<br>
1 < = c < = 2<br>
1<= Length of strings, integers<=103<br>
<br>
Example:<br>
Input:<br>
2<br>
1<br>
gfg<br>
2<br>
5<br>
<br>
Output:<br>
String<br>
gfg<br>
Integer<br>
5<br>


```java
class Data<T> {
    private T data;

    public Data(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void printDataTypeAndValue() {
        if (data instanceof String) {
            System.out.println("String");
        } else if (data instanceof Integer) {
            System.out.println("Integer");
        }
        System.out.println(data);
    }
}

```


```java
public class GenericClassExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt(); 
        scanner.nextLine();

        for (int i = 0; i < T; i++) {
            int c = scanner.nextInt(); // read the type indicator (1 is string, 2 is integer)
            scanner.nextLine();

            if (c == 1) {
                String str = scanner.nextLine(); 
                Data<String> stringData = new Data<>(str);
                stringData.printDataTypeAndValue();
            } else if (c == 2) {
                int num = scanner.nextInt(); 
                scanner.nextLine(); 
                Data<Integer> intData = new Data<>(num);
                intData.printDataTypeAndValue();
            }
        }

        scanner.close();
    }
}

```

## Exercise 2 
found on : https://docs.oracle.com/javase/tutorial/java/generics/QandE/generics-questions.html<br>

Write a generic method to count the number of elements in a collection that have a specific property (for example, odd integers, prime numbers, palindromes).

```java
import java.util.Collection;
import java.util.List;

public class GenericCounter {
    // Generic method to count elements with a specific property
    public static <T> int countIf(Collection<T> collection // collection of elements to be checked, ConditionChecker<T> checker //instance of ConditionChecker interface , for condition check logic) {
        int count = 0;
        // iterate through all elements in the collection
        for (T element : collection) {
            if (checker.check(element)) {
            // increment the count if the condition is met
                count++;
            }
        }
        return count;
    }
    // returns a boolean indicating if the element satisfies the condition
    interface ConditionChecker<T> {
        boolean check(T t);
    }
   // use countif with different condition checkers 
    public static void main(String[] args) {
        Collection<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        int oddCount = countIf(numbers, new ConditionChecker<Integer>() {
            @Override
            public boolean check(Integer n) {
                return n % 2 != 0;
            }
        });
        System.out.println("Odd numbers count: " + oddCount);
        
        int primeCount = countIf(numbers, new ConditionChecker<Integer>() {
            @Override
            public boolean check(Integer n) {
                return isPrime(n);
            }
        });
        System.out.println("Prime numbers count: " + primeCount);
        
        Collection<String> words = List.of( "apple", "level", "hello");
        
        int palindromeCount = countIf(words, new ConditionChecker<String>() {
            @Override
            public boolean check(String str) {
                return isPalindrome(str);
            }
        });
        System.out.println("Palindromes count: " + palindromeCount);
    }

    private static boolean isPrime(int number) {
        if (number <= 1) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

    private static boolean isPalindrome(String str) {
        int n = str.length();
        for (int i = 0; i < n / 2; i++) {
            if (str.charAt(i) != str.charAt(n - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}

```


## Exercise 3
found on : https://www.w3resource.com/java-exercises/generic/index.php <br>
<br>
Write a Java program to create a generic method that takes a map of any type and prints each key-value pair.
```java
import java.util.Map;

public class GenericMapPrinter {

    public static <K, V> void printMap(Map<K, V> map) {
        // iterate through the map entries
        for (Map.Entry<K, V> entry : map.entrySet()) {
            // print each key-value pair
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }
    // printMap with different types 
    public static void main(String[] args) {
        // keys are integers and the values are strings
        Map<Integer, String> intStringMap = Map.of(
            1, "One",
            2, "Two",
            3, "Three"
        );
        System.out.println("Integer-String Map:");
        printMap(intStringMap);
        // keys are strings and the values are integers
        Map<String, Integer> stringIntMap = Map.of(
            "One", 1,
            "Two", 2,
            "Three", 3
        );
        System.out.println("\nString-Integer Map:");
        printMap(stringIntMap);
        // keys and values are strings
        Map<String, String> stringStringMap = Map.of(
            "First", "1st",
            "Second", "2nd",
            "Third", "3rd"
        );
        System.out.println("\nString-String Map:");
        printMap(stringStringMap);
    }
}

```


