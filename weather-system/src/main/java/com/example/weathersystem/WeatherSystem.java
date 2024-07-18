package com.example.weathersystem;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class WeatherSystem {

    private static final String API_KEY = "8N785F2FMH4QZ7WUKER8XPAC4"; 
    private static final String BASE_URL = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";

    public static void main(String[] args) {
        // Query for New York City
        String query = "New York";

        WeatherSystem weatherSystem = new WeatherSystem();
        weatherSystem.getCurrentWeather(query);
    }

    public void getCurrentWeather(String query) {
        String urlString = String.format("%s%s?key=%s&include=current", BASE_URL, query, API_KEY);

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) { 
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JSONObject jsonResponse = new JSONObject(response.toString());
                JSONObject currentConditions = jsonResponse.getJSONObject("currentConditions");

                double temp = currentConditions.getDouble("temp");
                String condition = currentConditions.getString("conditions");
                String datetime = currentConditions.getString("datetime");

                System.out.println("Current Weather:");
                System.out.println("Datetime: " + datetime);
                System.out.println("Temperature: " + temp + " °F");
                System.out.println("Condition: " + condition);

            } else {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                String inputLine;
                StringBuffer errorResponse = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    errorResponse.append(inputLine);
                }
                in.close();
                System.out.println("GET request failed. Response Code: " + responseCode);
                System.out.println("Error Response: " + errorResponse.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
