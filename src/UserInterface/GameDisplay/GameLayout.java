package UserInterface.GameDisplay;

import UserInterface.MainWindow;
import DataParsing.Game;
import javax.swing.*;
import java.awt.*;

public class GameLayout extends JPanel {

    MainWindow parentFrame;
    private final Game game;
    public GameLayout(MainWindow parent, Game ingame) {

        parentFrame = parent;
        parentFrame.setSize(1280, 720);
        parentFrame.setLocationRelativeTo(null);
        parentFrame.setResizable(true);
        parentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        parentFrame.getContentPane().setBackground(Color.decode("#4d4d4d"));

        game = ingame;
        AddGamePanel();

    }

    public void show(){
        parentFrame.setVisible(true);
    }

    public void AddGamePanel(){

        GamePanel game_panel = new GamePanel(game);
        add(game_panel);
        game_panel.setVisible(true);


    }

}
