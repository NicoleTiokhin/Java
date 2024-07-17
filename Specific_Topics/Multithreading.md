# Multithreading 

working on different tasks at same time as in program can do many things at once <br>
-> more efficient CPU usage<br>
-> responsiveness : program doesn't freeze when doing something heavy in the background<br>
<br>
Thread Lifecycle : <br>
New: Thread is created but not yet started. <br>
Runnable: Thread is ready to run and waiting for CPU time.<br>
Running: Thread is executing.<br>
Waiting/Timed Waiting: Thread is waiting for another thread or a specific time.<br>
Terminated: Thread has finished execution.<br>
<br>
Thread Priorities:<br>
Threads have priorities (1 to 10) that can affect their execution order.<br>
<br>
Key Methods:<br>
start(): Starts the thread and calls run().<br>
run(): Contains the code to be executed by the thread.<br>
sleep(long millis): Pauses the thread for a specified time.<br>
join(): Waits for the thread to finish.<br>
interrupt(): Interrupts the thread.<br>
<br>
<br>
## How to create Threads <br>
<br>
class that extends Thread<br>
override the run method<br>
call the start method to begin the new thread<br>


```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Running in MyThread");
    }
}

public class Test {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();
    }
}
```
## Add Multithreading to Currency Converter 

thread for fetching the exchange rate
thread for displaying the exchange rate trends diagram

updated main Method with Multithreading: 



```java
public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the base currency (e.g.: USD): ");
        String baseCurrency = scanner.next().toUpperCase();
        System.out.print("Please enter the target currency (e.g.: EUR): ");
        String targetCurrency = scanner.next().toUpperCase();
        System.out.print("Enter the amount to convert: ");
        double amount = scanner.nextDouble();

        System.out.print("Do you want to see the exchange rate trends? (yes/no): ");
        String showTrends = scanner.next().toLowerCase();

        String startDate = null;
        String endDate = null;
        
        if (showTrends.equals("yes")) {
            System.out.print("Enter the start date (e.g.: 2022-01-01T00:00:00Z): ");
            startDate = scanner.next();
            System.out.print("Enter the end date (e.g.: 2022-12-31T23:59:59Z): ");
            endDate = scanner.next();
        }

        scanner.close();

        String finalStartDate = startDate;
        String finalEndDate = endDate;

        Thread exchangeRateThread = new Thread(() -> {
            try {
                double exchangeRate = getExchangeRate(baseCurrency, targetCurrency);
                double convertedAmount = amount * exchangeRate;
                System.out.println(amount + " " + baseCurrency + " is equal to " + convertedAmount + " " + targetCurrency);

                if (showTrends.equals("yes")) {
                    Thread trendChartThread = new Thread(() -> {
                        DefaultCategoryDataset dataset = getHistoricalExchangeRates(baseCurrency, targetCurrency, finalStartDate, finalEndDate);
                        JFreeChart lineChart = ChartFactory.createLineChart(
                                "Exchange Rate Trends",
                                "Date",
                                "Exchange Rate",
                                dataset,
                                PlotOrientation.VERTICAL,
                                true,
                                true,
                                false);

                        CurrencyConverter chart = new CurrencyConverter("Exchange Rate Trends", "Exchange Rate Trends", lineChart);
                        chart.pack();
                        chart.setVisible(true);
                    });

                    trendChartThread.start();
                }

            } catch (Exception e) {
                System.err.println("An error occurred: " + e.getMessage());
            }
        });

        exchangeRateThread.start();
    }
}


```



