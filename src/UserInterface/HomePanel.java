package UserInterface;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {

    JPanel home_panel= new JPanel(new GridLayout (2,2));
public HomePanel(){

    setSize(800, 500);
    setBackground(Color.decode("#333333"));
    HomeButtons();

}

private void HomeButtons(){


 //   JPanel home_panel= new JPanel(new GridLayout (2,2));


  //  home_panel.add(createComponent("Call of Duty"));
  //  home_panel.add(createComponent("Overcooked"));
  //  home_panel.add(createComponent("Last of Us"));
  //  home_panel.add(createComponent("Resident Evil 4"));

    JButton search = new JButton();

    search.setBackground(Color.decode("#0071bc"));
    search.setText("SEARCH");
    search.setForeground(Color.white);
    add(search);

    JButton library = new JButton();

    library.setBackground(Color.decode("#0071bc"));
    library.setText("LIBRARY");
    library.setForeground(Color.white);
    add(library);

    JButton collections = new JButton();

    collections.setBackground(Color.decode("#0071bc"));
    collections.setText("COLLECTIONS");
    collections.setForeground(Color.white);
    add(collections);

    JButton account_profile = new JButton();
    Panel p = new Panel();


    account_profile.setBackground(Color.decode("#0071bc"));
    account_profile.setText("SPENCER");
    account_profile.setForeground(Color.white);
    p.setLayout(new BorderLayout());
    p.add(account_profile, BorderLayout.EAST);
    add(p, BorderLayout.EAST);
    p.setVisible(true);


}

    private static JComponent createComponent(String s) {
        JLabel l = new JLabel(s);
        l.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
                Color.DARK_GRAY));
        l.setHorizontalAlignment(JLabel.CENTER);
        return l;
    }

}

