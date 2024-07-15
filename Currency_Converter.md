# Currency Comverter 

use org.json library for JSON parsing <br>
java class : CurrencyConverter <br>
use currency.api <br>
get input from the user for the base currency, target currency, and amount to convert <br>

source for info on api : https://currencyapi.com/docs/examples/java-currency-converter <br>
other source (excluding stackoverflow) : https://javadoc.io/doc/com.google.code.gson/gson/latest/com.google.gson/com/google/gson/JsonParser.html <br>
I will use Gson library from Google for Json parsing since it was used in the api documentation as well and seems less complex to implememt than my pokemon game json parsing <br>

## Imports 

```java
package com.example.currencyconverter;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
```


## ApiKey 

```java
private static final String API_KEY = "cur_live_tjqy0YT3dF3hqzpHzC04R0FE4NvVq36Oc1F3Wc6D";
```

## getExchangeRate Method 
combine the base URL of the API, the API key, the base currency, and the target currency to find url to connect to <br> 
create the connection to the API URL <br> 
create a JsonParser object to read the JSON data from the API <br> 
read data from api make it usable in Java <br> 
convert that data into a JsonObject object to get specific parts of the JSON data <br> 
in the data part of the json get the exhange rate for the target currency  and convert into double <br>

```java
public static double getExchangeRate(String baseCurrency, String targetCurrency) throws Exception {
        String urlString = "https://api.currencyapi.com/v3/latest?apikey=" + API_KEY + "&base_currency=" + baseCurrency + "&currencies=" + targetCurrency;
        URL url = new URL(urlString);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject jsonobj = root.getAsJsonObject();

        double exchangeRate = jsonobj.getAsJsonObject("data").getAsJsonObject(targetCurrency).get("value").getAsDouble();
        return exchangeRate;
    }
```


## Main Method 
create a Scanner Object <br> 
get Base Currency from User,convert the input to uppercase <br> 
get Target Currency from User,convert the input to uppercase <br> 
ask the user for how much they want to convert and read as double <br> 
call getExchangeRate method to get the exchange rate for the given currencies <br> 
converts by multiplying with the exchange rate <br> 
prints the result  <br> 
if an error occurs print an error message  <br> 


```java
     public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the base currency (e.g.. USD): ");
        String baseCurrency = scanner.next().toUpperCase();
        System.out.print("Please enter the target currency (e.g.. EUR): ");
        String targetCurrency = scanner.next().toUpperCase();
        System.out.print("Enter the amount to convert: ");
        double amount = scanner.nextDouble();

        try {
            double exchangeRate = getExchangeRate(baseCurrency, targetCurrency);
            double convertedAmount = amount * exchangeRate;
            System.out.println(amount + " " + baseCurrency + " is equal to " + convertedAmount + " " + targetCurrency);
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }

        scanner.close();
    }
}


```



## Converter Experience example 

Please enter the base currency (e.g.: USD): USD <br> 
Please enter the target currency (e.g.: EUR): EUR <br> 
Enter the amount to convert: 33 <br> 
33.0 USD is equal to 30.3494451315 EUR <br> 

## Add : Show trends using a graph

used this source : https://currencyapi.com/docs/range

New Libraries :
JFreeChart library for plotting the graph
Gson library for parsing JSON data

```java
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
```

new api endpoint to get historical data , have end and start parameters 

getHistoricalExchangeRates Method : 
I´ll need to create a dataset to store all the exchang erates for the previous dates to build a graph with 
create a URL for the API request with base currency, target currency, start date, end date, and accuracy (day)
create a HTTP connection to the API endpoint
parse JSON Response ; get the data array from the JSON object
iterate over every element in the data array ; convert into JsonObject; get the datetime and the exchange rate for the target currency 
add the extracted exchange rate and date to the dataset


```java
public static DefaultCategoryDataset getHistoricalExchangeRates(String baseCurrency, String targetCurrency, String startDate, String endDate) throws Exception {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    String urlString = "https://api.currencyapi.com/v3/range?apikey=" + API_KEY + "&base_currency=" + baseCurrency + "&currencies=" + targetCurrency
            + "&datetime_start=" + startDate + "&datetime_end=" + endDate + "&accuracy=day";
    URL url = new URL(urlString);
    HttpURLConnection request = (HttpURLConnection) url.openConnection();
    request.connect();

    JsonParser jp = new JsonParser();
    JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
    JsonObject jsonobj = root.getAsJsonObject();
    JsonArray data = jsonobj.getAsJsonArray("data");

    for (JsonElement element : data) {
        JsonObject obj = element.getAsJsonObject();
        String datetime = obj.get("datetime").getAsString();
        double exchangeRate = obj.getAsJsonObject("currencies").getAsJsonObject(targetCurrency).get("value").getAsDouble();
        dataset.addValue(exchangeRate, baseCurrency + "/" + targetCurrency, datetime);
    }

    return dataset;
}

```

modified main method : 
prompt the user if they want to see the exchange rate trends
if user says yes , prompt for the start and end dates of the desired range
call getHistoricalExchangeRates method 
create and show a line chart 

```java
public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Please enter the base currency (e.g.: USD): ");
    String baseCurrency = scanner.next().toUpperCase();
    System.out.print("Please enter the target currency (e.g.: EUR): ");
    String targetCurrency = scanner.next().toUpperCase();
    System.out.print("Enter the amount to convert: ");
    double amount = scanner.nextDouble();

    try {
        double exchangeRate = getExchangeRate(baseCurrency, targetCurrency);
        double convertedAmount = amount * exchangeRate;
        System.out.println(amount + " " + baseCurrency + " is equal to " + convertedAmount + " " + targetCurrency);

        System.out.print("Do you want to see the exchange rate trends? (yes/no): ");
        String showTrends = scanner.next().toLowerCase();

        if (showTrends.equals("yes")) {
            System.out.print("Enter the start date (e.g.: 2022-01-01T00:00:00Z): ");
            String startDate = scanner.next();
            System.out.print("Enter the end date (e.g.: 2022-12-31T23:59:59Z): ");
            String endDate = scanner.next();

            DefaultCategoryDataset dataset = getHistoricalExchangeRates(baseCurrency, targetCurrency, startDate, endDate);
            JFreeChart lineChart = ChartFactory.createLineChart(
                    "Exchange Rate Trends",// Graph title
                    "Date",// X-axis label
                    "Exchange Rate",// Y-axis label
                    dataset,// Data for the chart
                    PlotOrientation.VERTICAL,// Chart orientation 
                    true,// Include legend
                    true,// Include hover text
                    false);// URLs 
            CurrencyConverter chart = new CurrencyConverter("Exchange Rate Trends", "Exchange Rate Trends");
            chart.pack();//adjust the window size to fit the graph
            chart.setVisible(true); //make the window visible 
        }

    } catch (Exception e) {
        System.err.println("An error occurred: " + e.getMessage());
    }

    scanner.close();
}

```







```java
```






```java
```



