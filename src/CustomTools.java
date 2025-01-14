import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLOutput;

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

    public static String hideWord(String word) {
        String hiddenWord = "";
        for (int i = 0; i < word.length(); i++)
            if(Character.isLetter(word.charAt(i)))
                hiddenWord += "_ ";
            else
                hiddenWord += word.charAt(i);
        return hiddenWord;
    }

}
