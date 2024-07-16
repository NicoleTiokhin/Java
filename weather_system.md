# Weather system 

I will be using the API [: https://openweathermap.org/api/one-call-3#how](https://www.weatherapi.com) <br>
<br>
<br>
have a base url and api key <br>
<br>
main method : <br>
define the query (location) for New York City<br>
create an instance of the WeatherSystem class<br>
call the getCurrentWeather method with the query<br>
<br>
getCurrentWeather Method : <br>
take a query string (location) and build the complete URL for the API request<br>
make the HTTP GET Request<br>
read the Response and append it to a StringBuffer<br>
parse the JSON Response by extracting the currentConditions object from the JSON and getting the weather info from currentConditions <br>
print the extracted weather data to the console<br>
handle errors by reading the error stream from the connection , appending the error response to a StringBuffer and print the response <br>
<br>
```java
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

```

## Example programm experience 

Current Weather: <br>
Datetime: 13:56:00<br>
Temperature: 91.8 °F<br>
Condition: Clear<br>



```java
```

```java
```


```java
```


```java
```


```java
```


```java
```


```java
```


```java
```


```java
```
