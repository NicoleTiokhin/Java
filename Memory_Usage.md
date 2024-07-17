# Memory  Usage 

managed by the Java Virtual Machine (JVM)

Heap Memory: 
<ul>
  <li>allocate memory for objects and class instances</li>
  <li>managed automatically by the Garbage Collector, which cleans up unused objects to free memory</li>
  <li>dynamic, long-term storage</li>
  <li>every time a new object is created (new keyword) , memory is allocated</li>
  <li>Garbage collection to automatically free up memory in the heap that is no longer needed</li>
</ul>


Stack Memory: 
<ul>
  <li>short-term storage for local variables defined within the method, method parameters,where to return after the method completes</li>
  <li>smaller compared to the heap</li>
  <li> Last-In-First-Out (LIFO) </li>
  <li>when a method finishes executing, its stack frame is popped off the stack</li>
  <li> usually fixed and determined at the start of the program</li>
  <li>If a program uses too much stack memory:StackOverflowError</li>
</ul>
