```java
package com.example;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Fight {
    private ArrayList<Pokemon> team1;
    private ArrayList<Pokemon> team2;
    private String team1Name;
    private String team2Name;
    private Potion team1Potion;
    private Potion team2Potion;
    private int totalRounds;
    private int currentRound;
    private int team1TotalHealthLost;
    private int team2TotalHealthLost;
    private Weather weather;

    public Fight(ArrayList<Pokemon> team1, ArrayList<Pokemon> team2, String team1Name, String team2Name, Potion team1Potion, Potion team2Potion, int totalRounds, Weather weather) {
        this.team1 = team1;
        this.team2 = team2;
        this.team1Name = team1Name;
        this.team2Name = team2Name;
        this.team1Potion = team1Potion;
        this.team2Potion = team2Potion;
        this.totalRounds = totalRounds;
        this.currentRound = 1;
        this.team1TotalHealthLost = 0;
        this.team2TotalHealthLost = 0;
        this.weather = weather; 
    }

    public void start() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        while (currentRound <= totalRounds) {
            if (countActivePokemon(team1) > 0 && countActivePokemon(team2) > 0) {
                System.out.println("\nRound " + currentRound + " of " + totalRounds);
                fight();
                trackHealth();
                restoreHealth();
                currentRound++;
            } else {
                break;
            }
        }
        determineWinnerByHealth();
        scanner.close();
    }

    private void fight() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        Pokemon currentAttacker = team1.get(0);
        Pokemon currentDefender = team2.get(0);
        ArrayList<Pokemon> currentAttackerTeam = team1;
        ArrayList<Pokemon> currentDefenderTeam = team2;
        Potion currentPotion = team1Potion;

        while (countActivePokemon(team1) > 0 && countActivePokemon(team2) > 0) {
            System.out.println(getTeam1Status(team1, team1Name));
            System.out.println(getTeam2Status(team2, team2Name));

            boolean validChoice = false;
            while (!validChoice) {
                System.out.println(currentAttacker.getName() + "'s turn.");
                System.out.println("1. Attack");
                if (currentPotion != null && !currentAttacker.isAtMaxHealth()) {
                    System.out.println("2. Use Potion (heals " + currentPotion.getHealingPower() + " HP)");
                }
                if (countActivePokemon(currentAttackerTeam) > 1) {
                    System.out.println("3. Switch Pokémon");
                }

                int choice = getIntInput(scanner);

                if (choice == 1) {
                    if (weather != null) {
                        weather.applyWeatherEffects(currentAttacker, currentDefender); 
                    }
                    currentAttacker.attack(currentDefender); 
                    validChoice = true;
                } else if (choice == 2 && currentPotion != null && !currentAttacker.isAtMaxHealth()) {
                    currentAttacker.heal(currentPotion.getHealingPower());
                    validChoice = true;
                } else if (choice == 3 && countActivePokemon(currentAttackerTeam) > 1) {
                    System.out.println("What Pokémon do you want to switch to?");
                    printTeam(currentAttackerTeam, currentAttacker);
                    int switchChoice = getIntInput(scanner);
                    if (switchChoice > 0 && switchChoice <= currentAttackerTeam.size() && currentAttackerTeam.get(switchChoice - 1) != currentAttacker && !currentAttackerTeam.get(switchChoice - 1).hasFainted()) {
                        currentAttacker = currentAttackerTeam.get(switchChoice - 1);
                        validChoice = true;
                    } else {
                        System.out.print("Invalid choice. Please select a valid Pokémon to switch to.");
                    }
                } else {
                    System.out.println("Invalid choice. Please type 1 to Attack" +
                        (currentPotion != null && !currentAttacker.isAtMaxHealth() ? " or 2 to Use Healing Potion" : "") +
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

                currentPotion = (currentPotion == team1Potion) ? team2Potion : team1Potion;
            }

            System.out.println("Please press Enter to continue...");
            scanner.nextLine();
        }
    }

    
    private void determineWinnerByHealth() {
        System.out.println("Total health lost by " + team1Name + ": " + team1TotalHealthLost);
        System.out.println("Total health lost by " + team2Name + ": " + team2TotalHealthLost);
    
        if (team1TotalHealthLost < team2TotalHealthLost) {
            System.out.println(team1Name + " wins by losing less health!");
        } else if (team2TotalHealthLost < team1TotalHealthLost) {
            System.out.println(team2Name + " wins by losing less health!");
        } else {
            System.out.println("It's a draw!");
        }
    }

    private int calculateTotalHealthLost(ArrayList<Pokemon> team) {
        int totalHealthLost = 0;
        for (Pokemon p : team) {
            totalHealthLost += (p.getMaxHealth() - p.getHealth());
        }
        return totalHealthLost;
    }

    private void trackHealth() {
        team1TotalHealthLost += healthLostInRound(team1);
        team2TotalHealthLost += healthLostInRound(team2);
    }

    private int healthLostInRound(ArrayList<Pokemon> team) {
        int healthLost = 0;
        for (Pokemon p : team) {
            healthLost += (p.getMaxHealth() - p.getHealth());
        }
        return healthLost;
    }

    private void restoreHealth() {
        restoreTeamHealth(team1);
        restoreTeamHealth(team2);
    }

    private void restoreTeamHealth(ArrayList<Pokemon> team) {
        for (Pokemon p : team) {
            p.setHealth(p.getMaxHealth());
        }
    }

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

    private boolean allFainted(ArrayList<Pokemon> team) {
        for (Pokemon p : team) {
            if (!p.hasFainted()) {
                return false;
            }
        }
        return true;
    }
    
    private int countActivePokemon(ArrayList<Pokemon> team) {
        int count = 0;
        for (Pokemon p : team) {
            if (!p.hasFainted()) {
                count++;
            }
        }
        return count;
    }

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

    private void printTeam(ArrayList<Pokemon> team, Pokemon currentPokemon) {
        for (int i = 0; i < team.size(); i++) {
            if (team.get(i) != currentPokemon && !team.get(i).hasFainted()) {
                System.out.println((i + 1) + ". " + team.get(i).getName() + " (Health: " + team.get(i).getHealth() + ")");
            }
        }
    }

    private Pokemon switchToNext(ArrayList<Pokemon> team) {
        for (Pokemon p : team) {
            if (!p.hasFainted()) {
                return p;
            }
        }
        return null;
    }
}
 
```

