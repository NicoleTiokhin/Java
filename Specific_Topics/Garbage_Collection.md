# Garbage Collection 

- automatic memory management process
- Garbage collector identifies and removes unused objects to free up memory
- In other languages like C/C++ you have to manually manage memory , but not in Java

## How does it work ? 

- find objects still referenced somewhere in the program
- objects that are no  longer referenced -> possible for deletion
- garbage collector deletes these unused objects, reclaiming the memory for future use
- garbage collector deletes these objects memory for future use

## Generations

- Young Generation : holds short-lived objects
- Old Generation : holds longer-lived objects

## Unreachable Objects

- object with no references pointing to it
```java
Integer i = new Integer(1);
i = null; //now unreachable

```

## Types of Garbage Collection

- Minor or Incremental Garbage Collection :  happens frequently, removes objects that are no longer used (unreachable objects) in the young generation
- Major or Full Garbage Collection : objects that survived the minor garbage collection are moved to the old generation heap memory and are then removed  

## How to make objects eligible for Garbage Collection

- Set the reference to null
- Assign the reference to another object
- Objects created within methods , when method completes
- objects that reference each other but are not referenced by any active part of the program


## Request Garbage Collection 

- System.gc():
- Runtime.getRuntime().gc()

- does not guarantee that the garbage collector will run immediately
- JVM decides the best time to perform garbage collection
