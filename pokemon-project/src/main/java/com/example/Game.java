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
