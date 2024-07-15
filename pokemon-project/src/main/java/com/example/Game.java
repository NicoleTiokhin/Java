package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.json.JSONObject;

public class Game {
    private Scanner scanner;

    public Game() {
        this.scanner = new Scanner(System.in);
    }

    public Pokemon createPokemon(String pokemonName) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                JSONObject data = PokemonAPI.getPokemonData(pokemonName);
                String name = data.getString("name");
                int health = data.getJSONArray("stats").getJSONObject(0).getInt("base_stat");
                int attackPower = data.getJSONArray("stats").getJSONObject(1).getInt("base_stat");
                return new Pokemon(name, health, attackPower);
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

    public void startGame() throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the name of your first team: ");
        String team1Name = scanner.nextLine();
        ArrayList<Pokemon> team1 = createTeam(team1Name);

        System.out.print("Enter the name of your second team: ");
        String team2Name = scanner.nextLine();
        ArrayList<Pokemon> team2 = createTeam(team2Name);

        Potion team1Potion = null;
        Potion team2Potion = null;

        System.out.print("Do you want to use a healing potion in the battle for " + team1Name + "? (will be generated randomly in a range between 10 and 50) (yes/no): ");
        String usePotion = scanner.nextLine();
        if (usePotion.equalsIgnoreCase("yes")) {
            team1Potion = createPotion();
            System.out.println(team1Name + "'s potion has been created with a healing power of " + team1Potion.getHealingPower());
        }
        System.out.print("Do you want to use a healing potion in the battle for " + team2Name + "? (will be generated randomly in a range between 10 and 50) (yes/no): ");
        String useTeam2Potion = scanner.nextLine();
        if (useTeam2Potion.equalsIgnoreCase("yes")) {
            team2Potion = createPotion();
            System.out.println(team2Name + "'s potion has been created with a healing power of " + team2Potion.getHealingPower());
        }

        System.out.println("Begin the Pokémon battle!");
        Fight fight = new Fight(team1, team2, team1Name, team2Name, team1Potion, team2Potion);
        try {
            fight.start();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        scanner.close();
    }

    public static void main(String[] args) {
        try {
            Game game = new Game();
            game.startGame();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
