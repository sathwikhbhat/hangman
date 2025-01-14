import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class WordDB {
    private HashMap<String, String[]> wordList;

    private ArrayList<String> categories;

    public WordDB() {
        try {
            wordList = new HashMap<>();
            categories = new ArrayList<>();

            String filePath = getClass().getClassLoader().getResource(CommonConstants.DATA_PATH).getPath();

            if (filePath.contains("%20")) filePath.replaceAll("%20", " ");

            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String category = parts[0];
                categories.add(category);

                String values[] = Arrays.copyOfRange(parts, 1, parts.length);
                wordList.put(category, values);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public String[] loadChallenge() {
        Random rand = new Random();

        String category = categories.get(rand.nextInt(categories.size()));
        String[] categoryValues = wordList.get(category);
        String word = categoryValues[rand.nextInt(categoryValues.length)];

        return new String[]{category.toUpperCase(), word.toUpperCase()};
    }

}
