# Pokemon game 
Inspired by a simple Pokemon game I wrote when learning Python 
## Pokemon Class 
The Pokemon class represents individual Pokemon 
<ul>
  <li>Attributes : name , health , attackPower </li>
  <li>Methods toaccess and change attributes (Getter&Setter) </li>
  <li>Method for one Pokemon to attack another including a message to player describing the attack and decrease health of traget Pokemon by the attackpower of attacking Pokemon</li>
  <li>Check if Pokemon´s health is zero or less -> Pokemon fainted (since they dont die in the game)</li>
</ul>

``` java
    public class Pokemon {
    private String name;
    private int health;
    private int attackPower;

    public Pokemon(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void attack(Pokemon target) {
        System.out.println(this.name + " attacks " + target.getName() + " for " + this.attackPower + " damage!");
        target.setHealth(target.getHealth() - this.attackPower);
    }

    public boolean hasFainted() {
        if (this.health <= 0) {
            System.out.println("Your Pokemon has fainted!");
            return true;
        }
        return false;
    }

    public String pokedex() {
        return name + " [ Current Health Level : " + health + ", Current Power Level : " + attackPower + "]";
    }
}
```

## Fight Class
This class manages fight logic 

 <ul>
  <li>pokemon1 , pokemon2 as instances of Pokemon class </li>
   <li>Scanner object to read user input </li>
   <li>Set first attacker to pokemon1 and first defender to pokemon2 </li>
        <li> Fight Loop : <br> loop goes on until a Pokemon has fainted 
          <br> print out current status of both Pokemon  and announce who´s turn it is 
          <br> current attacker attacks current defender 
        <br> check if current defender has fainted ; If true then break out of loop and send message
        <br> Temporarily store the current attacker <br>
        current defender becomes the new attacker <br>
        original attacker becomes the new defender <br>
        Prompt user to press Enter to continue <br>
        wait for the user to press Enter <br>
        close Scanner object </li>
 </ul>

``` java
public class Fight {
    private Pokemon pokemon1;
    private Pokemon pokemon2;

    public Fight(Pokemon pokemon1, Pokemon pokemon2) {
        this.pokemon1 = pokemon1;
        this.pokemon2 = pokemon2;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        Pokemon currentAttacker = pokemon1;
        Pokemon currentDefender = pokemon2;

        while (!pokemon1.hasFainted() && !pokemon2.hasFainted()) {
            System.out.println("\n" + pokemon1.pokedex());
            System.out.println(pokemon2.pokedex());

            System.out.println(currentAttacker.getName() + "'s turn to attack.");
            currentAttacker.attack(currentDefender);

            if (currentDefender.hasFainted()) {
                System.out.println(currentDefender.getName() + " has fainted! Please bring to Pokémon Center to recover ! " + currentAttacker.getName() + " is the winner !");
                break;
            }

            Pokemon temp = currentAttacker;
            currentAttacker = currentDefender;
            currentDefender = temp;

            System.out.println("Please press Enter to continue...");
            scanner.nextLine();
        }

        scanner.close();
    }
}
``` 
 
## Game Class
This class starts the game

startGame method : 
<ul>
  <li> initializes two Pokemons (Pokemon instances) </li>
  <li> inform player the game is going to start </li>
</ul>

main method : 
<ul>
  <li>the main methid is secial to Java to start "application" </li>
  <li>creates instance if Game class </li>
  <li> call startGame method to start Game </li>
</ul>

``` java
public class Game {
    public void startGame() {
        Pokemon pikachu = new Pokemon("Pikachu", 130, 25);
        Pokemon bulbasaur = new Pokemon("Bulbasaur", 160, 15);

        System.out.println("Begin the Pokémon battle!");
        Fight fight = new Fight(pikachu, bulbasaur);
        fight.start();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}
```
## Example of how the game would work  
Pokemon 1 = Pikachu , Health: 130 , Attack Power: 25 <br>
Pokemon 2 = Bulbasaur , Health: 160 , Attack Power: 15 <br>
-> Initial State : Pikachu: 130 HP, 25 AP ; Bulbasaur: 160 HP, 15 AP <br>

1. main Method in Game class starts Game <- creates an instance of Game and calls startGame
2. The startGame Method initializes two Pokemon and returns a start message and starts the fight.
3. First Round Output : <br>
   Pikachu [ Current Health Level : 130, Current Power Level : 25]<br>
   Bulbasaur [ Current Health Level : 160, Current Power Level : 15]<br>
   Pikachu's turn to attack.<br>
   Pikachu attacks Bulbasaur for 25 damage!<br>
