import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> Hangman.getInstance().setVisible(true));
    }
}