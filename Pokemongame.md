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

### Pokemon Class 

What did I change ? 
package com.example; (I used the codespace on Github to add json library using maven)
otherwise same 


### New class : PokemonAPI to get Pokémon data from the API



```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

```


        



```java
```




```java
```
