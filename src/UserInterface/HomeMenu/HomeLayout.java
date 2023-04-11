package UserInterface.HomeMenu;

import javax.swing.*;
import java.awt.*;

public class HomeLayout {
    private final JFrame layout;
    public HomeLayout() {

        layout = new JFrame("Home Page");
        layout.setSize(1280, 720);
        layout.setLocationRelativeTo(null);
        layout.setResizable(true);
        layout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        layout.getContentPane().setBackground(Color.decode("#4d4d4d"));

        AddHomePanel();
     //   AddAccountPanel();
      //  AddFilterPanel();
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

  /*  public void AddAccountPanel(){

        HomePanel account_panel = new HomePanel(400, 300);
        layout.setLayout(new BorderLayout());
        layout.add(account_panel, BorderLayout.WEST);
        account_panel.setVisible(true);
    }*/

       public void AddFilterPanel(){

        HomePanel filter_panel = new HomePanel(400, 300);
        layout.setLayout(new BorderLayout());
        layout.add(filter_panel, BorderLayout.SOUTH);
        filter_panel.setVisible(true);
    }

    public void AddGameGrid(){

        HomePanel game_grid = new HomePanel(200);
        layout.setLayout(new GridLayout(0,1));
        layout.add(game_grid);
        game_grid.setVisible(true);
    }
}
