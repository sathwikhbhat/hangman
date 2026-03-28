import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class CustomTools {

    public static JLabel loadImage(String resources) {
        BufferedImage image;
        try {
            InputStream inputStream = CustomTools.class.getClassLoader().getResourceAsStream(resources);
            if (inputStream == null) {
                throw new IOException("Unable to locate image resource: " + resources);
            }
            image = ImageIO.read(inputStream);
            return new JLabel(new ImageIcon(image));
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;
    }

    public static void updateImage(JLabel imageContainer, String resources) {
        BufferedImage image;
        try {
            InputStream inputStream = CustomTools.class.getClassLoader().getResourceAsStream(resources);
            if (inputStream == null) {
                throw new IOException("Unable to locate image resource: " + resources);
            }
            image = ImageIO.read(inputStream);
            imageContainer.setIcon(new ImageIcon(image));
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public static Font createFont(String resources) {
        try (InputStream inputStream = CustomTools.class.getClassLoader().getResourceAsStream(resources)) {
            if (inputStream == null) {
                throw new IOException("Unable to locate font resource: " + resources);
            }
            return Font.createFont(Font.TRUETYPE_FONT, inputStream);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;

    }

    public static String hideWord(String word) {
        StringBuilder hiddenWord = new StringBuilder();
        for (int i = 0; i < word.length(); i++)
            if (!Character.isWhitespace(word.charAt(i)))
                hiddenWord.append("*");
            else
                hiddenWord.append(" ");
        return hiddenWord.toString();
    }

}