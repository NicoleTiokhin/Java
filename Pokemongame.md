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
- simple animations 
- ~~switching Pokémon mid-battle~~ **done**
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


## Add : switching Pokémon mid-battle

I´ll have to modify the Fight class to have the option to switch Pokémon mid-battle <br>
I´ll have to change the Game class accordingly <br>

### Modified Fight class
instead of pokemon1 and pokemon2 use ArrayList<Pokemon>  (dynamically resizable)

```java
private ArrayList<Pokemon> team1;
private ArrayList<Pokemon> team2;
```

change start Method : 
initialize currentAttacker and currentDefender using the first Pokémon from each team <br>
loop continues as long as not all Pokémon in either team have fainted <br>
 
added methods that are called in start Method for the Game logic : <br> 
getIntInput <br>
allFainted <br>
getTeamStatus <br>
printTeam <br>
switchToNext <br>

getIntInput Method :  <br>
make sure input received from the user is an integer <br>
implemented after programm crashing when entered a string <br>
using an infinite loop <br>
tries to read an integer from the scanner <br>
if the input is valid, it returns the integer. <br>
else print an error message and clear the invalid input using scanner.next() <br>

```java
private int getIntInput(Scanner scanner) {
    while (true) {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); 
        }
    }
}

```

allFainted Method : <br>
check if all Pokémon in a team have fainted -> that  team has lost  <br>
iterate over each Pokemon in the team <br>
check if any Pokemon has not fainted -> if any pokemon has not fainted return false  <br>
otherwise true  <br>

```java
private boolean allFainted(ArrayList<Pokemon> team) {
    for (Pokemon p : team) {
        if (!p.hasFainted()) {
            return false;
        }
    }
    return true;
}

```

getTeamStatus Method: <br>
get a string that shows the current health and power level of each Pokémon in a team <br>
iterate over each Pokemon in the team <br>
append the pokedex information of each Pokemon to the StringBuilder <br>
converts the StringBuilder to a string and returns it <br>
```java
private String getTeamStatus(ArrayList<Pokemon> team) {
    StringBuilder status = new StringBuilder("Team Status:\n");
    for (Pokemon p : team) {
        status.append(p.pokedex()).append("\n");
    }
    return status.toString();
}

```
 
 printTeam Method: <br>
 print the list of Pokémon in a team <br>
 iterate over the team <br>
 return pokemon in team that are not the currentPokemon and have not fainted <br>


```java
private void printTeam(ArrayList<Pokemon> team, Pokemon currentPokemon) {
    for (int i = 0; i < team.size(); i++) {
        if (team.get(i) != currentPokemon && !team.get(i).hasFainted()) {
            System.out.println((i + 1) + ". " + team.get(i).getName() + " (Health: " + team.get(i).getHealth() + ")");
        }
    }
}

```

switchToNext Method :  <br>
automatically select the next viable Pokémon in the team when the current defender faints <br>
iterate over the team  <br>
return the first Pokemon found that has not fainted <br>
if all Pokemon have fainted, returns null <br>


```java
private Pokemon switchToNext(ArrayList<Pokemon> team) {
    for (Pokemon p : team) {
        if (!p.hasFainted()) {
            return p;
        }
    }
    return null;
}
```

### Modified Game Class 

New method : createTeam  <br>
create a team of Pokémon by asking the user for the number of Pokémon and their names <br>
ask the user how many Pokémon they want in the team <br>
loop continues until a valid integer is entered
use try and catch block to attempt to read an integer from the user and clear the invalid input , if not 
loop to Add Pokémon to the Team
runs from 1 to the size of the team 
create a Pokemon object for each pokemon 
add the created Pokemon object to the team list
return the created team

```java
public Pokemon createPokemon(String pokemonName) {
        while (!validInput) {
            System.out.print("How many Pokémon do you want to have in " + teamName + "? ");
            try {
                teamSize = scanner.nextInt();
                scanner.nextLine(); 
                validInput = true; 
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }
        for (int i = 1; i <= teamSize; i++) {
            System.out.print("Please enter the name of Pokémon " + i + " for " + teamName + ": ");
            String pokemonName = scanner.nextLine();
            team.add(createPokemon(pokemonName));
        }
    
        return team;
    }
```

 
modified : startGame Method <br>
modfied to use the new createTeam method  <br>

```java
public void startGame() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Create the first team:");
    ArrayList<Pokemon> team1 = createTeam("first");

    System.out.println("Create the second team:");
    ArrayList<Pokemon> team2 = createTeam("second");

    Potion potion = null;
    System.out.print("Do you want to use a healing potion in the battle? (will be generated randomly in a range between 10 and 50) (yes/no): ");
    String usePotion = scanner.nextLine();
    if (usePotion.equalsIgnoreCase("yes")) {
        potion = createPotion();
        System.out.println("This potion has been created with a healing power of " + potion.getHealingPower());
    }

    System.out.println("Begin the Pokémon battle!");
    Fight fight = new Fight(team1, team2, potion); 
    fight.start();

    scanner.close();
}
```

## Fix Game Logic Error 

The Game Logic Error :  you can switch to a different pokemon even when you only had one in your team and let you switch to a pokemon from another team <br> 
-> restrict switching to only valid, non-fainted Pokémon within the current player's team <br>

Prevent Switching if Only One Pokémon in the Team

```java
if (currentAttackerTeam.size() > 1) {
    System.out.println("3. Switch Pokémon");
}

```

Allow Switching Only Within the Same Team
added currentAttackerTeam to keep track of which team the current attacker belongs to  <br>
make sure it is a different, non-fainted Pokémon within the current attacker's team  <br>

