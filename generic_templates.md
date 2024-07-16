# Generic/Templates 

create classes, interfaces, and methods with a placeholder for types that can be specified later
Type parameter : usuually sth like T ; placeholder for data type

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

        Box<Integer> integerBox = new Box<>(123);
        System.out.println("Integer Value: " + integerBox.getContent());

        Box<String> stringBox = new Box<>("Hello Generics");
        System.out.println("String Value: " + stringBox.getContent());
    }
}

```


```java
```


```java
```


```java
```


```java
```


```java
```
