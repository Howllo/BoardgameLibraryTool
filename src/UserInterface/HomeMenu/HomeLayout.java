package UserInterface.HomeMenu;

import javax.swing.*;
import java.awt.*;
import UserInterface.DimensionCompare;
import UserInterface.MainWindow;

public class HomeLayout extends JPanel {
    MainWindow parentFrame;
    public HomeLayout(MainWindow parent) {

        parentFrame = parent;
        parentFrame.setSize(1280, 720);
        parentFrame.setLocationRelativeTo(null);
        parentFrame.setResizable(true);
        parentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        parentFrame.getContentPane().setBackground(Color.decode("#4d4d4d"));

        AddHomePanel();
        //   AddAccountPanel();
        //  AddFilterPanel();
        AddGameGrid();
    }

    public void show(){
        parentFrame.setVisible(true);
    }

    public void AddHomePanel(){

        HomePanel home_panel = new HomePanel();
        home_panel.setLayout(new GridLayout(0,4));
        home_panel.setBounds(50, 500, 100, 100);
        add(home_panel);//, BorderLayout.NORTH);
        home_panel.setVisible(true);
    }

  /*  public void AddAccountPanel(){
        HomePanel account_panel = new HomePanel(400, 300);
        layout.setLayout(new BorderLayout());
        layout.add(account_panel, BorderLayout.WEST);
        account_panel.setVisible(true);
    }*/

      /* public void AddFilterPanel(){
        HomePanel filter_panel = new HomePanel(400, 300);
        parentFrame.setLayout(new BorderLayout());
        parentFrame.add(filter_panel, BorderLayout.SOUTH);
        filter_panel.setVisible(true);
    }*/

    public void AddGameGrid(){

        HomePanel game_grid = new HomePanel(500);
        game_grid.setLayout(new GridLayout(0,1));
        add(game_grid);
        game_grid.setVisible(true);
    }
}