```java
if (choice == 3 && currentAttackerTeam.size() > 1) {
    System.out.println("What Pokemon do you want to switch to?");
    printTeam(currentAttackerTeam, currentAttacker);
    int switchChoice = getIntInput(scanner);
    if (switchChoice > 0 && switchChoice <= currentAttackerTeam.size() && currentAttackerTeam.get(switchChoice - 1) != currentAttacker && !currentAttackerTeam.get(switchChoice - 1).hasFainted()) {
        currentAttacker = currentAttackerTeam.get(switchChoice - 1);
        validChoice = true;
    } else {
        System.out.println("Invalid choice. Please select a valid Pokémon to switch to.");
    }
}

```

 Alternate Teams
 if the current attacker's team was team1, it switches to team2 in the round afterand vice versa
 

```java
Pokemon temp = currentAttacker;
currentAttacker = currentDefender;
currentDefender = temp;

currentAttackerTeam = (currentAttackerTeam == team1) ? team2 : team1;

```

Some additional changes made when I saw during testing the gameplay that the defender attacker roles werent completely changed the right way : 


currentDefenderTeam to keep track of which team the current defender belongs to

```java
ArrayList<Pokemon> currentAttackerTeam = team1;
ArrayList<Pokemon> currentDefenderTeam = team2;

```



if the defender faints, check if all Pokémon in the defender's team have fainted. <br>
if yes, the attacker wins. <br>
if not, we switch to the next available Pokémon in the defender's team <br>

if the defender does not faint, switch roles between the attacker and defender, and update their corresponding teams <br>

before , as in why the error was there : did not correctly handle switching roles based on the faint status of Pokémon , due to the switching logic being to simplistic <br>


```java
if (currentDefender.hasFainted()) {
    System.out.println(currentDefender.getName() + " has fainted! Please bring to Pokémon Center to recover!");
    if (allFainted(currentDefenderTeam)) {
        System.out.println(currentAttacker.getName() + " is the winner!");
        break;
    } else {
        currentDefender = switchToNext(currentDefenderTeam);
    }
} else {
    Pokemon temp = currentAttacker;
    currentAttacker = currentDefender;
    currentDefender = temp;

    ArrayList<Pokemon> tempTeam = currentAttackerTeam;
    currentAttackerTeam = currentDefenderTeam;
    currentDefenderTeam = tempTeam;
}

System.out.println("Please press Enter to continue...");
scanner.nextLine();

```

New error :  <br>
blissey's turn. <br>
1. Attack <br>
3. Switch Pokémon <br>
3 <br>
What Pokemon do you want to switch to? <br>

As you can see here I don´t have any Pokemon to change to , but i am still being required to give one , as in I still get option 3 offered <br>
in other words :  I can still choose option 3 even though my team 1 now only has 1 pokemon that hasn't fainted  <br>

where this error comes from : check for the size of the team only considers the total number of Pokémon and not the number of non-fainted Pokémon <br>

Add a new Method : countActivePokemon Method  <br>
(countDeadPokemon was the only alternative that came to mind ,but since they only faint that felt extreme and also countNotFaintedPokemon was too long) <br>
count the number of active (non-fainted) Pokémon in the team -> conditionally show the switch option <br>


change while loop in start Method to use countActivePokemon -> game continues as long as both teams have active Pokémon <br>
let switch condition only be shown  if there is more than one active Pokémon in the team using countActivePokemon Method  <br>



```java
public void start() {
        Scanner scanner = new Scanner(System.in);
        Pokemon currentAttacker = team1.get(0);
        Pokemon currentDefender = team2.get(0);
        ArrayList<Pokemon> currentAttackerTeam = team1;
        ArrayList<Pokemon> currentDefenderTeam = team2;

        while (countActivePokemon(team1) > 0 && countActivePokemon(team2) > 0) {
            System.out.println("\n" + getTeamStatus(team1));
            System.out.println(getTeamStatus(team2));

            boolean validChoice = false;
            while (!validChoice) {
                System.out.println(currentAttacker.getName() + "'s turn.");
                System.out.println("1. Attack");
                if (potion != null && !currentAttacker.isAtMaxHealth()) {
                    System.out.println("2. Use Potion (heals " + potion.getHealingPower() + " HP)");
                }
                if (countActivePokemon(currentAttackerTeam) > 1) {
                    System.out.println("3. Switch Pokémon");
                }

                int choice = getIntInput(scanner);

                if (choice == 1) {
                    currentAttacker.attack(currentDefender);
                    validChoice = true;
                } else if (choice == 2 && potion != null && !currentAttacker.isAtMaxHealth()) {
                    currentAttacker.heal(potion.getHealingPower());
                    validChoice = true;
                } else if (choice == 3 && countActivePokemon(currentAttackerTeam) > 1) {
                    System.out.println("What Pokemon do you want to switch to?");
                    printTeam(currentAttackerTeam, currentAttacker);
                    int switchChoice = getIntInput(scanner);
                    if (switchChoice > 0 && switchChoice <= currentAttackerTeam.size() && currentAttackerTeam.get(switchChoice - 1) != currentAttacker && !currentAttackerTeam.get(switchChoice - 1).hasFainted()) {
                        currentAttacker = currentAttackerTeam.get(switchChoice - 1);
                        validChoice = true;
                    } else {
                        System.out.println("Invalid choice. Please select a valid Pokémon to switch to.");
                    }
                } else {
                    System.out.println("Invalid choice. Please type 1 to Attack" +
                        (potion != null && !currentAttacker.isAtMaxHealth() ? " or 2 to Use Healing Potion" : "") +
                        (countActivePokemon(currentAttackerTeam) > 1 ? " or 3 to Switch Pokémon." : "."));
                }
            }

            if (currentDefender.hasFainted()) {
                System.out.println(currentDefender.getName() + " has fainted! Please bring to Pokémon Center to recover!");
                if (allFainted(currentDefenderTeam)) {
                    System.out.println(currentAttacker.getName() + " is the winner!");
                    break;
                } else {
                    currentDefender = switchToNext(currentDefenderTeam);
                }
            } else {
                Pokemon temp = currentAttacker;
                currentAttacker = currentDefender;
                currentDefender = temp;

                ArrayList<Pokemon> tempTeam = currentAttackerTeam;
                currentAttackerTeam = currentDefenderTeam;
                currentDefenderTeam = tempTeam;
            }

            System.out.println("Please press Enter to continue...");
            scanner.nextLine();
        }

        scanner.close();
    }

```
### New Gameplay experience 
Create the first team: <br>
How many Pokémon do you want to have in first? 2 <br>
Please enter the name of Pokémon 1 for first: ditto <br>
Please enter the name of Pokémon 2 for first: Blissey <br>
Create the second team: <br>
How many Pokémon do you want to have in second? 1 <br> 
Please enter the name of Pokémon 1 for second: pikachu <br>
Do you want to use a healing potion in the battle? (will be generated randomly in a range between 10 and 50) (yes/no): no <br>
Begin the Pokémon battle! <br>

