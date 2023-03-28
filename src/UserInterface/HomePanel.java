package UserInterface;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
 //   GridLayout games_layout = new GridLayout(2,2);
public HomePanel(){

    setSize(800, 500);
    setBackground(Color.decode("#333333"));
    HomeButtons();

}

private void HomeButtons(){


    JPanel home_panel= new JPanel();

    home_panel.setLayout(new GridLayout(2,2));

    home_panel.add(new JButton("Call of Duty"));
    home_panel.add(new JButton("Overcooked"));
    home_panel.add(new JButton("Last of Us"));
    home_panel.add(new JButton("Last of Us"));


}

}