```java

package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.json.JSONObject;
import java.awt.EventQueue;

public class Game {
    private Scanner scanner;

    public Game() {
        this.scanner = new Scanner(System.in);
    }

    public Pokemon createPokemon(String pokemonName) {
        while (true) {
            try {
                JSONObject data = PokemonAPI.getPokemonData(pokemonName.trim().toLowerCase());
                String name = data.getString("name");
                int health = data.getJSONArray("stats").getJSONObject(0).getInt("base_stat");
                int attackPower = data.getJSONArray("stats").getJSONObject(1).getInt("base_stat");
                String type = data.getJSONArray("types").getJSONObject(0).getJSONObject("type").getString("name").toUpperCase();
                TypeEffectiveness typeEffectiveness = PokemonAPI.getTypeEffectiveness(type);
                return new Pokemon(name, health, attackPower, type, typeEffectiveness);
            } catch (Exception e) {
                System.out.print("Error fetching data for " + pokemonName + ". Please enter a valid Pokémon name: ");
                pokemonName = scanner.nextLine().trim();
            }
        }
    }

    public Potion createPotion() {
        return new Potion();
    }

    public ArrayList<Pokemon> createTeam(String teamName) {
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
            Pokemon pokemon = createPokemon(pokemonName); 
            team.add(pokemon); 
        }


        return team;
    }

    public void startGame() throws InterruptedException {
        while (true) {
            System.out.print("Choose a game mode (simulation/main story): ");
            String gameMode = scanner.nextLine().trim().toLowerCase();

            if (gameMode.equals("simulation")) {
                runSimulation();
                break;
            } else if (gameMode.equals("main story")) {
                // Launch the GUI for main story mode
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        GameGUI gameGUI = new GameGUI();
                        gameGUI.setVisible(true);
                    }
                });
                break;
            } else {
                System.out.println("Invalid choice. Please enter 'simulation' or 'main story'.");
            }
        }

        scanner.close();
    }
    private void runSimulation() throws InterruptedException {
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
            System.out.print("How many rounds do you want to play? : ");
            totalRounds = scanner.nextInt();
            scanner.nextLine();
        }

        Weather weather = null;
        System.out.print("Do you want the weather to influence the battle? (yes/no): ");
        String weatherInfluence = scanner.nextLine();
        if (weatherInfluence.equalsIgnoreCase("yes")) {
            weather = Weather.generateRandomWeather();
            System.out.println("The weather condition for this battle is: " + weather.getCondition());
            switch (weather.getCondition().toLowerCase()) {
                case "sunny":
                    System.out.println("Influence: Boosts Fire-type attacks by 50%.");
                    break;
                case "rain":
                    System.out.println("Influence: Boosts Water-type attacks by 50%.");
                    break;
                case "hail":
                    System.out.println("Influence: Damages Attacking Pokémon by 10 HP after every action.");
                    break;
                case "sandstorm":
                    System.out.println("Influence: Damages Both Pokémon by 10 HP after every action.");
                    break;
            }
        }

    
        System.out.println("Begin the Pokémon battle!");

        Fight fight = new Fight(team1, team2, team1Name, team2Name, team1Potion, team2Potion, totalRounds, weather);
        try {
            fight.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Game game = new Game();
            game.startGame();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

```java
package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import org.json.JSONObject;

