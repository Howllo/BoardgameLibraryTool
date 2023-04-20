package UserInterface.HomeMenu;

import Backend.Database.Database;
import DataParsing.Game;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.util.ArrayList;


public class HomePanel extends JPanel implements ActionListener {
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


    private void HomeButtons() {

        JButton searchButton = new JButton("Enter");
        JTextField searchField = new JTextField("Search...");
        searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Search...")) {
                    searchField.setText("");
                    String searchText = searchField.getText();


                }
            }

            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Search...");

                }
            }
        });
        searchButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform search operation here


            }
        });


        add(searchField);
        add(searchButton);
        searchButton.setBackground(Color.decode("#0071bc"));
        searchButton.setForeground(Color.white);
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
        try {
            search();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void AccountButton() {
        JButton account_profile = new JButton();
        account_profile.setBackground(Color.decode("#0071bc"));
        account_profile.setText("Account");
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
    public void search() throws IOException {
        ArrayList<Game> temp = new ArrayList<Game>();
        int i = 0;
        new Database();
        String[] columnNames = {"Results"};
        temp = Database.GetGameList();
        Object[][] rowData = new Object[temp.size()][1];
         for (Object empty : temp)
        {
            Object o = temp.get(i);
            rowData[i][0] = o;
            i++;
        }
        JTextField textField = new JTextField(10);
        JLabel searchLabel = new JLabel("Search");
        DefaultTableModel model;
        model = new DefaultTableModel(rowData, columnNames);
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        JTable table = new JTable(model);
        table.setRowSorter(sorter);
        JScrollPane results = new JScrollPane(table);
        add(searchLabel);
        add(textField);
        textField.setBackground(Color.decode("#0071bc"));;
        textField.setForeground(Color.WHITE);
        add(textField);
        JFrame searchResults = new JFrame();
        searchResults.setVisible(true);
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
    private static JComponent createComponent(String s) {
        JLabel l = new JLabel(s);
        l.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
                Color.DARK_GRAY));
        l.setHorizontalAlignment(JLabel.CENTER);
        return l;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }


    }