Team Status: <br>
ditto [Current Health Level: 48, Current Power Level: 48] <br>
blissey [Current Health Level: 255, Current Power Level: 10] <br>

Team Status: <br>
pikachu [Current Health Level: 35, Current Power Level: 55] <br>

ditto's turn. <br>
1. Attack <br>
3. Switch Pokémon <br>
3 <br>
What Pokemon do you want to switch to? <br>
2. blissey (Health: 255) <br>
2 <br>
Please press Enter to continue... <br>

Team Status: <br>
ditto [Current Health Level: 48, Current Power Level: 48] <br>
blissey [Current Health Level: 255, Current Power Level: 10] <br>

Team Status: <br>
pikachu [Current Health Level: 35, Current Power Level: 55] <br>

pikachu's turn. <br>
1. Attack <br>
1 <br>
pikachu attacks blissey for 55 damage! <br>
Please press Enter to continue... <br>

Team Status:  <br>
ditto [Current Health Level: 48, Current Power Level: 48]  <br>
blissey [Current Health Level: 200, Current Power Level: 10] <br>

Team Status: <br>
pikachu [Current Health Level: 35, Current Power Level: 55] <br>

blissey's turn. <br>
1. Attack <br>
3. Switch Pokémon <br>
1 <br>
blissey attacks pikachu for 10 damage! <br>
Please press Enter to continue... <br>

Team Status: <br>
ditto [Current Health Level: 48, Current Power Level: 48]<br>
blissey [Current Health Level: 200, Current Power Level: 10] <br>

Team Status: <br>
pikachu [Current Health Level: 25, Current Power Level: 55] <br>

pikachu's turn. <br> 
1. Attack <br>
1 <br>
pikachu attacks blissey for 55 damage! <br>
Please press Enter to continue... <br>

Team Status: <br>
ditto [Current Health Level: 48, Current Power Level: 48] <br>
blissey [Current Health Level: 145, Current Power Level: 10] <br>

Team Status: <br>
pikachu [Current Health Level: 25, Current Power Level: 55] <br>

blissey's turn. <br>
1. Attack  <br>
3. Switch Pokémon <br>
3 <br>
What Pokemon do you want to switch to? <br>
1. ditto (Health: 48) <br>
1 <br>
Please press Enter to continue... <br>

Team Status: <br>
ditto [Current Health Level: 48, Current Power Level: 48] <br>
blissey [Current Health Level: 145, Current Power Level: 10] <br>

Team Status: <br>
pikachu [Current Health Level: 25, Current Power Level: 55] <br>

pikachu's turn. <br>
1. Attack <br>
1 <br>
pikachu attacks ditto for 55 damage! <br>
ditto has fainted! Please bring to Pokémon Center to recover!<br>
Please press Enter to continue... <br>

Team Status: <br>
ditto [Current Health Level: -7, Current Power Level: 48] <br> 
blissey [Current Health Level: 145, Current Power Level: 10] <br>

Team Status: <br>
pikachu [Current Health Level: 25, Current Power Level: 55] <br>

pikachu's turn. <br>
1. Attack <br>
1 <br>
pikachu attacks blissey for 55 damage!<br>
Please press Enter to continue... <br>

Team Status: <br>
ditto [Current Health Level: -7, Current Power Level: 48] <br>
blissey [Current Health Level: 90, Current Power Level: 10] <br>

Team Status: <br>
pikachu [Current Health Level: 25, Current Power Level: 55] <br>

blissey's turn. <br>
1. Attack <br>
1 <br>
blissey attacks pikachu for 10 damage! <br>
Please press Enter to continue... <br>

Team Status: <br>
ditto [Current Health Level: -7, Current Power Level: 48] <br>
blissey [Current Health Level: 90, Current Power Level: 10] <br>

Team Status: <br>
pikachu [Current Health Level: 15, Current Power Level: 55] <br>

pikachu's turn. <br>
1. Attack <br>
1 <br>
pikachu attacks blissey for 55 damage! <br>
Please press Enter to continue... <br>

Team Status: <br>
ditto [Current Health Level: -7, Current Power Level: 48] <br>
blissey [Current Health Level: 35, Current Power Level: 10] <br>

Team Status: <br>
pikachu [Current Health Level: 15, Current Power Level: 55] <br>

blissey's turn. <br>
1. Attack <br>
1 <br>
blissey attacks pikachu for 10 damage! <br>
Please press Enter to continue... <br>

Team Status: <br>
ditto [Current Health Level: -7, Current Power Level: 48] <br>
blissey [Current Health Level: 35, Current Power Level: 10] <br>

Team Status: <br>
pikachu [Current Health Level: 5, Current Power Level: 55] <br>

