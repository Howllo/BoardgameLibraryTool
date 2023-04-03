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

}
