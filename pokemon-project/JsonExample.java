import org.json.JSONObject;

public class JsonExample {
    public static void main(String[] args) {
        // A sample JSON string
        String jsonString = "{"
                + "\"name\": \"Pikachu\","
                + "\"type\": \"Electric\","
                + "\"health\": 100,"
                + "\"abilities\": [\"Static\", \"Lightning Rod\"]"
                + "}";

        // Parse the JSON string into a JSONObject
        JSONObject jsonObject = new JSONObject(jsonString);

        // Retrieve and print values from the JSON object
        String name = jsonObject.getString("name");
        String type = jsonObject.getString("type");
        int health = jsonObject.getInt("health");
        
        System.out.println("Name: " + name);
        System.out.println("Type: " + type);
        System.out.println("Health: " + health);

        // Retrieve and print array values from the JSON object
        System.out.println("Abilities: ");
        for (Object ability : jsonObject.getJSONArray("abilities")) {
            System.out.println(" - " + ability);
        }
    }
}
