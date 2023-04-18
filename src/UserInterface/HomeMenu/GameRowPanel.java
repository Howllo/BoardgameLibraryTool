package UserInterface.HomeMenu;

import DataParsing.Game;
import UserInterface.JTextButton;

import javax.swing.*;
import java.awt.*;

public class GameRowPanel extends JPanel {
    private final Game game;
    private ImageIcon icon;
    GameFilterUI gameFilterUI;

    GameRowPanel(Game inGame, GameFilterUI inGameFilterUI){
        game = inGame;
        setVisible(true);

        // Set
        gameFilterUI = inGameFilterUI;

        // Create
        createNewTitleLabel();
        createNewReleaseDataLabel();
        createNewMinPlayerText();
        createNewMaxPlayerText();
    }

    private void createNewTitleLabel(){
        JTextButton titleText = new JTextButton(game.getTitle());
        JLabel description = new JLabel(game.getDescription());
        titleText.setVisible(true);

        // Set Colors
        titleText.setOnMouseClick(Color.decode("#636363"));
        titleText.setOnMouseHover(Color.decode("#828282"));
        titleText.setOnMouseExit(Color.decode("#d7d7d7"));

        add(titleText);
    }

    private void createNewReleaseDataLabel(){
        JLabel releaseData = new JLabel(game.getPublicationYear().toString());
        releaseData.setVisible(true);
        add(releaseData);
    }

    private void createNewMinPlayerText(){
        JLabel minPlayerText = new JLabel(game.getMinPlayers().toString());
        minPlayerText.setVisible(true);
        add(minPlayerText);
    }

    private void createNewMaxPlayerText(){
        JLabel maxPlayerText = new JLabel(game.getMaxPlayer().toString());
        maxPlayerText.setVisible(true);
        add(maxPlayerText);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(gameFilterUI.getWidth(), 75);
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(gameFilterUI.getWidth(), 75);
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(gameFilterUI.getWidth(), 75);
    }

    /**
     * Gets the game and returns it.
     * @return Game object.
     */
    public Game getGame() {
        return game;
    }
}
