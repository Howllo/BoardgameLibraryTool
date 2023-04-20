package UserInterface.HomeMenu;

import Backend.Database.Database;
import DataParsing.Game;
import UserInterface.MainWindow;

import javax.naming.directory.SearchResult;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class HomePanel extends JPanel {
    private MainWindow mainWindow;
    public String[] cols = {"Title", "Release", "Min Players", "Max Players"};
    public Object[][] rows = {};

    public HomePanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        setSize(1280, 720);
        setLayout(null);
        setBackground(Color.decode("#4d4d4d"));
        HomeButtons();
        createTable(Database.getInstance().GetGameList());
    }

    /**
     * Creates menu bar buttons for navigation.
     */
    private void HomeButtons(){
        JPanel menuBar = new JPanel(new GridBagLayout());
        menuBar.setBackground(Color.decode("#333333"));
        menuBar.setSize(1280, 75);
        menuBar.setLocation(0, 0);

        GridBagConstraints con = new GridBagConstraints();
        con.gridwidth = 10;

        JButton browse = new JButton("BROWSE");

        browse.setLocation(0,0);
        browse.setBackground(Color.decode("#0071bc"));
        browse.setText("BROWSE");
        browse.setForeground(Color.white);
        menuBar.add(browse);

        JButton collections = new JButton();

        collections.setBackground(Color.decode("#0071bc"));
        collections.setText("COLLECTIONS");
        collections.setForeground(Color.white);
        menuBar.add(collections);

        JTextField search = new JTextField();

        search.setColumns(10);
        search.setBackground(Color.decode("#0071bc"));
        search.setText("Search...");
        search.setForeground(Color.white);
        Font fontSearch = search.getFont().deriveFont(Font.PLAIN, 16f);
        search.setFont(fontSearch);
        menuBar.add(search);
        JButton searchButton = new JButton();
        searchButton.setBackground(Color.decode("#0071bc"));
        searchButton.setText("Enter");
        searchButton.setForeground(Color.white);
        menuBar.add(searchButton);

        search.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                search.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(search.getText().equals("")){
                    search.setText("Search...");
                }
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Game> temp = new ArrayList<Game>();
                temp = Database.getInstance().GetGameList();
                try {
                    searching(temp, search);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        JButton account_profile = new JButton();

        account_profile.setBackground(Color.decode("#0071bc"));
        account_profile.setText("Save Button Temp");
        account_profile.setForeground(Color.white);

        account_profile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Database.getInstance().SaveUserData();
                JOptionPane.showMessageDialog(HomePanel.this, "User data was saved!");
            }
        });
        menuBar.add(account_profile);

        add(menuBar);
    }
    public void searching(ArrayList<Game> temp,JTextField textField) throws IOException {
        int i = 0;
        String[] columnNames = {"Results"};
        Object[][] rowData = new Object[temp.size()][1];
        for (Object empty : temp)
        {
            Object o = temp.get(i);
            rowData[i][0] = o;
            i++;
        }
        DefaultTableModel model;
        model = new DefaultTableModel(rowData, columnNames);
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        JTable table = new JTable(model);
        table.setRowSorter(sorter);
        JScrollPane results = new JScrollPane(table);
        JFrame searchResults = new JFrame();
        searchResults.setVisible(true);
        searchResults.setBounds(800,600,800,800);
        searchResults.add(results);
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(textField.getText());
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                search(textField.getText());
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                search(textField.getText());
            }
            public void search(String str) {
                if (str.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter(str));
                }
            }
        });
    }

    /**
     * Create a display table for the games
     * @param inList Takes in an array list of games.
     */
    private void createTable(ArrayList<Game> inList){
        // Create Object Array.
        GameGrid(inList);

        // Create Table
        JTable displayGames = new JTable(rows, cols){
            @Override
            public boolean isCellEditable(int row, int column) {
                if(column == 0) {
                    return super.isCellEditable(row, column);
                }
                return false;
            }
        };

        TableCellRenderer tableCellRenderer = displayGames.getDefaultRenderer(JButton.class);
        displayGames.getColumn("Title").setCellRenderer(new JTableButtonRenderer(tableCellRenderer));
        displayGames.getColumn("Title").setCellEditor(new JTableButton(new JCheckBox()));
        displayGames.setDefaultRenderer(JButton.class, new JTableButtonRenderer(tableCellRenderer));
        displayGames.getTableHeader().setReorderingAllowed(false);
        displayGames.setRowSelectionAllowed(false);
        displayGames.setAutoCreateRowSorter(true);

        JScrollPane scroll = new JScrollPane(displayGames);
        scroll.setSize(600, 500);
        scroll.setLocation(320, 125);

        add(scroll, BorderLayout.SOUTH);
        setVisible(true);
    }

    /**
     * Create objects array.
     * @param inList Takes in a Games array list.
     */
    private void GameGrid(ArrayList<Game> inList){
        for(Game game : inList){
            if(game.getTitle().equals(inList.get(0).getTitle()))
                continue;
            rows = addObjectArray(game);
        }
    }

    /**
     * Adds new Object[][] to a 2D array.
     * @param game Takes in a game object to be process for display purpose.
     * @return an new Object[][] array with extended capacity.
     */
    public Object[][] addObjectArray(Game game){
        Object[][] obj = new Object[rows.length + 1][4];
        int size = rows.length;

        // Copy Array
        System.arraycopy(rows, 0, obj, 0, size);

        // Set Information
        if(size > 0){
            obj[size - 1][0] = game.getTitle();
            obj[size - 1][1] = game.getPublicationYear();
            obj[size - 1][2] = game.getMinPlayers();
            obj[size - 1][3] = game.getMaxPlayer();
        } else {
            obj[0][0] = game.getTitle();
            obj[0][1] = game.getPublicationYear();
            obj[0][2] = game.getMinPlayers();
            obj[0][3] = game.getMaxPlayer();
        }
        return obj;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1280, 720);
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(1280, 720);
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(1280, 720);
    }
}