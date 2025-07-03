import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Hangman extends JFrame implements ActionListener {

    private static Hangman instance;

    private int incorrectGuesses = 0;
    private String[] wordChallenge;

    private final WordDB wordDB;
    private JLabel hangmanImage, categoryLabel, hiddenWordLabel, resultsLabel, wordLabel;
    private JButton[] letterButtons;
    private JDialog resultsDialog;
    private Font customFont;

    protected Hangman() {
        super("Hangman Game");
        setSize(CommonConstants.FRAME_SIZE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        getContentPane().setBackground(CommonConstants.BACKGROUND_COLOUR);

        wordDB = new WordDB();
        letterButtons = new JButton[26];
        wordChallenge = wordDB.loadChallenge();

        customFont = CustomTools.createFont(CommonConstants.FONT_PATH);
        createResultDialog();

        addGUIComponents();

    }

    public static Hangman getInstance() {
        if (instance == null)
            instance = new Hangman();
        return instance;
    }

    private void addGUIComponents() {

        hangmanImage = CustomTools.loadImage(CommonConstants.IMAGE_PATH);
        assert hangmanImage != null;
        hangmanImage.setBounds(0, 0, hangmanImage.getPreferredSize().width, hangmanImage.getPreferredSize().height);

        categoryLabel = new JLabel(wordChallenge[0]);
        categoryLabel.setFont(customFont.deriveFont(Font.BOLD, 30f));
        categoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        categoryLabel.setOpaque(true);
        categoryLabel.setForeground(Color.WHITE);
        categoryLabel.setBackground(CommonConstants.SECONDARY_COLOUR);
        categoryLabel.setBorder(BorderFactory.createLineBorder(CommonConstants.SECONDARY_COLOUR));
        categoryLabel.setBounds(
                0,
                hangmanImage.getPreferredSize().height - 28,
                CommonConstants.FRAME_SIZE.width,
                categoryLabel.getPreferredSize().height
        );

        hiddenWordLabel = new JLabel(CustomTools.hideWord(wordChallenge[1]));
        hiddenWordLabel.setFont(customFont.deriveFont(Font.BOLD, 60f));
        hiddenWordLabel.setForeground(Color.WHITE);
        hiddenWordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        hiddenWordLabel.setBounds(
                0,
                categoryLabel.getY() + categoryLabel.getPreferredSize().height + 50,
                CommonConstants.FRAME_SIZE.width,
                hiddenWordLabel.getPreferredSize().height
        );

        GridLayout gridLayout = new GridLayout(4, 7);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(
                -5,
                hiddenWordLabel.getY() + hiddenWordLabel.getPreferredSize().height,
                CommonConstants.Button_PANEL_SIZE.width,
                CommonConstants.Button_PANEL_SIZE.height
        );
        buttonPanel.setLayout(gridLayout);

        for (char c = 'A'; c <= 'Z'; c++) {
            JButton button = new JButton(Character.toString(c));
            button.setBackground(CommonConstants.PRIMARY_COLOUR);
            button.setFont(customFont.deriveFont(Font.BOLD, 20f));
            button.setForeground(Color.WHITE);
            button.addActionListener(this);

            int currentIndex = c - 'A';
            letterButtons[currentIndex] = button;
            buttonPanel.add(letterButtons[currentIndex]);
        }


        JButton resetButton = new JButton("Reset");
        resetButton.setFont(customFont.deriveFont(Font.BOLD, 20f));
        resetButton.setForeground(Color.WHITE);
        resetButton.setBackground(CommonConstants.SECONDARY_COLOUR);
        resetButton.addActionListener(this);
        buttonPanel.add(resetButton);

        JButton quitButton = new JButton("Quit");
        quitButton.setFont(customFont.deriveFont(Font.BOLD, 20f));
        quitButton.setForeground(Color.WHITE);
        quitButton.setBackground(CommonConstants.SECONDARY_COLOUR);
        quitButton.addActionListener(this);
        buttonPanel.add(quitButton);

        getContentPane().add(categoryLabel);
        getContentPane().add(hangmanImage);
        getContentPane().add(hiddenWordLabel);
        getContentPane().add(buttonPanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Reset") || command.equals("Restart")) {
            resetGame();

            if (command.equals("Restart"))
                resultsDialog.setVisible(false);
        } else if (command.equals("Quit"))
            System.exit(0);
        else {
            JButton button = (JButton) e.getSource();
            button.setEnabled(false);

            if (wordChallenge[1].contains(command)) {
                button.setBackground(Color.GREEN);

                char[] hiddenWord = hiddenWordLabel.getText().toCharArray();

                for (int i = 0; i < wordChallenge[1].length(); i++)
                    if (wordChallenge[1].charAt(i) == command.charAt(0))
                        hiddenWord[i] = command.charAt(0);

                hiddenWordLabel.setText(String.valueOf(hiddenWord));

                if (!hiddenWordLabel.getText().contains("*")) {
                    resultsLabel.setText("You got it right!!");
                    resultsDialog.setVisible(true);
                }

            } else {
                button.setBackground(Color.RED);
                incorrectGuesses++;
                CustomTools.updateImage(hangmanImage, "resources/" + (incorrectGuesses + 1) + ".png");
                if (incorrectGuesses >= 6) {
                    resultsLabel.setText("Too Bad. Try Again!!");
                    resultsDialog.setVisible(true);
                }
            }
            wordLabel.setText("Word: " + wordChallenge[1]);
        }
    }

    private void createResultDialog() {

        resultsDialog = new JDialog();
        resultsDialog.setTitle("Results");
        resultsDialog.setSize(CommonConstants.RESULT_PANEL_SIZE);
        resultsDialog.getContentPane().setBackground(CommonConstants.BACKGROUND_COLOUR);
        resultsDialog.setResizable(false);
        resultsDialog.setLocationRelativeTo(this);
        resultsDialog.setModal(true);
        resultsDialog.setLayout(new GridLayout(3, 1));
        resultsDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                resetGame();
            }
        });

        resultsLabel = new JLabel();
        resultsLabel.setForeground(Color.WHITE);
        resultsLabel.setHorizontalAlignment(SwingConstants.CENTER);

        wordLabel = new JLabel();
        wordLabel.setForeground(Color.WHITE);
        wordLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton restartButton = new JButton("Restart");
        restartButton.setForeground(Color.WHITE);
        restartButton.setBackground(CommonConstants.SECONDARY_COLOUR);
        restartButton.addActionListener(this);

        resultsDialog.add(resultsLabel);
        resultsDialog.add(wordLabel);
        resultsDialog.add(restartButton);

    }

    private void resetGame() {
        wordChallenge = wordDB.loadChallenge();
        incorrectGuesses = 0;

        CustomTools.updateImage(hangmanImage, CommonConstants.IMAGE_PATH);

        categoryLabel.setText(wordChallenge[0]);

        String hiddenWord = CustomTools.hideWord(wordChallenge[1]);
        hiddenWordLabel.setText(hiddenWord);

        for (JButton letterButton : letterButtons) {
            letterButton.setEnabled(true);
            letterButton.setBackground(CommonConstants.PRIMARY_COLOUR);
        }

    }

}