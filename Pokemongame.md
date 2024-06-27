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
- Add more pokemon
- Type Advantages
- Leveling Up
- potions to heal
- Wild Pokémon Encounters
- Pokémon Catching
- NPC trainers and gym battles with  rewards for winning
- simple animations for attacks
- switching Pokémon mid-battle
- Stamina : use certain moves a limited number of times before needing to rest or use a different move
- Pokémon Centers to heal Pokémon
- Sound and Music
- Save and Load Game
(- use pokemon API)
- Let users decide on their pokemon on their own by typing the name in

## Let users decide on their pokemon on their own by typing the name in

Using Pokemon API : https://pokeapi.co/docs/v2#pokemon

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

