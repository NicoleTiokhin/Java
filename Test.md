# Welcome to the Learning Outcomes Evaluation

Dear students,

Welcome to this Learning Outcomes Evaluation session. Over the next two hours, we will be assessing your understanding and mastery of the learning outcomes for this semester. This evaluation is an important opportunity for you to showcase your knowledge and skills.

Please take this evaluation seriously and demonstrate your best work from the last two weeks by using your personal git account as proof. Remember to answer each question thoroughly and provide clear explanations where necessary.

Best regards,
Ghada Hassan and Kay Berkling

## Ethics Section regarding generative and other forms of AI

The student acknowledges and agrees that the use of AI is strictly prohibited during this evaluation. By submitting this report, the student affirms that they have completed the assessment independently and without the assistance of any AI technologies. This agreement serves to ensure the integrity and authenticity of the student's work, as well as their understanding of the learning outcomes.


## Checklist before handing in your work

- [ ] Review the assignment requirements to ensure you have completed all the necessary tasks.
- [ ] Pay careful attention to the requested links that must come from your project.
- [ ] Double-check your links and make sure that links lead to where you intended. Each answer should have links to work done by you in your own git repository
- [ ] Make sure you have at least 10 references to your project code (This is important evidence to prove that your project is substantial enough to support the learning outcome of object oriented design and coding within a larger piece of code.)
- [ ] Include comments to explain your referenced code and why it supports the learning outcome
- [ ] Proofread any accompanying documentation or comments for grammar and clarity.
- [ ] Commit and push this markup file to your personal git repository and hand in the link and a hard-copy via email at the end of the exam.

Remember, this checklist is not exhaustive, but it should help you ensure that your work is complete, well-structured, and meets the required standards.

Good luck with your evaluation!


## Learning Outcomes

| Exam Question | Total Achievable Points | Points Reached During Grading |
|---------------|------------------------|-------------------------------|
| Algorithms    |           4            |                               |
| Data types    |           4            |                               |
| Complex Data Structures |  4            |                               |
| Concepts of OOP |          6            |                               |
| OO Design     |           6            |                               |
| Class concepts |            8           |                               |
| Testing       |           6            |                               |
| Operator/Method Overloading | 6 |                               |
| Templates/Generics |       6            |                               |
| Class libraries |          6            |                               |
| Multi-threading |          6            |                               |
| Lambda expressions |       6            |                               |
| Serialization |            6           |                               |
| Database connectivity |   6            |                               |
| Total        |           80          |                               |



## Evaluation Questions

Please answer the following questions to the best of your ability to show your understanding of the learning outcomes. Please provide examples from your project code to support your answers. Only links to your own git will count. When you explain what you did make sure to explain why you did it this way with clear reasoning relating to your own work and not generic statements that anyone could make. 

## Evaluation Material
link to entire github repository : https://github.com/NicoleTiokhin/Java/tree/main
### Algorithms

Algorithms are manyfold and Java can be used to program these. Examples are sorting or search strategies but also mathematical calculations. Please refer to **two** areas in either your regular coding practice or within your project, where you have coded an algorithm. Do not make reference to code written for other classes, like theoretical informatics.
https://github.com/NicoleTiokhin/Java/blob/main/BackupCode/Pokemon_code.md
In my project I needed to count the number of non-fainted Pokemon in my team , mym algorithm in the countActivePokemon Method  iteratesd through the list of Pokémon and checked their status

I also have an algorithm to track and restore the health of Pokémon . I track the health lost by each team during a round and restore Pokémon health to the maximum Health after each round:
trackHealth(): calls healthLostInRound for each team and updates the total health lost
healthLostInRound(): iiterates through the team list, calculates the health lost for each Pokémon, and sums it up
restoreTeamHealth(): iterates through the team list and restores each Pokémon's health to its maximum

*your text*


| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|           2            |                               |
|           2            |                               |


### Data types

