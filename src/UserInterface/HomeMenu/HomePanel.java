package UserInterface.HomeMenu;

import Backend.Database.Database;
import DataParsing.Game;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HomePanel extends JPanel {
    private JPanel home_panel = new JPanel();
    private JTable displayGames;
    JScrollPane scroll;


    public HomePanel(){
        setSize(1166, 1000);
        setBackground(Color.decode("#333333"));
        HomeButtons();
}
    public HomePanel(int s, int a){
        setSize(s, a);
        setBackground(Color.decode("#333333"));
    }

    public HomePanel(int w){
        setSize(w, 300);
        setBackground(Color.decode("#333333"));
        createTable(Database.getInstance().GetGameList());
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

    private void createTable(ArrayList<Game> inList){
        TableCellRenderer tableCellRenderer;
        displayGames = new JTable(new JTableButton(inList));
        tableCellRenderer = displayGames.getDefaultRenderer(JButton.class);
        //displayGames.setDefaultRenderer(JButton.class, new JTableButtonRenderer(tableCellRenderer));

        displayGames.getTableHeader().setReorderingAllowed(false);
        displayGames.setRowSelectionAllowed(false);
        displayGames.setAutoCreateRowSorter(true);

        scroll = new JScrollPane(displayGames);
        add(scroll, BorderLayout.WEST);
        setVisible(true);
    }

    private static JComponent createComponent(String s) {
        JLabel l = new JLabel(s);
        l.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
                Color.DARK_GRAY));
        l.setHorizontalAlignment(JLabel.CENTER);
        return l;
    }
}