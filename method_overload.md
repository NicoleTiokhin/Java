# operator/ method overload 

- have more than one method with the same name, but different parameters
- also possible to use different numbers of parameters or order of Parameters
- call different methods based on parameters
- cannot overload methods based only on return type
  
Example : 
```java
public class Addition {
      public int add(int a, int b) {
        return a + b;
    }
       public int add(double a, double b) {
        return a + b;
    }
      public static void main(String[] args) {
        Addition addition = new Addition();
        System.out.println(addition.add(2, 4));
        System.out.println(addition.add(2.5,3.5));

```

