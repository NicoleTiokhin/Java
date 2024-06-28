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
- potions to heal
- Wild Pokémon Encounters
- add money to buy potions and stuff
- Pokémon Catching
- NPC trainers and gym battles with  rewards for winning
- simple animations for attacks
- switching Pokémon mid-battle
- Stamina : use certain moves a limited number of times before needing to rest or use a different move
- Pokémon Centers to heal Pokémon
- Sound and Music
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

## Some Minor chnaged in the Project to improve User experience 

### at the beginning dont exit game if pokemon name is false , prompt user write right name 

### at the beginning dont exit game if potion name is false , prompt user write right name 

### at the beginning give users option to choose whether they want a potion or not 






