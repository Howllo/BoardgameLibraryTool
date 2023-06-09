package UserInterface.HomeMenu;

import Backend.Database.Database;
import DataParsing.Game;
import UserInterface.MainWindow;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomePanel extends JPanel {
    private MainWindow mainWindow;
    public String[] cols = {"Title", "Release", "Min Players", "Max Players"};
    public Object[][] rows = {};
    public JTable table = new JTable();
    public HomePanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        setSize(1280, 720);
        setLayout(null);
        setBackground(Color.decode("#4d4d4d"));
        HomeButtons();
        table = createTable(Database.getInstance().GetGameList());
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


        JTextField search = new JTextField();

        search.setColumns(10);
        search.setBackground(Color.decode("#0071bc"));
        search.setText("Search...");
        search.setForeground(Color.white);
        Font fontSearch = search.getFont().deriveFont(Font.PLAIN, 16f);
        search.setFont(fontSearch);
        menuBar.add(search);
        search.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                search.setText("");
                try {
                    searching(table, search);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(search.getText().equals("")){
                    search.setText("Search...");
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

    /**
     * Search for certain boards within the list.
     * @param temp Takes in a temp table.
     * @param searchField Takes in a JTextField to process the search data.
     * @throws IOException Error
     */
    public void searching(JTable temp,JTextField searchField) throws IOException {
        temp.setAutoCreateRowSorter(true);
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(temp.getModel());
        temp.setRowSorter(sorter);
        temp.setVisible(true);
        JTable tempTable = new JTable(temp.getModel());
        add(tempTable);

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(searchField.getText());
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                search(searchField.getText());
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                search(searchField.getText());
            }
            public void search(String str) {
                if (str.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + str));
                }
            }
        });
    }

    /**
     * Create a display table for the games
     * @param inList Takes in an array list of games.
     */
    private JTable createTable(ArrayList<Game> inList){
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
        return displayGames;
    }

    /**
     * Create objects array.
     * @param inList Takes in a Games array list.
     */
    private void GameGrid(ArrayList<Game> inList){
        for(Game game : inList){
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
            obj[size][0] = game.getTitle();
            obj[size][1] = game.getPublicationYear();
            obj[size][2] = game.getMinPlayers();
            obj[size][3] = game.getMaxPlayer();
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