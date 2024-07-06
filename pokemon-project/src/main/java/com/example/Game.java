package com.example;

import org.json.JSONObject;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Game {
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

    public Potion createPotion() {
        return new Potion();
    }

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

    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}
