import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Hangman extends JFrame implements ActionListener {

    private JLabel hangmanImage, categoryLabel, hiddenWordLabel;
    private JButton[] letterButtons;

    private final WordDB wordDB;
    private int incorrectGuesses = 0;
    private String[] wordChallenge;


    public Hangman() {
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

        addGUIComponents();

    }

    private void addGUIComponents() {

        hangmanImage = CustomTools.loadImage(CommonConstants.IMAGE_PATH);
        hangmanImage.setBounds(0,0, hangmanImage.getPreferredSize().width, hangmanImage.getPreferredSize().height);

        categoryLabel = new JLabel(wordChallenge[0]);
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

        for(char c = 'A'; c <= 'Z'; c++) {
            JButton button = new JButton(Character.toString(c));
            button.setBackground(CommonConstants.PRIMARY_COLOUR);
            button.setForeground(Color.WHITE);
            button.addActionListener(this);

            int currentIndex = c - 'A';
            letterButtons[currentIndex] = button;
            buttonPanel.add(letterButtons[currentIndex]);
        }


        JButton resetButton = new JButton("Reset");
        resetButton.setForeground(Color.WHITE);
        resetButton.setBackground(CommonConstants.SECONDARY_COLOUR);
        resetButton.addActionListener(this);
        buttonPanel.add(resetButton);

        JButton quitButton = new JButton("Quit");
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

    }
}