pikachu's turn. <br>
1. Attack <br>
1 <br>
pikachu attacks blissey for 55 damage! <br>
blissey has fainted! Please bring to Pokémon Center to recover! <br>
pikachu is the winner! <br>

## Small modifications : Let User decide on Team Name 

modify the createTeam method to add a teamName parameter and of course the startGame method has to be modified accordingly 

### Modifed Game class 

modify createTeam method :  <br>
String parameter teamName  <br>



```java
 public ArrayList<Pokemon> createTeam(String teamName) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Pokemon> team = new ArrayList<>();

        
        int teamSize = 0;
        boolean validInput = false;
        
        while (!validInput) {
            System.out.print("How many Pokémon do you want to have in " + teamName + "? ");
            try {
                teamSize = scanner.nextInt();
                scanner.nextLine();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }

        for (int i = 1; i <= teamSize; i++) {
            System.out.print("Please enter the name of Pokémon " + i + " for " + teamName + ": ");
            String pokemonName = scanner.nextLine();
            team.add(createPokemon(pokemonName));
        }

        return team;
    }
```

modified startGame Method : 
prompt the user to enter the name of the teams
call the createTeam method, pass team1Name to it 

```java
System.out.print("Enter the name of your first team: ");
String team1Name = scanner.nextLine();
ArrayList<Pokemon> team1 = createTeam(team1Name);

System.out.print("Enter the name of your second team: ");
String team2Name = scanner.nextLine();
ArrayList<Pokemon> team2 = createTeam(team2Name);

```


And other changes in terms of user prompts , using Team names when appropriate 


### Modfied Fight Class 



have two different methods for team status to show the name of the team when status is shown , so its easier to follow the Gameplay . <br>
I could´ve done it in a way where I used one method instead of two , but I didnt have the time , after I had already wasted it on trying to add sounds to the game and I wnated to add other gameplay elements .





```java
private String getTeam1Status(ArrayList<Pokemon> team1, String team1Name) {
        StringBuilder status = new StringBuilder(team1Name + " Team Status:\n");
        for (Pokemon p : team1) {
            status.append(p.pokedex()).append("\n");
        }
        return status.toString();
    }
    
    private String getTeam2Status(ArrayList<Pokemon> team2, String team2Name) {
        StringBuilder status = new StringBuilder(team2Name + " Team Status:\n");
        for (Pokemon p : team2) {
            status.append(p.pokedex()).append("\n");
        }
        return status.toString();
    }
```


### New Gameplay experience (example) 

Enter the name of your first team: team rocket <br>
How many Pokémon do you want to have in team rocket ? 3<br>
Please enter the name of Pokémon 1 for team rocket : snorlax<br>
Please enter the name of Pokémon 2 for team rocket : s<br>
Error fetching data for s. Please enter a valid Pokémon name: Mew<br>
Please enter the name of Pokémon 3 for team rocket : meowth<br>
Enter the name of your second team: girlbossgatekeepgaslight <br>
How many Pokémon do you want to have in girlbossgatekeepgaslight? 2<br>
Please enter the name of Pokémon 1 for girlbossgatekeepgaslight: pikachu<br>
Please enter the name of Pokémon 2 for girlbossgatekeepgaslight: ditto<br>
Do you want to use a healing potion in the battle? (will be generated randomly in a range between 10 and 50) (yes/no): yes<br>
This potion has been created with a healing power of 42<br>
Begin the Pokémon battle!<br>

team rocket  Team Status:<br>
snorlax [Current Health Level: 160, Current Power Level: 110]<br>
mew [Current Health Level: 100, Current Power Level: 100]<br>
meowth [Current Health Level: 40, Current Power Level: 45]<br>

girlbossgatekeepgaslight Team Status:<br>
pikachu [Current Health Level: 35, Current Power Level: 55]<br>
ditto [Current Health Level: 48, Current Power Level: 48]<br>

snorlax's turn.<br>
1. Attack<br>
3. Switch Pokémon<br>
1<br>
snorlax attacks pikachu for 110 damage!<br>
pikachu has fainted! Please bring to Pokémon Center to recover!<br>
Please press Enter to continue...<br>
<br>
team rocket  Team Status:<br>
snorlax [Current Health Level: 160, Current Power Level: 110]<br>
mew [Current Health Level: 100, Current Power Level: 100]<br>
meowth [Current Health Level: 40, Current Power Level: 45]<br>
<br>
girlbossgatekeepgaslight Team Status:<br>
pikachu [Current Health Level: -75, Current Power Level: 55]<br>
ditto [Current Health Level: 48, Current Power Level: 48]<br>
<br>
snorlax's turn.<br>
1. Attack<br>
3. Switch Pokémon<br>
1<br>
snorlax attacks ditto for 110 damage!<br>
ditto has fainted! Please bring to Pokémon Center to recover!<br>
snorlax is the winner!<br>



## Modifiying Healing Potions

I want every team to have to generate their own healing potion instead of one for all . 

### Modified Fight Class


```java
 private String team2Name;
 private Potion team1Potion;
```

have a currentPotion based on attacking team , and update all potion usage to use currentPotion instead 
```java
Potion currentPotion = team1Potion;
```

switch Potions When Turns Change


```java
currentPotion = (currentPotion == team1Potion) ? team2Potion : team1Potion;  
```

### Modified Game Class 

Separate Potions for Each Team

```java
Potion team1Potion = null;  
Potion team2Potion = null; 
```

Seperate Prompts for the potions 

```java
System.out.print("Do you want team 1 to use a healing potion in the battle? (will be generated randomly in a range between 10 and 50) (yes/no): ");
String useTeam1Potion = scanner.nextLine();
if (useTeam1Potion.equalsIgnoreCase("yes")) {
    team1Potion = createPotion();
    System.out.println("Team 1's potion has been created with a healing power of " + team1Potion.getHealingPower());
}
```


