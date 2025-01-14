import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class CustomTools {

    public static JLabel loadImage(String resources) {
        BufferedImage image;
        try {
            InputStream inputStream = CustomTools.class.getResourceAsStream(resources);
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
            InputStream inputStream = CustomTools.class.getResourceAsStream(resources);
            image = ImageIO.read(inputStream);
            imageContainer.setIcon(new ImageIcon(image));
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public static Font createFont(String resources) {

        String filePath = CustomTools.class.getClassLoader().getResource(resources).getPath();
        if(filePath.contains("%20")) filePath.replaceAll("%20", " ");

        try {
            File customFontfile = new File(filePath);
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, customFontfile);
            return customFont;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return null;

    }

    public static String hideWord(String word) {
        String hiddenWord = "";
        for (int i = 0; i < word.length(); i++)
            if(!Character.isWhitespace(word.charAt(i)))
                hiddenWord += "*";
            else
                hiddenWord += " ";
        return hiddenWord;
    }

}
