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
