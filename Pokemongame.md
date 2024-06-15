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
 