Please explain the concept of data types and provide examples of different data types in Java.
Typical data types in java are int, double, float, char, boolean, long, short, byte, String, and arrays. Please provide one example for each of the **three** following data types in your code. One of these links must be to your project.
* Array
are a collection of variables of the same type
https://github.com/NicoleTiokhin/Java/blob/main/BackupCode/Pokemon_code.md
In the weather class I used an array of strings to store all possible weathers , then I selected a random element from the array 
* Strings
Strings are a series of characters to represent text
I used String to store and manage the names of teams and Pokémon
I also used String to store user input
I used StringBuilder to create Status Messaged on the Pokemon
I also used String to display messages on the console
https://github.com/NicoleTiokhin/Java/blob/main/BackupCode/Pokemon_code.md
* boolean
returns true or false
https://github.com/NicoleTiokhin/Java/blob/main/BackupCode/Pokemon_code.md
allFainted method returns a boolean to show if  all Pokémon in a given team have fainted
Regarding the link to your project, explain the context in which you have used this data type being specific to your project. 

*your text*

| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|           1            |                               |
|           1           |                               |
|           2 (project link)          |                               |



### Complex Data Structures

Examples of complex data structures in Java are ArrayList, HashMap, HashSet, LinkedList, and TreeMap. Please provide an example of how you have used **two** of these complex data structures in your code and explain why you have chosen these data structures. Examples do not have to come from the project.
https://github.com/NicoleTiokhin/Java/blob/main/BackupCode/Pokemon_code.md
I used ArrayList to store the Pokémon teams (team1 and team2).
I used it because it has dynamic resizing and my teams can choose their own teams sizes 
https://github.com/NicoleTiokhin/Java/blob/main/Specific_Topics/Complex_Datastructure.md
I wrote a Java program to append the specified element to the end of a hash set
I created a new HashSet instance set for integers , added 1, 2, and 3 to the HashSet, print the HashSet before adding the new element,created an int variable newElement and assigned it the value 4 ,appended newElement (4) to the HashSet and printed the HashSet after adding the new element
for this file : https://github.com/NicoleTiokhin/Java/blob/main/BackupCode/Memory_Game.md
The initializeBoard method in the Board class uses an ArrayList to store and mix the tile numbers before showing them on the board, this is useful for the available methods for an ArrayList like add() get() that make managing the tiles easier 
Collections.shuffle() also works well with ArrayLists 
The Board class uses a 2D array of Tile objects to represent the game board to have quick access to the location of the tiles (direct access to each element based on row and column indices)(more intuitive since it resembles a grid)
*your text*

| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|           2            |                               |
|           2            |                               |



### Concepts of OOP
Concepts of OOP are the basic building blocks of object-oriented programming, such as classes, objects, methods, and attributes. 
Explain HOW and WHY your **project** demonstrates the use of OOP by using all of the following concepts:
* Classes/Objects
I have several classes such as Fight, Pokemon, Potion, and Weather
I created instances of the classes to represent specific Pokémon, battles, potions, and weather conditions
An instance of the Pokemon class would be an actual Pokémon with specific values for the attributes in the Class 
I used it to break down my program into smaller, reusable parts
* Methods
For example in the Pokemon class I have the Methods : attack, heal, and hasFainted
They define an action a Pokemon can perform in the Game 
* Attributes 
Link to the code in your project that demonstrates what you have explained above.
For example in my Pokemon class I have attributes such as name, health, attackPower, and type as states of my Pokemon object
https://github.com/NicoleTiokhin/Java/blob/main/BackupCode/Pokemon_code.md

*your text*

| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|          2              |                               |
|          2              |                               |
|          2              |                               |



### OO Design
Please showcase **two** areas **where** you have used object orientation in your project. (How and why you use it will be the next question below.)
Examples in Java of good oo design are composition, encapsulation, inheritance, polymorphism, and abstraction.
composition : 
= a class is composed of one or more objects from other classes
Fight class contains objects of Pokemon, Potion, and Weather
team1 and team2 (arrays of Pokemon), team1Potion and team2Potion (objects of Potion), and weather (object of Weather)
-> easy to create a more complex program by keeping the code organized 
encapsulation :
= internal state of an object is hidden from the outside
Pokemon class has private attributes and getter and setter methods to access them (name, health, attackPower, maxHealth, type, typeEffectiveness)
->  controlled access to the attributes 
https://github.com/NicoleTiokhin/Java/blob/main/BackupCode/Pokemon_code.md

*your text*

| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|              3         |                               |
|              3         |                               |