```java
System.out.print("Do you want team 2 to use a healing potion in the battle? (will be generated randomly in a range between 10 and 50) (yes/no): ");
String useTeam2Potion = scanner.nextLine();
if (useTeam2Potion.equalsIgnoreCase("yes")) {
    team2Potion = createPotion();
    System.out.println("Team 2's potion has been created with a healing power of " + team2Potion.getHealingPower());
}
```

Pass Both Potions to the Fight Constructor
```java
Fight fight = new Fight(team1, team2, team1Name, team2Name, team1Potion, team2Potion);
```


### New Gameplay Experience Example 
Enter the name of your first team: team rocket <br> 
How many Pokémon do you want to have in team rocket ? 1 <br> 
Please enter the name of Pokémon 1 for team rocket : ditto <br> 
Enter the name of your second team: :) <br> 
How many Pokémon do you want to have in :)? 2 <br> 
Please enter the name of Pokémon 1 for :): pikachu<br> 
Please enter the name of Pokémon 2 for :): eevee<br> 
Do you want to use a healing potion in the battle for team rocket ? (will be generated randomly in a range between 10 and 50) (yes/no): yes <br> 
team rocket ´s potion has been created with a healing power of 49 <br> 
Do you want to use a healing potion in the battle for :)? (will be generated randomly in a range between 10 and 50) (yes/no): yes <br> 
:)´s potion has been created with a healing power of 37 <br> 
Begin the Pokémon battle! <br> 
<br> 
team rocket  Team Status: <br> 
ditto [Current Health Level: 48, Current Power Level: 48] <br> 
<br> 
:) Team Status: <br> 
pikachu [Current Health Level: 35, Current Power Level: 55] <br> 
eevee [Current Health Level: 55, Current Power Level: 55] <br> 
<br> 
ditto's turn. <br> 
1. Attack <br> 
1 <br> 
ditto attacks pikachu for 48 damage! <br> 
pikachu has fainted! Please bring to Pokémon Center to recover! <br> 
Please press Enter to continue... <br> 
<br> 
team rocket  Team Status: <br> 
ditto [Current Health Level: 48, Current Power Level: 48] <br> 
<br> 
:) Team Status: <br> 
pikachu [Current Health Level: -13, Current Power Level: 55] <br> 
eevee [Current Health Level: 55, Current Power Level: 55] <br> 
<br> 
ditto's turn. <br> 
1. Attack <br> 
1 <br> 
ditto attacks eevee for 48 damage! <br> 
Please press Enter to continue... <br> 
<br> 
team rocket  Team Status:<br> 
ditto [Current Health Level: 48, Current Power Level: 48]<br> 
<br> 
:) Team Status:<br> 
pikachu [Current Health Level: -13, Current Power Level: 55]<br> 
eevee [Current Health Level: 7, Current Power Level: 55]<br> 
<br> 
eevee's turn.<br> 
1. Attack<br> 
2. Use Potion (heals 37 HP)<br> 
1<br> 
eevee attacks ditto for 55 damage!<br> 
ditto has fainted! Please bring to Pokémon Center to recover!<br> 
eevee is the winner!<br> 



## Add simple animation 

I want to add a progress bar animation in the terminal for a Pokémon losing health . <br>
Terminal because my Game runs in the Terminal , so it makes sense to do it like that . <br>

I would do so by displaying the progress bar and updating it based on current health of pokemon
I will try using JLine library <br>


### Modified Pokemon Class

displayHealthBar Methdod : <br>
displays the health bar <br>
bar will have a total of 20 segments <br>
use the proportion of the current health to the maximum health to calculate how many segments should be filled based on the current health  <br>
build the health bar string with an opening bracket [ and a closing bracket ]  <br>
and then fill the health bar string by looping through all 20 segments and add a # to bar as long as the segments that should be filled are less than the current index of segement ; otherwise add a space to the bar  <br>
move the Cursor Back to the Start of the Line for when overwriting of the previous line (like updating the health bar) is needed  <br>
print out the health bar on the terminal  <br>
flush the buffer to show health bar update immediately  <br>




```java
public void displayHealthBar(Terminal terminal) throws IOException {
        int totalSegments = 20; 
        int filledSegments = (health * totalSegments) / maxHealth;
        StringBuilder bar = new StringBuilder("[");
        for (int i = 0; i < totalSegments; i++) {
            if (i < filledSegments) {
                bar.append("#"); 
            } else {
                bar.append(" ");
            }
        }
        bar.append("] ").append(health).append("/").append(maxHealth);

        terminal.puts(InfoCmp.Capability.carriage_return);
        terminal.writer().print(bar.toString());
        terminal.flush();
    }

```


animatedHealthChange Methdod : <br>
animate the change in a Pokémon's health<br>
calculate the new health by adding the health change to the current health<br>
check that the new health does not exceed the maximum health or fall beneath 0 <br>
animation will consist of 20 steps (amount of segments), making the change appear gradual <br>
alculate how much the health should change in each step by dividing the total health change by the number of steps <br>
loop through each step of the animation  <br>
increment the health by the calculated step amount  <br>
update the health bar display in the terminal to reflect the current health.  <br>
pause for 50 milliseconds to create a visible animation effect  <br>


```java
 public void animatedHealthChange(int healthChange, Terminal terminal) throws IOException, InterruptedException {
        int newHealth = this.health + healthChange;
        if (newHealth > maxHealth) newHealth = maxHealth;
        if (newHealth < 0) newHealth = 0;

        int steps = 20;
        int healthStep = healthChange / steps;

        for (int i = 0; i < steps; i++) {
            this.health += healthStep;
            if (this.health > maxHealth) this.health = maxHealth;
            if (this.health < 0) this.health = 0;
            displayHealthBar(terminal);
            Thread.sleep(50);
        }
        this.health = newHealth; 
        displayHealthBar(terminal);
    }
}
```

