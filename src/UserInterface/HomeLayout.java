package UserInterface;

import javax.swing.*;
import java.awt.*;

public class HomeLayout {
    private final JFrame layout;
    public HomeLayout() {

        layout = new JFrame("Home Page");
        layout.setSize(1280, 720);
        layout.setLocationRelativeTo(null);
        layout.setResizable(false);
        layout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        layout.getContentPane().setBackground(Color.decode("#4d4d4d"));

        AddHomePanel();
        AddAccountPanel();
        AddGameGrid();

    }

    public void show(){
        layout.setVisible(true);
    }


    public void AddHomePanel(){

        HomePanel home_panel = new HomePanel();
        layout.setLayout(new BorderLayout());
        layout.add(home_panel, BorderLayout.WEST);
        home_panel.setVisible(true);



    }

    public void AddAccountPanel(){

        HomePanel account_panel = new HomePanel(800, 1000);
        layout.setLayout(new BorderLayout());
        layout.add(account_panel, BorderLayout.EAST);
        account_panel.setVisible(true);

    }

    public void AddGameGrid(){

        HomePanel game_grid = new HomePanel(800);
        layout.setLayout(new GridLayout(2,2));
        layout.add(game_grid);
        game_grid.setVisible(true);

    }


}
