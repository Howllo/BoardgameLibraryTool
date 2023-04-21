package UserInterface.GameDisplay;

import Backend.Database.Database;
import Backend.User.Review;
import DataParsing.Game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class GameLayout extends JFrame {
    private final Game game;
    private JTextArea userReview;

    public GameLayout(Game ingame) {
        setBackground(Color.decode("#4d4d4d"));
        setSize(640, 360);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.decode("#4d4d4d"));
        game = ingame;
        GameDisplay(game);
        GameReviewTextArea();
        GameReview();
    }

    /**
     * Dispaly game information
     * @param game_info Takes in a game object to be parsed.
     */
    private void GameDisplay(Game game_info){
        if(game_info == null) return;

        JPanel game_page = new JPanel();
        game_page.setBackground(Color.decode("#333333"));
        JTextArea textarea = new JTextArea("Title:\t\t" + game_info.getTitle() + "    \n" + "Publication Year:\t" + game_info.getPublicationYear() + "\n" + "Minimum Player(s):\t" + game_info.getMinPlayers() + "\n" + "Maximum Player(s):\t" + game_info.getMaxPlayer() + "\n");
        textarea.setColumns(25);
        textarea.setForeground(Color.white);
        textarea.setBackground(Color.decode("#4d4d4d"));
        textarea.setEditable(false);
        game_page.add(textarea);
        game_page.setVisible(true);
        add(game_page, BorderLayout.PAGE_START);
    }

    /**
     * Displays the user review text area.
     */
    private void GameReviewTextArea(){
        if(Database.getInstance().getUserData() == null){
            return;
        }

        JPanel textAreaPanel = new JPanel();
        Review getReview = Database.getInstance().getUserData().GetReviewBasedOnGameID(game);

        textAreaPanel.setBackground(Color.decode("#333333"));
        userReview = new JTextArea("Add review...");
        if(getReview != null){
            userReview.setText(getReview.getReviewText());
        }
        userReview.setLineWrap(true);
        userReview.setColumns(36);
        userReview.setBackground(Color.decode("#4d4d4d"));
        userReview.setForeground(Color.white);
        Font newFront = new Font(userReview.getFont().getName(), userReview.getFont().getStyle(), 20);
        userReview.setFont(newFront);

        userReview.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(userReview.getText().equals("Add review...")){
                    userReview.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(userReview.getText().equals("") || userReview.getText().equals(" ")){
                    userReview.setText("Add review...");
                }
            }
        });

        textAreaPanel.setSize(getWidth(), 200);
        textAreaPanel.add(userReview);

        add(textAreaPanel, BorderLayout.CENTER);
    }

    /**
     * Display the user score and submit button area.
     */
    private void GameReview(){
        if(Database.getInstance().getUserData() == null){
            JOptionPane.showMessageDialog(this, "To access reviews, you must be login first.");
            return;
        }

        // Get the review.
        Review getReview = Database.getInstance().getUserData().GetReviewBasedOnGameID(game);

        int playerID = Database.getInstance().getUserData().userID;
        JPanel userReviewPanel = new JPanel();
        userReviewPanel.setBackground(Color.decode("#4d4d4d"));
        Integer[] score = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

        // Text
        JLabel reviewText = new JLabel("Review Score: ");
        reviewText.setForeground(Color.white);
        userReviewPanel.add(reviewText);

        // Create User Score
        JComboBox<Integer> userScore = new JComboBox<>(score);

        if(getReview != null){
            userScore.setSelectedIndex(getReview.getPlayerScore());
        } else {
            userScore.setSelectedIndex(5);
        }
        userReviewPanel.add(userScore);

        JButton submitReview = new JButton("Submit Review");
        submitReview.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Review review = new Review(playerID, userScore.getSelectedIndex(), userReview.getText(), game.getGameId());
                game.setReviews(review);
                Database.getInstance().getUserData().addNewReview(review);
                JOptionPane.showMessageDialog(GameLayout.this, "Review was submitted!");
            }
        });
        userReviewPanel.add(submitReview);

        // Add Review
        add(userReviewPanel, BorderLayout.PAGE_END);
    }
}
