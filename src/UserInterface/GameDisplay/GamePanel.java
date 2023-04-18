package UserInterface.GameDisplay;

import Backend.Database.Database;
import DataParsing.Game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class GamePanel extends JPanel{

    JPanel game_panel = new JPanel();
    public GamePanel(Game game){

        setSize(1166, 1000);
        setBackground(Color.decode("#333333"));


        GameClick(game);
        //GameDisplay(game);
    }

    private void GameClick(Game game_info){
        JButton game_pressed = new JButton();
        game_pressed.setText(game_info.getTitle());
        game_pressed.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){
                game_pressed.setVisible(false);
                GameDisplay(game_info);


            }


        });



    }
    private void GameDisplay(Game game_info){

        JPanel game_page = new JPanel();
        ImageIcon game_image = new ImageIcon(game_info.getThumbnailUrl());
        JLabel game_pic = new JLabel();

        game_page.setSize(600, 500);
        JTextArea textarea = new JTextArea("TITLE" + game_info.getTitle() + "\n" + "PUBLICATION YEAR: " + game_info.getPublicationYear() + "\n" + "MINIMUM PLAYERS: " + game_info.getMinPlayers() + "\n" + "MAXIMUM PLAYERS: " + game_info.getMaxPlayer() + "\n"   );
        textarea.setForeground(Color.white);
        textarea.setBackground(Color.black);
        textarea.setEditable(false);

        game_page.add(textarea);

        game_pic.setIcon(game_image);

        game_page.add(game_pic);
        game_page.setVisible(true);


    }
}