public class GameGUI extends JFrame implements KeyListener {
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JPanel introPanel;
    private JPanel gamePanel;
    private JLabel character;
    private int characterX = 100;
    private int characterY = 100;
    private Pokemon starterPokemon;

    public GameGUI() {
        setTitle("Pokemon main Story Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Introduction Panel
        introPanel = new JPanel();
        introPanel.setLayout(new BorderLayout());
        JLabel introLabel = new JLabel("Choose your starter Pokémon: Squirtle, Charmander, or Bulbasaur", JLabel.CENTER);
        introPanel.add(introLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton squirtleButton = new JButton("Squirtle");
        JButton charmanderButton = new JButton("Charmander");
        JButton bulbasaurButton = new JButton("Bulbasaur");

        squirtleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                starterPokemon = createPokemon("squirtle");
                startGame();
            }
        });

        charmanderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                starterPokemon = createPokemon("charmander");
                startGame();
            }
        });

        bulbasaurButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                starterPokemon = createPokemon("bulbasaur");
                startGame();
            }
        });

        buttonPanel.add(squirtleButton);
        buttonPanel.add(charmanderButton);
        buttonPanel.add(bulbasaurButton);
        introPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Game Panel
        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLUE);
                g.fillRect(characterX, characterY, 50, 50);
            }
        };
        gamePanel.setFocusable(true);
        gamePanel.addKeyListener(this);

        mainPanel.add(introPanel, "Intro");
        mainPanel.add(gamePanel, "Game");

        add(mainPanel);
    }

    private void startGame() {
        cardLayout.show(mainPanel, "Game");
        gamePanel.requestFocusInWindow();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            characterX -= 10;
        } else if (key == KeyEvent.VK_RIGHT) {
            characterX += 10;
        } else if (key == KeyEvent.VK_UP) {
            characterY -= 10;
        } else if (key == KeyEvent.VK_DOWN) {
            characterY += 10;
        }

        gamePanel.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameGUI game = new GameGUI();
                game.setVisible(true);
            }
        });
    }

    public Pokemon createPokemon(String pokemonName) {
        try {
            JSONObject data = PokemonAPI.getPokemonData(pokemonName.trim().toLowerCase());
            String name = data.getString("name");
            int health = data.getJSONArray("stats").getJSONObject(0).getInt("base_stat");
            int attackPower = data.getJSONArray("stats").getJSONObject(1).getInt("base_stat");
            String type = data.getJSONArray("types").getJSONObject(0).getJSONObject("type").getString("name").toUpperCase();
            TypeEffectiveness typeEffectiveness = PokemonAPI.getTypeEffectiveness(type);
            return new Pokemon(name, health, attackPower, type, typeEffectiveness);
        } catch (Exception e) {
            System.out.println("Error fetching data for " + pokemonName + ".");
            return null;
        }
    }
}

```

```java
package com.example;

import java.io.IOException;

public class Pokemon {
    private String name;
    private int health;
    private int attackPower;
    private int maxHealth;
    private String type;
    private TypeEffectiveness typeEffectiveness;

