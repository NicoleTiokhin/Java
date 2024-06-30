package com.example;

import org.json.JSONObject;
import java.util.Scanner;

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
    

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
    
        System.out.print("Enter the name of your first Pokémon: ");
        String firstPokemonName = scanner.nextLine();
        Pokemon pokemon1 = createPokemon(firstPokemonName);
    
        System.out.print("Enter the name of your second Pokémon: ");
        String secondPokemonName = scanner.nextLine();
        Pokemon pokemon2 = createPokemon(secondPokemonName);
    
        Potion potion = null;
        System.out.print("Do you want to use a healing potion in the battle? (will be generated randomly in a range between 10 and 50) (yes/no): ");
        String usePotion = scanner.nextLine();
        if (usePotion.equalsIgnoreCase("yes")) {
            potion = createPotion();
            System.out.println("This potion has been created with a healing power of " + potion.getHealingPower());
        }
    
        System.out.println("Begin the Pokémon battle!");
        Fight fight = new Fight(pokemon1, pokemon2, potion);
        fight.start();
    
        scanner.close();
    }
    
    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}
