

package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class PokemonAPI {
    private static final String POKEAPI_URL = "https://pokeapi.co/api/v2/pokemon/";

    public static JSONObject getPokemonData(String pokemonName) throws Exception {
        String pokemonURL = POKEAPI_URL + pokemonName.toLowerCase();
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