### Advanced Class Concepts
Advanced class concepts include constructors, abstract classes, interfaces, access modifiers, static methods, and variables. Please provide an example of how you have used **two** of these class concepts in your **project** code and explain why you have chosen these class concepts and how they work within your code.
Constructor : 
= initialize objects when they are created
In the Pokemon Class for example I have a constructor to initialize the attributes of a Pokemon object
In my GameGUI class the constructor sets up the game window and its components
I set up the Window Properties like the title and size , I initialze the components like the card layout, I set up the  Introduction Panel with a label and buttons for Pokémon selection .
By using the Constructor in the GameGUI class I make sure that all components (panels, buttons, labels) are correctly created and configured by the time the game window is displayed
https://github.com/NicoleTiokhin/Java/blob/main/BackupCode/Pokemon_code.md
Access modifiers :
= control the visibility of attributes and methods 
In the Pokemon class, attributes are private and methods are public
Attributes are private in order to only accesse them  within the Pokemon class , control how they are accessed and modified, protects from sudden unexpected changes 
Methods in order to acces them  from anywhere in my program
*your text*

| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|            4           |                               |
|            4           |                               |

### Testing
Java code is tested by using JUnit. Please explain how you have used JUnit in your project and provide a link to the code where you have used JUnit. Links do not have to refer to your project and can refer to your practice code. If you tested without JUnit, please explain how you tested your code.
Be detailed about what you are testing and how you argue for your test cases. 
Test cases usually cover the following areas:
* boundary cases
method allFainted checks if all Pokémon in a team have fainted
* normal cases
fight method handles a normal battle
* error cases / catching exceptions
method getIntInput handles InputMismatchException to ensure valid integer input.
createPokemon method in the Game class handles exceptions when fetching Pokémon data
https://github.com/NicoleTiokhin/Java/blob/main/BackupCode/Pokemon_code.md


*your text*

| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|                        |                               |
|            6           |                               |

### Operator/Method Overloading
An example of operator overloading is the "+" operator that can be used to add two numbers or concatenate two strings. An example of method overloading is having two methods with the same name but different parameters. Please provide an example of how you have used operator or method overloading in your code and explain why you have chosen this method of coding.
The link does not have to be to your project and can be to your practice code.
https://github.com/NicoleTiokhin/Java/blob/main/Specific_Topics/method_overload.md
the add method is overloaded to accept different types of parameters: one version of the add method takes two integers, and another version takes two doubles

*your text*

| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|                        |                               |
|            6            |                               |



### Generics
Generics in java are used to create classes, interfaces, and methods that operate on objects of specified types. Please provide an example of how you have used generics in your code and explain why you have chosen to use generics. The link does not have to be to your project and can be to your practice code.
https://github.com/NicoleTiokhin/Java/blob/main/Specific_Topics/generic_templates.md
takes a map of any type and prints each key-value pair
I created a method  using generics with <K, V>, meaning that it can operate on any key type K and value type V
The method method iterates through the map entries using entrySet() and prints each key-value pair.
I used generics so I could use the method  with any type of map
In the code i used the same method for : Integer-String Maps , String-Integer Maps and String-String Maps
*your text*

| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|                        |                               |
|            6           |                               |

### Class Libraries
Examples of class libraries in java are the Java Standard Library, JavaFX, Apache Commons, JUnit, Log4j, Jackson, Guava, Joda-Time, Hibernate, Spring, Maven, and many more. Please provide an example of how you have used a class library in your **project** code and explain why you have chosen to use this class library. 
I used org.json to handle JSON Responses from the PokeAPI 
I chose it because its the easiets wayy to parse JSON data
It has all methods needed to handle json data in one place 
It also doesnt have many extra dependencies 

for this code here : https://github.com/NicoleTiokhin/Java/blob/main/BackupCode/Memory_Game.md
java.util library for  : Timer, and TimerTask for example to handle the timing of couning the time how long it takes to finish the game 
*your text*

| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|                        |                               |
|             6           |                               |

### Multi-threading
multi-threading is the ability of a CPU to execute multiple processes or threads concurrently. Please explain the concept of multi-threading and provide an example of how you have used multi-threading in your code. The link does not have to be to your project and can be to your practice code.

https://github.com/NicoleTiokhin/Java/blob/main/BackupCode/CurrencyConverter.md

