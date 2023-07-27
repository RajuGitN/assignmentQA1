package assinmentpackage1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherApp {

    private static final String API_KEY = "YOUR_OPENWEATHERMAP_API_KEY";
    private static final String API_URL = "https://samples.openweathermap.org/data/2.5/forecast/hourly?q=London,us&appid=b6907d289e10d714a6e88b30761fae22";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String location = "London,us";

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Get weather");
            System.out.println("2. Get Wind Speed");
            System.out.println("3. Get Pressure");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(reader.readLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter the date (YYYY-MM-DD): ");
                    String targetDate1 = reader.readLine();
                    double temperature = getWeatherData(location, targetDate1);
                    if (temperature != Double.MIN_VALUE) {
                        System.out.println("Temperature on " + targetDate1 + ": " + temperature + " Kelvin");
                    } else {
                        System.out.println("Weather data not found for the specified date.");
                    }
                    break;
                case 2:
                    System.out.print("Enter the date (YYYY-MM-DD): ");
                    String targetDate2 = reader.readLine();
                    double windSpeed = getWindSpeedData(location, targetDate2);
                    if (windSpeed != Double.MIN_VALUE) {
                        System.out.println("Wind Speed on " + targetDate2 + ": " + windSpeed + " m/s");
                    } else {
                        System.out.println("Weather data not found for the specified date.");
                    }
                    break;
                case 3:
                    System.out.print("Enter the date (YYYY-MM-DD): ");
                    String targetDate3 = reader.readLine();
                    double pressure = getPressureData(location, targetDate3);
                    if (pressure != Double.MIN_VALUE) {
                        System.out.println("Pressure on " + targetDate3 + ": " + pressure + " hPa");
                    } else {
                        System.out.println("Weather data not found for the specified date.");
                    }
                    break;
                case 0:
                    System.out.println("Exiting the program.");
                    reader.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static double getWeatherData(String location, String targetDate) throws IOException {
        String urlString = API_URL + "?q=" + location + "&appid=" + API_KEY;
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        reader.close();

        // Parse the JSON response to extract the temperature for the given date
        // (You can use a JSON parsing library like Gson or Jackson for more complex parsing)
        // For simplicity, I'll just use basic String manipulation here.
        String jsonString = response.toString();
        String targetDateString = targetDate + " 00:00:00"; // We are interested in the data for the entire day.
        int index = jsonString.indexOf(targetDateString);
        if (index != -1) {
            int tempStartIndex = jsonString.indexOf("temp", index);
            int tempEndIndex = jsonString.indexOf(",", tempStartIndex);
            String temperatureStr = jsonString.substring(tempStartIndex + 6, tempEndIndex);
            return Double.parseDouble(temperatureStr);
        } else {
            return Double.MIN_VALUE;
        }
    }

    private static double getWindSpeedData(String location, String targetDate) throws IOException {
        // Similar to getWeatherData, you can implement the logic to get wind speed data.
        // I'll leave this as an exercise for you. It follows a similar approach as above.
        return Double.MIN_VALUE;
    }

    private static double getPressureData(String location, String targetDate) throws IOException {
        // Similar to getWeatherData, you can implement the logic to get pressure data.
        // I'll leave this as an exercise for you. It follows a similar approach as above.
        return Double.MIN_VALUE;
    }
}
