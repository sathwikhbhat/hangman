import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class WordDB {
    private HashMap<String, String[]> wordList;

    private ArrayList<String> categories;

    public WordDB() {
        try {
            wordList = new HashMap<>();
            categories = new ArrayList<>();

            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(CommonConstants.DATA_PATH);
            if (inputStream == null) {
                throw new IOException("Unable to locate resource: " + CommonConstants.DATA_PATH);
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    String category = parts[0];
                    categories.add(category);

                    String[] values = Arrays.copyOfRange(parts, 1, parts.length);
                    wordList.put(category, values);
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public String[] loadChallenge() {
        if (categories.isEmpty()) {
            throw new IllegalStateException("No words loaded from " + CommonConstants.DATA_PATH);
        }

        Random rand = new Random();

        String category = categories.get(rand.nextInt(categories.size()));
        String[] categoryValues = wordList.get(category);
        String word = categoryValues[rand.nextInt(categoryValues.length)];

        return new String[] { category.toUpperCase(), word.toUpperCase() };
    }

}