handles fetching exchange rates and displaying the exchange rate trends concurrently , which wouldve been very useul for if I had enough time to make this project bigger 
a thread is created and started to fetch the current exchange rate and display the converted amount
another thread is created and started to generate and display a trend chart
another thread that runs in the background  , for the Timer is also created to check for changes in the exchange rate . This is especially useful since the timer constantly checls for changes , so it helps not disturb the other processes in case sth goes wrong and here the risk is high and its also a more efficient way to use the CPU .  

for this code here  : https://github.com/NicoleTiokhin/Java/blob/main/BackupCode/Memory_Game.md
startTimer method in the MemoryGame class uses Timer and TimerTask to manage the countdown timer for the game.
It runs parallely  with the main game 
*your text*

| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|                        |                               |
|            6           |                               |

### Lambda Expressions
An example of a lambda expression is the following code: 
```java
List<String> list = new ArrayList<>();
list.forEach((String s) -> System.out.println(s));
```
Please explain the concept of lambda expressions and provide an example of how you have used lambda expressions in your code. The link does not have to be to your project and can be to your practice code.
Lamda allows for short, anonymous functions( defined without a name often for short one-time tasks) , helps make code shorter and more readable 
I used it to write a Java program to implement a lambda expression to check if a list of strings are all uppercase or all lowercase or mixedcase
First I created a list of Strings
Then I used Lambda Expressions to check if each string in the list is: all uppercase , all lowercase or mixed case
I used Predicates (functional interface in the java.util.function package , used for evaluating conditions) in The Lambda Expressions 
*your text*

| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|                        |                               |
|           6            |                               |


### Serialization

Serialization is the process of converting an object into a stream of bytes to store the object or transmit it to memory, a database, or a file. Please explain why you would use serialization and provide an example of how you have used serialization in your code. The link does not have to be to your project and can be to your practice code.
for this code : https://github.com/NicoleTiokhin/Java/blob/main/BackupCode/Weather_System.md
I wouldve used serialization by creating a class to hold the weather data, by representing the weather data with attributes for datetime, temperature, and condition and implementing Serializable to allow for serialization 
In my WeatherSystem Class I wouldbe added methods to get current weather data and store it in a WeatherData object 
I wouldve serialized it like that weatherSystem.saveWeatherData("weatherData.ser");
I wouldve deserialized it like that : WeatherData loadedData = weatherSystem.loadWeatherData("weatherData.ser");
Imagine i have a loadWeatherData and saveWeatherData Method in the WeatherSystemClass to read the now deserialized file and serializing a WeatherData object and saving it to a file

*your text*

| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|                        |                               |
|             6          |                               |


### Database Connectivity
Web applications use databases to store data. Please explain how you have connected to a database in your project and provide an example of how you have used database connectivity in your code. The link does not have to be to your project and can be to your practice code. Do not use code from other classes, like database programming, Web Engineering, or theoretical informatics.
This topic appeared in your learning agreement as the last point in the table under advanced topics.
I used the PokeApi in my project , meaining I used a web-based APi rather than a traditional Database .
Using the PokeApi I got access to Pokemon Data which I queried over the Internet 
I connected to the API usin the PokemonAPI Class :
I created a base URL for the Pokémon API
I had a getPokemonData Method : 
takes a String (the Pokémon's name) as an argument and returns a JSONObject ; can throw an Exception Add Pokemon name to to base URL and create a URL object
I opened a connection to the URL Set the request method to "GET" and retrieved data from the server
I created a BufferedReader to read the response . It gets the input stream from the HTTP connection (conn.getInputStream()), takes the raw byte input stream(InputStreamReader) and converts into characters and read the lines of test (in) String variable to temporarily hold each line of text one at a time Create a new StringBuilder object to append strings
I read each line from the BufferedReader until there are no more lines (readLine returns null) and appened each line to the StringBuilder
I closed the BufferedReader , disconnected the HttpURLConnection ,converted  the complete response to a String and  created a new JSONObject from that string

https://github.com/NicoleTiokhin/Java/blob/main/BackupCode/Pokemon_code.md
*your text*

| Total Achievable Points | Points Reached During Grading |
|------------------------|-------------------------------|
|                        |                               |
|            6            |                               |