## Changes to improve the health bar "animation" 
Also I would like to see the health bar change when a potion is used to improve health <br>

modify the heal method to include the animatedHealthChange method call and Terminal object <br>
modify fight class accordingly <br>

### Modified Pokemon class 
modified heal Method : <br> 
call animatedHealthChange to show health bar animation when healing <br>


```java
public void heal(int amount, Terminal terminal) throws IOException, InterruptedException {
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
        animatedHealthChange(healedAmount, terminal);
    }
```

modified attack Method <br>
call animatedHealthChange for animated health reduction <br>

```java
public void attack(Pokemon target, Terminal terminal) throws IOException, InterruptedException {
        System.out.println(this.name + " attacks " + target.getName() + " for " + this.attackPower + " damage!");
        target.animatedHealthChange(-this.attackPower, terminal);
    }
```

### Updated Fight Method 

modified start Method <br>


```java
} else if (choice == 2 && currentPotion != null && !currentAttacker.isAtMaxHealth()) {
                    currentAttacker.heal(currentPotion.getHealingPower(), terminal);  
                    validChoice = true;
```

## Changes to improve the health bar "animation" 

I would like to see the health bar after the change and for it not to disappear . <br> 

### Modified Pokemon class 

modified displayHealthBar :  overwrite the current line 


```java
 public void displayHealthBar(Terminal terminal) throws IOException {
        int totalSegments = 20;
        int filledSegments = (health * totalSegments) / maxHealth;
        StringBuilder bar = new StringBuilder("[");
        for (int i = 0; i < totalSegments; i++) {
            if (i < filledSegments) {
                bar.append("#");
            } else {
                bar.append(" ");
            }
        }
        bar.append("] ").append(health).append("/").append(maxHealth);

        terminal.puts(InfoCmp.Capability.carriage_return);
        terminal.writer().print(bar.toString());
        terminal.flush();

    }
```

modified animatedHealthChange Method : 
overwrite the previous health bar during each step of the animation
after the loop, the final health bar state is displayed



```java
 public void animatedHealthChange(int healthChange, Terminal terminal) throws IOException, InterruptedException {
        int newHealth = this.health + healthChange;
        if (newHealth > maxHealth) newHealth = maxHealth;
        if (newHealth < 0) newHealth = 0;

        int steps = 20;
        int healthStep = healthChange / steps;

        for (int i = 0; i < steps; i++) {
            this.health += healthStep;
            if (this.health > maxHealth) this.health = maxHealth;
            if (this.health < 0) this.health = 0;
            displayHealthBar(terminal);
            Thread.sleep(50);
            terminal.puts(InfoCmp.Capability.carriage_return);
        }
        this.health = newHealth;
        displayHealthBar(terminal);
        System.out.println();
    }
```

### New Gameplay experience 
<br>
If you try out runnig the code you will also see the animation <br>
<br>
Enter the name of your first team: 1 <br>
How many Pokémon do you want to have in 1?  Eternatus       <br>    
Invalid input. Please enter a number. <br>
How many Pokémon do you want to have in 1? 1 <br>
Please enter the name of Pokémon 1 for 1:  Eternatus <br>
Error fetching data for  Eternatus. Please enter a valid Pokémon name:  Eternatus <br>
Enter the name of your second team: 2 <br>
How many Pokémon do you want to have in 2? 1 <br>
Please enter the name of Pokémon 1 for 2:  Eternatus <br>
Error fetching data for  Eternatus. Please enter a valid Pokémon name:  Eternatus <br>
Do you want to use a healing potion in the battle for 1? (will be generated randomly in a range between 10 and 50) (yes/no): yes <br>
1's potion has been created with a healing power of 10 <br>
Do you want to use a healing potion in the battle for 2? (will be generated randomly in a range between 10 and 50) (yes/no): yes <br>
2's potion has been created with a healing power of 45 <br>
Begin the Pokémon battle! <br>
 <br>