-> Bulbasaur's health decreases from 160 to 135<br>
5. Second Round :<br>
   Pikachu [ Current Health Level : 130, Current Power Level : 25]<br>
   Bulbasaur [ Current Health Level : 135, Current Power Level : 15]<br>
   Please press Enter to continue...<br>
   Bulbasaur's turn to attack.<br>
   Bulbasaur attacks Pikachu for 15 damage!<br>
-> Pikachu's health decreases from 130 to 115<br>
7. This continues until the 13th Round , where : <br>
   Pikachu [ Current Health Level : 40, Current Power Level : 25]<br>
   Bulbasaur [ Current Health Level : 10, Current Power Level : 15]<br>
   Please press Enter to continue...<br>
   Pikachu's turn to attack.<br>
   Pikachu attacks Bulbasaur for 25 damage!<br>
-> Bulbasaur's health decreases from 10 to -15 -> Bulbasaur faints<br>
9. Last Output :<br>
   Bulbasaur has fainted! Please bring to Pokémon Center to recover! Pikachu is the winner!

# Making the Game more elaborate 
## Ideas : 
- Special Abilities : Like Volt Tackle for Pikachu , can be used once per Game 
- Type Advantages
- Leveling Up
- ~~potions to heal~~ **done**
- Wild Pokémon Encounters
- add money to buy potions and stuff
- Multi-player Mode
- Pokémon Catching
- NPC trainers and gym battles with  rewards for winning
- simple animations for attacks
- switching Pokémon mid-battle
- Stamina : use certain moves a limited number of times before needing to rest or use a different move
- Pokémon Centers to heal Pokémon
- ~~Sound and Music~~  **aborted**
- Save and Load Game
- ~~( use pokemon API)~~      **done**
- ~~Let users decide on their pokemon on their own by typing the name in~~       **done**

## Let users decide on their pokemon on their own by typing the name in

Using Pokemon API : https://pokeapi.co/docs/v2#pokemon
to check how the response looks like : https://pokeapi.co/

### Workflow 
How the classes are connected to each other : <br>
 PokemonAPI class fetches data from the Pokémon API and returns it as a JSONObject <br>
 Game class uses the PokemonAPI class to fetch data and create Pokemon objects <br>
 Pokemon objects are used in the Fight class <br>
 <br>
How does the "simulation" react to user input ? :  <br>
Game class calls PokemonAPI.getPokemonData() to get data for each Pokémon <br>
The data is used to create Pokemon objects with the attributes (name, health, attackPower) <br>
The Pokemon objects are then used in the Fight class to simulate a battle <br>

### Pokemon Class 

What did I change ?  <br>
package com.example; (I used the codespace on Github to add json library using maven) <br>
otherwise same  <br>


### New class : PokemonAPI to get Pokémon data from the API



```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection; // send and receive data over the web   
import java.net.URL;// Import the URL class
import org.json.JSONObject; // work with JSON 
```

base URL for the Pokémon API  

```java
public class PokemonAPI {
    private static final String POKEAPI_URL = "https://pokeapi.co/api/v2/pokemon/";
```


