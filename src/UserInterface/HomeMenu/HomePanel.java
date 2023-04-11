package UserInterface.HomeMenu;

import Backend.Database.GameFilters;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePanel extends JPanel {

    JPanel home_panel = new JPanel();

public HomePanel(){

    setSize(1166, 1000);
    setBackground(Color.decode("#333333"));

    HomeButtons();



}

    public HomePanel(int s, int a){

        setSize(s, a);
        setBackground(Color.decode("#333333"));


       // FilterButton();


    }

    public HomePanel(int w){

        setSize(w, 300);
        setBackground(Color.decode("#333333"));


        GameGrid();


    }


private void HomeButtons(){

    JButton search = new JButton();

   // setLayout(new GridLayout(0,3));

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

    AccountButton();


}

private void AccountButton(){

    JButton account_profile = new JButton();



    account_profile.setBackground(Color.decode("#0071bc"));
    account_profile.setText("SPENCER");
    account_profile.setForeground(Color.white);
    add(account_profile);


}

/*private void FilterButton(){

    JButton name_filter = new JButton();

    // setLayout(new GridLayout(0,3));

    name_filter.setBackground(Color.decode("#0071bc"));
    name_filter.setText("TITLE");
    name_filter.setForeground(Color.white);
    add(name_filter);

    int top = 200;
    int left = top;
    int bottom = 2 * top;
    int right = left;
    setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));

}*/

private void GameGrid(){

    JButton name_filter = new JButton();

    // setLayout(new GridLayout(0,3));

    name_filter.setBackground(Color.decode("#0071bc"));
    name_filter.setText("TITLE");
    name_filter.setForeground(Color.red);
    name_filter.setFocusPainted(false);

    name_filter.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e){


        }


    });

    add(name_filter);

    int top = 200;
    int left = top;
    int bottom = 2 * top;
    int right = left;
    setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));

    setLayout(new GridLayout(0,1));
    setBackground(Color.decode("#0071bc"));
    add(new Button("Call of Duty"));
    add(new Button("Overcooked"));
    add(new Button("Last of Us"));
    add(new Button("Resident Evil 4"));

    top = 25;
    left = top;
    bottom = 2 * top;
    right = left;
    setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));





}

    private static JComponent createComponent(String s) {
        JLabel l = new JLabel(s);
        l.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
                Color.DARK_GRAY));
        l.setHorizontalAlignment(JLabel.CENTER);
        return l;
    }

}

