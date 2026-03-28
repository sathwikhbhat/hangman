package game;

import constants.PathConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class WordDB {
    private static final Random RANDOM = new Random();
    private HashMap<String, String[]> wordList;
    private ArrayList<String> categories;

    public WordDB() {
        try {
            wordList = new HashMap<>();
            categories = new ArrayList<>();

            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PathConstants.DATA_PATH);
            if (inputStream == null) {
                throw new IOException("Unable to locate resource: " + PathConstants.DATA_PATH);
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
            throw new IllegalStateException("No words loaded from " + PathConstants.DATA_PATH);
        }

        String category = categories.get(RANDOM.nextInt(categories.size()));
        String[] categoryValues = wordList.get(category);
        String word = categoryValues[RANDOM.nextInt(categoryValues.length)];

        return new String[]{category.toUpperCase(), word.toUpperCase()};
    }
}