Method getPokemonData ; takes a String (the Pokémon's name) as an argument and returns a JSONObject ; can throw an Exception
Add Pokemon name to to base URL 
and create a URL object 

```java
public static JSONObject getPokemonData(String pokemonName) throws Exception {
        String pokemonURL = POKEAPI_URL + pokemonName.toLowerCase();
        URL url = new URL(pokemonURL);
```


Open a connection to the URL 
Set the request method to "GET" -> retrieve data from the server
```java
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
```

Create a BufferedReader to read the response . It gets the input stream from the HTTP connection (conn.getInputStream()), takes the raw byte input stream(InputStreamReader) and converts into characters and read the lines of test (in)
String variable to temporarily hold each line of text one at a time 
Create a new StringBuilder object to append strings 

```java
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
       StringBuilder response = new StringBuilder();
```


Read each line from the BufferedReader until there are no more lines (readLine returns null)
Append each line to the StringBuilder
```java
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
```


Close the BufferedReader
Disconnect the HttpURLConnection
Convert the complete response to a String, create a new JSONObject from that string
```java
       in.close();
       conn.disconnect();

       return new JSONObject(content.toString());
```

### Game class

First of all key differences from before : 
<ul>
  <li>creates Pokémon using data fetched from the Pokémon API</li>
  <li>Prompts the user to input the names of the Pokémon</li>
  <li>Include error handling for cases where the API call might fail</li>
  <li>Requires internet access to fetch data from the API</li>
  <li>uses the org.json library for parsing JSON</li>
</ul>

createPokemon Method : 
<ul>
  <li> Method to Create a Pokémon </li>
  <li>Take a Pokémon name as an argument and returns a Pokemon object</li>
  <li>Call the PokemonAPI.getPokemonData method -> get data for the Pokémon name from the Pokémon API</li>
  <li>get name, health stat,attack power stat</li>
  <li>If error occurs , return null and an error message</li>
</ul>

```java
   public Pokemon createPokemon(String pokemonName) {
        try {
            JSONObject data = PokemonAPI.getPokemonData(pokemonName);
            String name = data.getString("name");
            int health = data.getJSONArray("stats").getJSONObject(0).getInt("base_stat");
            int attackPower = data.getJSONArray("stats").getJSONObject(1).getInt("base_stat");
            return new Pokemon(name, health, attackPower);
        } catch (Exception e) {
            System.out.println("Error fetching data for " + pokemonName);
            e.printStackTrace();
            return null;
        }
    }
```
create first and second Pokemon
if either Pokemon object is null print an error message and exits the game
create a Fight object with the two Pokémon and start the simulation battle 
obviously use main method to start "simulation"

```java
public void startGame() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the name of your first Pokémon: ");
        String firstPokemonName = scanner.nextLine();
        Pokemon pokemon1 = createPokemon(firstPokemonName);

        System.out.print("Enter the name of your second Pokémon: ");
        String secondPokemonName = scanner.nextLine();
        Pokemon pokemon2 = createPokemon(secondPokemonName);

        if (pokemon1 == null || pokemon2 == null) {
            System.out.println("Could not create Pokemon Exiting game.");
            return;
        }

        System.out.println("Begin the Pokémon battle!");
        Fight fight = new Fight(pokemon1, pokemon2);
        fight.start();

        scanner.close();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}
```

entire updated Game Code : 
```java
package com.example;

import org.json.JSONObject;
import java.util.Scanner;

public class Game {
    public Pokemon createPokemon(String pokemonName) {
        try {
            JSONObject data = PokemonAPI.getPokemonData(pokemonName);
            String name = data.getString("name");
            int health = data.getJSONArray("stats").getJSONObject(0).getInt("base_stat");
            int attackPower = data.getJSONArray("stats").getJSONObject(1).getInt("base_stat");
            return new Pokemon(name, health, attackPower);
        } catch (Exception e) {
            System.out.println("Error fetching data for " + pokemonName);
            e.printStackTrace();
            return null;
        }
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the name of your first Pokémon: ");
        String firstPokemonName = scanner.nextLine();
        Pokemon pokemon1 = createPokemon(firstPokemonName);

        System.out.print("Enter the name of your second Pokémon: ");
        String secondPokemonName = scanner.nextLine();
        Pokemon pokemon2 = createPokemon(secondPokemonName);

        if (pokemon1 == null || pokemon2 == null) {
            System.out.println("Could not create Pokemon Exiting game.");
            return;
        }

        System.out.println("Begin the Pokémon battle!");
        Fight fight = new Fight(pokemon1, pokemon2);
        fight.start();

        scanner.close();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}
```

### Fight Class
Also stays basically the same : 
``` java
package com.example;

import java.util.Scanner;

public class Fight {
    private Pokemon pokemon1;
    private Pokemon pokemon2;

    public Fight(Pokemon pokemon1, Pokemon pokemon2) {
        this.pokemon1 = pokemon1;
        this.pokemon2 = pokemon2;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        Pokemon currentAttacker = pokemon1;
        Pokemon currentDefender = pokemon2;

        while (!pokemon1.hasFainted() && !pokemon2.hasFainted()) {
            System.out.println("\n" + pokemon1.pokedex());
            System.out.println(pokemon2.pokedex());

            System.out.println(currentAttacker.getName() + "'s turn to attack.");
            currentAttacker.attack(currentDefender);

            if (currentDefender.hasFainted()) {
                System.out.println(currentDefender.getName() + " has fainted! Please bring to Pokémon Center to recover! " + currentAttacker.getName() + " is the winner!");
                break;
            }

            Pokemon temp = currentAttacker;
            currentAttacker = currentDefender;
            currentDefender = temp;

            System.out.println("Please press Enter to continue...");
            scanner.nextLine();
        }
       scanner.close();
    }
}
```
### Project structure on Github Codespace (jubilant space halibut)

Pokemon Project inside pokemon-project Directory
JsonExample.java : just a small example for testing whether the json library works (unimportant for project)
pom.xml : Maven configuration files 
rc/main/java/com/example : Java source files located there 
target Directory : compiled classes (target/classes/com/example) and other generated files from the build process
. <br>
├── Concep ts_Object-Oriented_Programming .md <br>
├── Datatypes.md <br>
├── FlippingTilesMemoryGame.md <br>
├── Functions.md <br>
├── Input_Output.md <br>
├── Operators.md <br>
├── Pokemongame.md <br>
├── README.md <br>
├── Strings.md <br>
├── Tic_Tac_Toe.md <br>
├── _01.md <br>
├── pokemon-project <br>
│   ├── JsonExample.java <br>
│   ├── pom.xml <br>
│   ├── src <br>
│   │   └── main <br>
│   │       └── java <br>
│   │           └── com <br>
│   │               └── example <br>
│   │                   ├── Fight.java <br>
│   │                   ├── Game.java <br>
│   │                   ├── Pokemon.java <br>
│   │                   └── PokemonAPI.java <br>
│   └── target <br>
│       ├── classes <br>
│       │   └── com <br>
│       │       └── example <br>
│       │           ├── Fight.class <br>
│       │           ├── Game.class <br>
│       │           ├── Pokemon.class <br>
│       │           └── PokemonAPI.class <br>
│       ├── generated-sources <br>
│       │   └── annotations <br>
│       ├── maven-archiver <br>
│       │   └── pom.properties <br>
│       ├── maven-status <br>
│       │   └── maven-compiler-plugin <br>
│       │       └── compile <br>
│       │           └── default-compile <br>
│       │               ├── createdFiles.lst <br>
│       │               └── inputFiles.lst <br>
│       └── pokemon-project-1.0-SNAPSHOT.jar <br>
└── src <br>
    ├── main <br>
    │   └── java <br>
    │       └── com <br>
    │           └── example <br>
    └── test <br>
        └── java <br>
            └── com <br>
                └── example <br>

### Example Game Play 
Enter the name of your first Pokémon: Eevee <br>
Enter the name of your second Pokémon: Bulbasaur <br>
Begin the Pokémon battle! <br>

eevee [Current Health Level: 55, Current Power Level: 55] <br>
bulbasaur [Current Health Level: 45, Current Power Level: 49]<br>
eevee's turn to attack. <br>
eevee attacks bulbasaur for 55 damage! <br>
bulbasaur has fainted! <br>
bulbasaur has fainted! Please bring to Pokémon Center to recover! eevee is the winner! <br>
        
### Another Game Play 
Enter the name of your first Pokémon: Venusaur  <br>
Enter the name of your second Pokémon: Pidgeot <br>
Begin the Pokémon battle! <br>

venusaur [Current Health Level: 80, Current Power Level: 82] <br>
pidgeot [Current Health Level: 83, Current Power Level: 80] <br>
venusaur's turn to attack. <br>
venusaur attacks pidgeot for 82 damage! <br>
Please press Enter to continue... <br>


venusaur [Current Health Level: 80, Current Power Level: 82] <br>
pidgeot [Current Health Level: 1, Current Power Level: 80] <br>
pidgeot's turn to attack. <br>
pidgeot attacks venusaur for 80 damage! <br>
venusaur has fainted! <br>
venusaur has fainted! Please bring to Pokémon Center to recover! pidgeot is the winner! <br>

## Add  potions to heal

modifications needed to be undertaken : <br>
Add a Potion class for handling the Healing  <br> 
Change the Pokemon class to allow healing 
Chnage Fight class to handle the usage of healing 
Change Game class to let users use potions during the game 

### Get the Potion Info via API in the PokemonAPI class 

HEALING_API_URL : Base URL for the Healing Potions <br>

getPotionData method : get data for a specific potion from the PokeAPI <br>
construct a URL using the potion name (potionURL) <br>
HTTP GET request to the API <br>
read the response <br>
convert it into a JSON object <br>
follows same logic like  getPokemonData <br>

```java
public static JSONObject getPotionData(String potionName) throws Exception {
        String potionURL = HEALING_API_URL + potionName.toLowerCase();
        URL url = new URL(potionURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();
        conn.disconnect();

        return new JSONObject(response.toString());
    }
```
getHealingPowerFromPotionData Method :  get the amount of HP restored<br>
potionData : JSON object representing the potion data gotten from the PokeAPI<br>
effect_entries array :  contains  effect descriptions , first object in the effect_entries array contains the needed effect information<br>
short_effect string : contains info about the potios effect <br>
healingPower :short_effect string without all non-numeric characters ("Restores 20 HP." becomes "20") converted into integer<br>



```java
public static int getHealingPowerFromPotionData(JSONObject potionData) {
        String shortEffect = potionData.getJSONArray("effect_entries")
                                       .getJSONObject(0)
                                       .getString("short_effect");
        int healingPower = Integer.parseInt(shortEffect.replaceAll("[^0-9]", ""));
        return healingPower;
    }
```

### Updated Pokemon Class 
maxHealth added : stores maximum possible health , so that Pokemon that has a health of max 30 doesnt get a higher health due to the potion as that would negate the Pokemon logic 

heal method : increase the Pokémon's health by 'amount', make sure it doesn´t exceed the Pokemons max health and print out what happened
```java
public void heal(int amount) {
        this.health += amount;
        if (this.health > this.maxHealth) {
            this.health = this.maxHealth;
        }
        System.out.println(this.name + " healed for " + amount + " health!");
    }
```

### Updated Fight Class 

added : potion object 

added : while loop : <br>
players can now choose between potion and attacking during their turn : <br>
while loop that keeps prompting the player until they make a  valid choice (if valid choice then validChoice becomes true)<br>
1 for attacking : attacker attacks the defender using the attack method<br>
and 2 for healing potion : attacker heals themselves using the heal method with the potion's healing power<br>

### Updated Game Class 

added : createPotion Method<br>
use the PokemonAPI.getPotionData method to fetch potion data<br>
get the healing power using the PokemonAPI.getHealingPowerFromPotionData method<br>


```java
public Potion createPotion(String potionName) {
    try {
        JSONObject data = PokemonAPI.getPotionData(potionName);
        int healingPower = PokemonAPI.getHealingPowerFromPotionData(data);
        return new Potion(healingPower);
    } catch (Exception e) { // If Error happens catch the exception, print an error message, and return null
        System.out.println("Error fetching data for " + potionName);
        e.printStackTrace();
        return null;
    }
}
```
modified : startGame Method <br>
Prompt user for Potion Name<br>
Call createPotion(potionName) to create the potion<br>
if the potion creation failed (returned null) exit game <br>

If both Pokémon and the potion are successfully created -> creating a Fight object and calling its start method -> start battle

```java
public void startGame() {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter the name of your first Pokémon: ");
    String firstPokemonName = scanner.nextLine();
    Pokemon pokemon1 = createPokemon(firstPokemonName);

    System.out.print("Enter the name of your second Pokémon: ");
    String secondPokemonName = scanner.nextLine();
    Pokemon pokemon2 = createPokemon(secondPokemonName);

    if (pokemon1 == null || pokemon2 == null) {
        System.out.println("Could not create Pokémon. Exiting game.");
        return;
    }

    System.out.print("Enter the name of the healing item: ");
    String potionName = scanner.nextLine();
    Potion potion = createPotion(potionName);

    if (potion == null) {
        System.out.println("Could not create Potion. Exiting game.");
        return;
    }

    System.out.println("Begin the Pokémon battle!");
    Fight fight = new Fight(pokemon1, pokemon2, potion);
    fight.start();

    scanner.close();
}
```
### Example Gameplay 

Enter the name of your first Pokémon: ditto
Enter the name of your second Pokémon: Pidgeot 
Enter the name of the healing item: potion
Begin the Pokémon battle!

ditto [Current Health Level: 48, Current Power Level: 48]
pidgeot [Current Health Level: 83, Current Power Level: 80]
ditto's turn.
1. Attack
2. Use Potion
1
ditto attacks pidgeot for 48 damage!
Please press Enter to continue...


ditto [Current Health Level: 48, Current Power Level: 48]
pidgeot [Current Health Level: 35, Current Power Level: 80]
pidgeot's turn.
1. Attack
2. Use Potion
2
pidgeot healed for 20 health!
Please press Enter to continue...


ditto [Current Health Level: 48, Current Power Level: 48]
pidgeot [Current Health Level: 55, Current Power Level: 80]
ditto's turn.
1. Attack
2. Use Potion
1
ditto attacks pidgeot for 48 damage!
Please press Enter to continue...


ditto [Current Health Level: 48, Current Power Level: 48]
pidgeot [Current Health Level: 7, Current Power Level: 80]
pidgeot's turn.
1. Attack
2. Use Potion

-> Evaluation of Gameplay : There are some issues in the Gameplay that need to be addressed in the future , for example the possibility of a boring limitless game due to the current usage of the potion 

## Some Minor chnaged in the Project to improve User experience 

 at the beginning dont exit game if pokemon name is false , prompt user write right name <br>

 at the beginning dont exit game if potion name is false , prompt user write right name <br>

 at the beginning give users option to choose whether they want a potion or not <br>

### Game Class 

modified createPokemon method : <br>
added while loop : prompt the user for a valid Pokémon name until a valid name is given  <br>

```java
public Pokemon createPokemon(String pokemonName) {
    while (true) {
        try {
            JSONObject data = PokemonAPI.getPokemonData(pokemonName);
            String name = data.getString("name");
            int health = data.getJSONArray("stats").getJSONObject(0).getInt("base_stat");
            int attackPower = data.getJSONArray("stats").getJSONObject(1).getInt("base_stat");
            return new Pokemon(name, health, attackPower);
        } catch (Exception e) {
            System.out.println("Error fetching data for " + pokemonName + ". Please enter a valid Pokémon name:");
            Scanner scanner = new Scanner(System.in);
            pokemonName = scanner.nextLine();
        }
    }
}
```

modified createPotion method : <br>
added while loop :  prompt the user for a valid potion name until a valid name is provided <br>

```java
public Potion createPotion(String potionName) {
    while (true) {
        try {
            JSONObject data = PokemonAPI.getPotionData(potionName);
            int healingPower = PokemonAPI.getHealingPowerFromPotionData(data);
            return new Potion(healingPower);
        } catch (Exception e) {
            System.out.println("Error fetching data for " + potionName + ". Please enter a valid potion name:");
            Scanner scanner = new Scanner(System.in);
            potionName = scanner.nextLine();
        }
    }
}

```

modified startGame method : <br> 
ask user if they want to use a potion  <br>
if yes : prompt to enter the name of the potion, and call createPotion <br>
if no : proceeds without a potion <br>

```java
public void startGame() {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter the name of your first Pokémon: ");
    String firstPokemonName = scanner.nextLine();
    Pokemon pokemon1 = createPokemon(firstPokemonName);

    System.out.print("Enter the name of your second Pokémon: ");
    String secondPokemonName = scanner.nextLine();
    Pokemon pokemon2 = createPokemon(secondPokemonName);

    Potion potion = null;
    System.out.print("Do you want to use a healing potion in the battle? (yes/no): ");
    String usePotion = scanner.nextLine();
    if (usePotion.equalsIgnoreCase("yes")) {
        System.out.print("Enter the name of the healing item: ");
        String potionName = scanner.nextLine();
        potion = createPotion(potionName);
    }

    System.out.println("Begin the Pokémon battle!");
    Fight fight = new Fight(pokemon1, pokemon2, potion);
    fight.start();

    scanner.close();
}

```



### Fight Class 
modified start method : <br>
display option to use a potion only if a potion was given<br>

```java
public void start() {
    Scanner scanner = new Scanner(System.in);
    Pokemon currentAttacker = pokemon1;
    Pokemon currentDefender = pokemon2;

    while (!pokemon1.hasFainted() && !pokemon2.hasFainted()) {
        System.out.println("\n" + pokemon1.pokedex());
        System.out.println(pokemon2.pokedex());

        boolean validChoice = false;
        while (!validChoice) {
            System.out.println(currentAttacker.getName() + "'s turn.");
            System.out.println("1. Attack");
            if (potion != null) {
                System.out.println("2. Use Potion");
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                currentAttacker.attack(currentDefender);
                validChoice = true;
            } else if (choice == 2 && potion != null) {
                currentAttacker.heal(potion.getHealingPower());
                validChoice = true;
            } else {
                System.out.println("Invalid choice. Please type 1 to Attack" + (potion != null ? " or 2 to Use Healing Potion." : "."));
            }
        }

        if (currentDefender.hasFainted()) {
            System.out.println(currentDefender.getName() + " has fainted! Please bring to Pokémon Center to recover! " + currentAttacker.getName() + " is the winner!");
            break;
        }

        Pokemon temp = currentAttacker;
        currentAttacker = currentDefender;
        currentDefender = temp;

        System.out.println("Please press Enter to continue...");
        scanner.nextLine();
    }

    scanner.close();
}
```

### Game Play Experience Example

Enter the name of your first Pokémon: ditto <br>
Enter the name of your second Pokémon: pikachu <br>
Do you want to use a healing potion in the battle? (yes/no): no <br>
Begin the Pokémon battle! <br>

ditto [Current Health Level: 48, Current Power Level: 48] <br>
pikachu [Current Health Level: 35, Current Power Level: 55] <br>
ditto's turn. <br>
1. Attack <br>
1 <br>
ditto attacks pikachu for 48 damage! <br>
pikachu has fainted! <br>
pikachu has fainted! Please bring to Pokémon Center to recover! ditto is the winner! <br>

-> The pokemon api only has potion as an option to heal hp <br>
 <br>
-> Ask users to type in potion yes/no instead of a specific one  <br>
OR :  <br>
-> To make it more fun : for this task dont use pokeapi but do on my own , by either : <br> 
    -> generating healing potion randomly in a certain range (add luck element)<br>
                                or : <br>
    -> let users choose on their own  <br>

## Changes due to previously described potion issue 

task I am implementing here : generating healing potion randomly in a certain range <br> 
also :  <br> 
Pokémon cannot heal more than their maximum health <br> 
If a Pokémon is at its maximum health and tries to use a potion, force them to choose to attack instead <br> 
range : between 10 and 50 <br>

Remove API fetch to get potion data<br> 
Random class for generation <br> 

### Modified Potion class

import Random class <br>
healingPower variable stores how much health the potion can restore <br>
minHealingPower and maxHealingPower for the range of 10 to 50  <br>
generate the healing boos by : <br>
    1. Generates a random integer between 0 (inclusive) and 41 (exclusive)-> 0 to 40 <br>
    2. do plus 10 -> move range by 10 -> 10 to 50 <br>

```java
package com.example;

import java.util.Random;

public class Potion {
    private int healingPower;

    public Potion() {
        Random random = new Random();
        this.healingPower = random.nextInt(41) + 10;
    }

    public int getHealingPower() {
        return healingPower;
    }
}
```
### modified Game class 

reflect the modified potion class in createPotion method 

```java
 public Potion createPotion() {
        return new Potion();
    }
```

```java
// modification inside the startGame method
potion = createPotion();
```

don´t ask for potion name but return potion health boost 

```java
Potion potion = null;
        System.out.print("Do you want to use a healing potion in the battle? (yes/no): ");
        String usePotion = scanner.nextLine();
        if (usePotion.equalsIgnoreCase("yes")) {
            potion = createPotion();
            System.out.println("This potion has been created with a healing power of " + potion.getHealingPower());
        }
```

### Modified  Pokemon class

modified heal method : <br>
let only heal up to maxhealth of pokemon<br>
<br>
```java
 public void heal(int amount) {
        if (this.health == this.maxHealth) {
            System.out.println(this.name + " is already at full health and cannot be healed.");
            return;
        }
        int healedAmount = amount;
        if (this.health + amount > this.maxHealth) { 
            healedAmount = this.maxHealth - this.health;
            this.health = this.maxHealth; 
        } else {
            this.health += amount; 
        }
        System.out.println(this.name + " healed by " + healedAmount + " hp points!");
    }
```

new method : isAtMaxHealth : checks out if pokemon is already at its best health -> used for fight class to choose whether healing option is there or force attack 


```java
 public boolean isAtMaxHealth() {
        return this.health == this.maxHealth;
    }
```

### Modified PokemonAPI class

just delete all the potion fetch stuff

```java
package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class PokemonAPI {
    private static final String POKEAPI_URL = "https://pokeapi.co/api/v2/pokemon/";

    public static JSONObject getPokemonData(String pokemonName) throws Exception {
        String pokemonURL = POKEAPI_URL + pokemonName.toLowerCase();
        URL url = new URL(pokemonURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();
        conn.disconnect();

        return new JSONObject(response.toString());
    }
}
```

### Modified  Fight class

display  healing power of the potion in the choice menu <br>
check if current attacker is at max health before allowing for usage of healing potion  <br>
show by how much healed  <br>
If Pokemon is at max health force to attack <br>


```java
public void start() {
        Scanner scanner = new Scanner(System.in);
        Pokemon currentAttacker = pokemon1;
        Pokemon currentDefender = pokemon2;

        while (!pokemon1.hasFainted() && !pokemon2.hasFainted()) {
            System.out.println("\n" + pokemon1.pokedex());
            System.out.println(pokemon2.pokedex());

            boolean validChoice = false;
            while (!validChoice) {
                System.out.println(currentAttacker.getName() + "'s turn.");
                System.out.println("1. Attack");
                if (potion != null && !currentAttacker.isAtMaxHealth()) { 
                    System.out.println("2. Use Potion (heals " + potion.getHealingPower() + " HP)");
                }

                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 1) {
                    currentAttacker.attack(currentDefender);
                    validChoice = true;
                } else if (choice == 2 && potion != null && !currentAttacker.isAtMaxHealth()) { 
                    currentAttacker.heal(potion.getHealingPower());
                    validChoice = true;
                } else {
                    System.out.println("Invalid choice. Please type 1 to Attack" + (potion != null && !currentAttacker.isAtMaxHealth() ? " or 2 to Use Healing Potion." : "."));
                }
            }

            if (currentDefender.hasFainted()) {
                System.out.println(currentDefender.getName() + " has fainted! Please bring to Pokémon Center to recover! " + currentAttacker.getName() + " is the winner!");
                break;
            }

            Pokemon temp = currentAttacker;
            currentAttacker = currentDefender;
            currentDefender = temp;

            System.out.println("Please press Enter to continue...");
            scanner.nextLine();
        }

        scanner.close();
    }

```

## New Gameplay Experience Example

Enter the name of your first Pokémon: ditto <br>
Enter the name of your second Pokémon: pokemon  <br>
Error fetching data for pokemon . Please enter a valid Pokémon name: <br>
pikachu <br>
Do you want to use a healing potion in the battle? (yes/no): yes <br>
This potion has been created with a healing power of 36 <br>
Begin the Pokémon battle! <br>
 <br>
ditto [Current Health Level: 48, Current Power Level: 48] <br>
pikachu [Current Health Level: 35, Current Power Level: 55] <br>
ditto's turn. <br>
1. Attack <br>
1 <br>
ditto attacks pikachu for 48 damage! <br>
pikachu has fainted! <br>
pikachu has fainted! Please bring to Pokémon Center to recover! ditto is the winner! <br>
 <br>
 <br>
Another example :  <br>
 <br>
Enter the name of your first Pokémon: ditto <br>
Enter the name of your second Pokémon: eevee <br>
Do you want to use a healing potion in the battle? (yes/no): yes <br>
This potion has been created with a healing power of 32 <br>
Begin the Pokémon battle! <br>
 <br>
ditto [Current Health Level: 48, Current Power Level: 48] <br>
eevee [Current Health Level: 55, Current Power Level: 55] <br>
ditto's turn. <br>
1. Attack <br>
1 <br>
ditto attacks eevee for 48 damage! <br>
Please press Enter to continue... <br>
 <br>
 <br>
ditto [Current Health Level: 48, Current Power Level: 48] <br>
eevee [Current Health Level: 7, Current Power Level: 55] <br>
eevee's turn. <br>
1. Attack <br>
2. Use Potion (heals 32 HP) <br>
2 <br>
eevee healed by 32 hp points! <br>
Please press Enter to continue... <br>
 <br>
 <br>
ditto [Current Health Level: 48, Current Power Level: 48] <br>
eevee [Current Health Level: 39, Current Power Level: 55] <br>
ditto's turn. <br>
1. Attack <br>
1 <br>
ditto attacks eevee for 48 damage! <br>
eevee has fainted! <br>
eevee has fainted! Please bring to Pokémon Center to recover! ditto is the winner! <br>

## Remove double message of "has fainted"

achieve this by : Remove the message from the hasFainted() method , only have fainting message in the Fight class

now the new gameplay looks like this : 

Enter the name of your first Pokémon: ditto <br>
Enter the name of your second Pokémon: ditto <br>
Do you want to use a healing potion in the battle? (yes/no): no  <br>
Begin the Pokémon battle!  <br>

ditto [Current Health Level: 48, Current Power Level: 48]  <br>
ditto [Current Health Level: 48, Current Power Level: 48]  <br>
ditto's turn.  <br>
1. Attack  <br>
1  <br>
ditto attacks ditto for 48 damage!  <br>
ditto has fainted! Please bring to Pokémon Center to recover! ditto is the winner!  <br>

## Let user know about the randomness of the healing potion 

modify message in Game class 

now the new gameplay looks like this :

Enter the name of your first Pokémon: ditto <br>
Enter the name of your second Pokémon: ditto <br>
Do you want to use a healing potion in the battle? (will be generated randomly in a range between 10 and 50) (yes/no): no <br>
Begin the Pokémon battle! <br>
<br>
ditto [Current Health Level: 48, Current Power Level: 48] <br>
ditto [Current Health Level: 48, Current Power Level: 48] <br>
ditto's turn. <br>
1. Attack <br>
1 <br>
ditto attacks ditto for 48 damage! <br>
ditto has fainted! Please bring to Pokémon Center to recover! ditto is the winner! <br>

## Trying to add sound

turned out to be a failure , always got the message that the file type was unsupported and even though I tried out different ways and checked the file type , it still didnt work . <br>
I tried out JLayer library and JavaFX . <br>

So I did my best to revert the code back to before I did the changes for addition of soundeffects , as I dont have enough time to solve that issue right now . 






```java
```



```java
```




```java
```