1 Team Status: <br>
eternatus [Current Health Level: 140, Current Power Level: 85] <br>
<br>
2 Team Status:<br>
eternatus [Current Health Level: 140, Current Power Level: 85]<br>
<br>
eternatus's turn.<br>
1. Attack<br>
1<br>
eternatus attacks eternatus for 85 damage!<br>
[#######             ] 55/1400<br>
<br>
Please press Enter to continue...<br>
<br>
1 Team Status:<br>
eternatus [Current Health Level: 140, Current Power Level: 85]<br>
etc <br>

## Add Type 
add to Pokemon class type String <br>
add calculateEffectiveness <br>
mofidy attack method accordingly <br>

for the Game class 
modify createPokemon Method to also fetch type info and type effectiveness<br>

### Modified Pokemon class 
Modified Attack Method
calculate effectiveness by calling getEffectiveness on the typeEffectiveness object, passing in the attacker's type and the target's type
calculate damage by multiplying the attack power by the effectiveness 



```java
public void attack(Pokemon target, Terminal terminal) throws IOException, InterruptedException {
    double effectiveness = typeEffectiveness.getEffectiveness(this.type, target.getType());
    int damage = (int) (this.attackPower * effectiveness);
    System.out.println(this.name + " attacks " + target.getName() + " for " + damage + " damage!");
    target.animatedHealthChange(-damage, terminal);
    System.out.println();
}

```


### TypeEffectiveness class 
to get type effectiveness data fetched from the API
doubleDamageTo: A list that keeps track of pairs where one type does double damage to another
halfDamageTo:  A list that keeps track of pairs where one type does half damage from another
noDamageTo: A list that keeps track of pairs where one type does no damage from another.


```java
public class TypeEffectiveness {
        private List<String[]> doubleDamageTo;
        private List<String[]> halfDamageTo;
        private List<String[]> noDamageTo;
    
        public TypeEffectiveness() {
            doubleDamageTo = new ArrayList<>();
            halfDamageTo = new ArrayList<>();
            noDamageTo = new ArrayList<>();
        }
```

addDoubleDamageTo Method :
add a pair where attackType does double damage to defenseType


addHalfDamageTo Method :
add a pair where attackType does half damage to defenseType

addNoDamageTo Method :
add a pair where attackType does no damage to defenseType


```java
public void addDoubleDamageTo(String attackType, String defenseType) {
            doubleDamageTo.add(new String[]{attackType, defenseType});
        }

        public void addHalfDamageTo(String attackType, String defenseType) {
            halfDamageTo.add(new String[]{attackType, defenseType});
        }

        public void addNoDamageTo(String attackType, String defenseType) {
            noDamageTo.add(new String[]{attackType, defenseType});
        }
        
```


getEffectiveness Method :
first check the doubleDamageTo list , return 2.0
then check the halfDamageTo list , return 0.5 
then check the noDamageTo list , return 0.0 .
otherwise return 1.0 




```java
public double getEffectiveness(String attackType, String defenseType) {
    for (String[] pair : doubleDamageTo) {
        if (pair[0].equals(attackType) && pair[1].equals(defenseType)) {
            return 2.0;
        }
    }
    for (String[] pair : halfDamageTo) {
        if (pair[0].equals(attackType) && pair[1].equals(defenseType)) {
            return 0.5;
        }
    }
    for (String[] pair : noDamageTo) {
        if (pair[0].equals(attackType) && pair[1].equals(defenseType)) {
            return 0.0;
        }
    }
    return 1.0; 
}

```

### modified PokemonAPI Class 

getTypeEffectiveness Method :
fetch type effectiveness data from the PokéAPI
construct the URL to fetch data from the PokéAPI for the given type
open a connection to the URL
read the response from the API into a StringBuilder
reads each line from the input stream and appends it to the response
convert the response string into a JSONObject
create an empty TypeEffectiveness object to store the type effectiveness data
extract the double_damage_to array from the JSON , loop through each element in the array , convert name of type into uppercase andd add type pair to the TypeEffectiveness object (e.g.: {"FIRE", "GRASS"} and {"FIRE", "ICE"} ice for doubleDamageTo )
same as for double_damage_to but for half damage and no damage 




```java
public static TypeEffectiveness getTypeEffectiveness(String typeName) throws Exception {
        String typeURL = POKEAPI_URL + "type/" + typeName.toLowerCase();
        URL url = new URL(typeURL);
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

        JSONObject json = new JSONObject(response.toString());
        TypeEffectiveness typeEffectiveness = new TypeEffectiveness();

        JSONArray doubleDamageToArray = json.getJSONObject("damage_relations").getJSONArray("double_damage_to");
        for (int i = 0; i < doubleDamageToArray.length(); i++) {
            String doubleDamageType = doubleDamageToArray.getJSONObject(i).getString("name").toUpperCase();
            typeEffectiveness.addDoubleDamageTo(typeName.toUpperCase(), doubleDamageType);
        }

        JSONArray halfDamageToArray = json.getJSONObject("damage_relations").getJSONArray("half_damage_to");
        for (int i = 0; i < halfDamageToArray.length(); i++) {
            String halfDamageType = halfDamageToArray.getJSONObject(i).getString("name").toUpperCase();
            typeEffectiveness.addHalfDamageTo(typeName.toUpperCase(), halfDamageType);
        }

        JSONArray noDamageToArray = json.getJSONObject("damage_relations").getJSONArray("no_damage_to");
        for (int i = 0; i < noDamageToArray.length(); i++) {
            String noDamageType = noDamageToArray.getJSONObject(i).getString("name").toUpperCase();
            typeEffectiveness.addNoDamageTo(typeName.toUpperCase(), noDamageType);
        }

        return typeEffectiveness;
    }
}
```
## Fix error 
I get this error : <br>
java.io.IOException: Error executing '/bin/stty -a': stty: stdin isn't a terminal <br>
        at org.jline.utils.ExecHelper.exec(ExecHelper.java:42)<br>
        at org.jline.terminal.impl.ExecPty.doGetConfig(ExecPty.java:163) <br>
        at org.jline.terminal.impl.ExecPty.getAttr(ExecPty.java:87)<br>
        at org.jline.terminal.impl.ExecPty.doSetAttr(ExecPty.java:93) <br>
        at org.jline.terminal.impl.AbstractPty.setAttr(AbstractPty.java:29) <br>
        at org.jline.terminal.impl.AbstractPosixTerminal.doClose(AbstractPosixTerminal.java:76)<br>
        at org.jline.terminal.impl.PosixSysTerminal.doClose(PosixSysTerminal.java:95)<br>
        at org.jline.terminal.impl.AbstractTerminal.close(AbstractTerminal.java:98)<br>
        at com.example.Game.startGame(Game.java:108)<br>
        at com.example.Game.main(Game.java:115)<br>
        at org.codehaus.mojo.exec.ExecJavaMojo$1.run(ExecJavaMojo.java:254)<br>
        at java.base/java.lang.Thread.run(Thread.java:833)<br>

The reason for this error is apparently is due to my environment (VSCode)

How I solved it :
Removed JLine Dependencies

Updated displayHealthBar Method : 
use simple console output instead of terminal 
instead of terminal.puts and terminal.writer().print use System.out.print

Updated animatedHealthChange Method: 
simple console output for health bar updates intead of terminal 

Updated attack and heal Method :
call animatedHealthChange without the terminal object


```java
public void displayHealthBar() {
    int totalSegments = 20;
    int filledSegments = (health * totalSegments) / maxHealth;
    StringBuilder bar = new StringBuilder("[");
    for (int i = 0; i < totalSegments; i++) {
        if (i < filledSegments) {
            bar.append("#");
        } else {
            bar.append(" ");
        }
    }
    bar.append("] ").append(health).append("/").append(maxHealth);
    System.out.print("\r" + bar.toString());
}

```


```java
public void animatedHealthChange(int healthChange) throws InterruptedException {
    int newHealth = this.health + healthChange;
    if (newHealth > maxHealth) newHealth = maxHealth;
    if (newHealth < 0) newHealth = 0;

    int steps = 20;
    int healthStep = healthChange / steps;

    for (int i = 0; i < steps; i++) {
        this.health += healthStep;
        if (this.health > maxHealth) this.health = maxHealth;
        if (this.health < 0) this.health = 0;
        displayHealthBar();
        Thread.sleep(50);
    }
    this.health = newHealth;
    displayHealthBar();
    System.out.println();
}
```


```java
public void attack(Pokemon target) throws InterruptedException {
    double effectiveness = typeEffectiveness.getEffectiveness(this.type, target.getType());
    int damage = (int) (this.attackPower * effectiveness);
    System.out.println(this.name + " attacks " + target.getName() + " for " + damage + " damage!");
    target.animatedHealthChange(-damage);
    System.out.println();
}

```


```java
public void heal(int amount) throws InterruptedException {
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
    animatedHealthChange(healedAmount);
}

```
## Small Change : Don´t let the teams choose the same name

### Modified Game class  
prompt the user to re-enter the second team name if it is the same as the first team name <br>
<br>
modified startGame method : <br>
while loop to continuously prompt user to enter a team name until its different from the other teams name <br>
if condition to check if the name is the same <br>
<br>
if second Team name is different from the first Team name (no matter the casing), then exit loop <br>
otherwise continue loop until not same name <br> 

```java
String team2Name;
ArrayList<Pokemon> team2;

while (true) {
    System.out.print("Enter the name of your second team: ");
    team2Name = scanner.nextLine();
    if (!team2Name.equalsIgnoreCase(team1Name)) {
        team2 = createTeam(team2Name);
        break;
    } else {
        System.out.println("The Team names must be different. Please enter a different name for your second team.");
    }
}

```

## Updates in Gameplay 

Enter the name of your first team: 1  <br>
How many Pokémon do you want to have in 1? 2 <br>
Please enter the name of Pokémon 1 for 1: eevee <br>
Please enter the name of Pokémon 2 for 1: pikachu <br>
Enter the name of your second team: 1 <br>
Team names must be different. Please enter a different name for the second team <br>
Enter the name of your second team: 2 <br>
How many Pokémon do you want to have in 2? 1 <br>
Please enter the name of Pokémon 1 for 2: eeve <br>


## Add multiple rounds to Pokemon Game 


I need to have a number of rounds <br>
I need to track what round I am on , to know after what round to end <br>
Determine winner based on sth ( since i have teams where i can have different amounts of pokemon it would make sense to determine winner by overall health lost , as the amount of pokemon would have strong influence in the long term of multiple rounds ) <br>
Make multiple rounds optional <br>

-> option for multiple rounds in Game class <br>
-> Fight class should be able to handle single-round and mutliple-round games  <br>
->calculate overall health lost in each team to determine the winner in multiple-round games <br>

### Modified Game Class 

modified startGame Method : 
ask user whether they want multiple- , or single-round games  (yes/no -> because easier to implement)
if yes : tell user to enter how many rounds they wanna play (totalRounds)
if no : totalRounds = 1 



```java
public void startGame() throws InterruptedException {
        System.out.print("Enter the name of your first team: ");
        String team1Name = scanner.nextLine();
        ArrayList<Pokemon> team1 = createTeam(team1Name);
    
        String team2Name;
        ArrayList<Pokemon> team2;
    
        while (true) {
            System.out.print("Enter the name of your second team: ");
            team2Name = scanner.nextLine();
            if (!team2Name.equalsIgnoreCase(team1Name)) {
                team2 = createTeam(team2Name);
                break;
            } else {
                System.out.println("Team names must be different. Please enter a different name for the second team.");
            }
        }
    
        Potion team1Potion = null;
        Potion team2Potion = null;
    
        System.out.print("Do you want team 1 to use a healing potion in the battle? (will be generated randomly in a range between 10 and 50) (yes/no): ");
        String useTeam1Potion = scanner.nextLine();
        if (useTeam1Potion.equalsIgnoreCase("yes")) {
            team1Potion = createPotion();
            System.out.println("Team 1's potion has been created with a healing power of " + team1Potion.getHealingPower());
        }
        System.out.print("Do you want team 2 to use a healing potion in the battle? (will be generated randomly in a range between 10 and 50) (yes/no): ");
        String useTeam2Potion = scanner.nextLine();
        if (useTeam2Potion.equalsIgnoreCase("yes")) {
            team2Potion = createPotion();
            System.out.println("Team 2's potion has been created with a healing power of " + team2Potion.getHealingPower());
        }
    
        System.out.print("Do you want to play multiple rounds? (yes/no): ");
        String multipleRoundsChoice = scanner.nextLine();
        int totalRounds = 1; 

        if (multipleRoundsChoice.equalsIgnoreCase("yes")) {
            System.out.print("How many rounds do you want to play ? : ");
            totalRounds = scanner.nextInt();
            scanner.nextLine();
        }
    
        System.out.println("Begin the Pokémon battle!");

        Fight fight = new Fight(team1, team2, team1Name, team2Name, team1Potion, team2Potion, totalRounds);
        try {
            fight.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    
        scanner.close();
    }
    
```
### Modified Fight class 

```java
```



```java
```



```java
```