    public Pokemon(String name, int health, int attackPower, String type, TypeEffectiveness typeEffectiveness) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.maxHealth = health;
        this.type = type;
        this.typeEffectiveness = typeEffectiveness;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttackPower() {
        return attackPower;
    }
    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public String getType() {
        return type;
    }
    public void damage(int amount) {
        this.health -= amount;
        if (this.health < 0) this.health = 0;
    }
    public void attack(Pokemon target) throws InterruptedException {
        double effectiveness = typeEffectiveness.getEffectiveness(this.type, target.getType());
        int damage = (int) (this.attackPower * effectiveness);
        
        System.out.println(this.name + " attacks " + target.getName() + " for " + damage + " damage!");
    
        if (effectiveness > 1.0) {
            System.out.println("It's super effective!");
        } else if (effectiveness < 1.0 && effectiveness > 0.0) {
            System.out.println("It's not very effective...");
        } else if (effectiveness == 0.0) {
            System.out.println("It doesn't affect " + target.getName() + "...");
        }
    
        target.animatedHealthChange(-damage);
        System.out.println();
    }
    

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

    public boolean hasFainted() {
        return this.health <= 0;
    }

    public boolean isAtMaxHealth() {
        return this.health == this.maxHealth;
    }

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

    public String pokedex() {
        return name + " [Current Health Level: " + health + ", Current Power Level: " + attackPower + "]";
    }

}

```



```java
package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class PokemonAPI {
    private static final String POKEAPI_URL = "https://pokeapi.co/api/v2/";

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

    public static JSONObject getPokemonData(String pokemonName) throws Exception {
        String pokemonURL = POKEAPI_URL + "pokemon/" + pokemonName.toLowerCase();
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




```javapackage com.example;

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



```java
package com.example;

import java.util.ArrayList;
import java.util.List;

public class TypeEffectiveness {
    private List<String[]> doubleDamageTo;
    private List<String[]> halfDamageTo;
    private List<String[]> noDamageTo;

    public TypeEffectiveness() {
        doubleDamageTo = new ArrayList<>();
        halfDamageTo = new ArrayList<>();
        noDamageTo = new ArrayList<>();
    }

    public void addDoubleDamageTo(String attackType, String defenseType) {
        doubleDamageTo.add(new String[]{attackType, defenseType});
    }

    public void addHalfDamageTo(String attackType, String defenseType) {
        halfDamageTo.add(new String[]{attackType, defenseType});
    }

    public void addNoDamageTo(String attackType, String defenseType) {
        noDamageTo.add(new String[]{attackType, defenseType});
    }

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
}

```


```java
package com.example;

import java.util.Random;

public class Weather {
    private String condition;

    public Weather(String condition) {
        this.condition = condition;
    }

    public String getCondition() {
        return condition;
    }

    public void applyWeatherEffects(Pokemon attacker, Pokemon defender) {
        switch (condition.toLowerCase()) {
            case "sunny":
                if (attacker.getType().equals("FIRE")) {
                    attacker.setAttackPower((int) (attacker.getAttackPower() * 1.5));
                }
                break;
            case "rain":
                if (attacker.getType().equals("WATER")) {
                    attacker.setAttackPower((int) (attacker.getAttackPower() * 1.5));
                }
                break;
            case "hail":
                defender.damage(10);
                break;
            case "sandstorm":
                attacker.damage(10);
                defender.damage(10);
                break;
        }
    }

    public static Weather generateRandomWeather() {
        String[] weatherConditions = { "sunny", "rain", "hail", "sandstorm" };
        Random random = new Random();
        int index = random.nextInt(weatherConditions.length);
        return new Weather(weatherConditions[index]);
    }
}

```

.
├── JsonExample.java
├── dependency-reduced-pom.xml
├── pom.xml
├── src
│   ├── main
│   │   └── java
│   │       └── com
│   │           └── example
│   │               ├── Fight.java
│   │               ├── Game.java
│   │               ├── GameGUI.java
│   │               ├── Pokemon.java
│   │               ├── PokemonAPI.java
│   │               ├── Potion.java
│   │               ├── TypeEffectiveness.java
│   │               └── Weather.java
│   └── test
│       └── java
└── target
    ├── classes
    │   └── com
    │       └── example
    │           ├── Fight.class
    │           ├── Game$1.class
    │           ├── Game.class
    │           ├── GameGUI$1.class
    │           ├── GameGUI$2.class
    │           ├── GameGUI$3.class
    │           ├── GameGUI$4.class
    │           ├── GameGUI$5.class
    │           ├── GameGUI.class
    │           ├── Pokemon.class
    │           ├── PokemonAPI.class
    │           ├── Potion.class
    │           ├── TypeEffectiveness.class
    │           └── Weather.class
    ├── generated-sources
    │   └── annotations
    └── maven-status
        └── maven-compiler-plugin
            └── compile
                └── default-compile
                    ├── createdFiles.lst
                    └── inputFiles.lst
