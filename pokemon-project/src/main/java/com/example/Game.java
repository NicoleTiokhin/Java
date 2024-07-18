
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