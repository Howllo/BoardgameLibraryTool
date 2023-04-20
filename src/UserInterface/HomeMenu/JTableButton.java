package UserInterface.HomeMenu;

import DataParsing.Game;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


// Base off of: http://www.java2s.com/Code/Java/Swing-Components/ButtonTableExample.htm
class JTableButtonRenderer extends JButton implements TableCellRenderer{
    private TableCellRenderer tableCellRenderer;
    String title;

    public JTableButtonRenderer(TableCellRenderer renderer, String title){
        tableCellRenderer = renderer;
        this.title = title;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if(isSelected){
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(Color.decode("#0071bc"));
        }
        setText(title);

        return this;
    }
}

public class JTableButton extends AbstractTableModel {
    public String[] cols = {"Title", "Release", "Min Players", "Max Players"};
    public Object[][] rows = {};

    JTableButton(ArrayList<Game> game){
        GameGrid(game);
    }

    private void GameGrid(ArrayList<Game> inList){
        for(Game game : inList){
            if(game.getTitle().equals(inList.get(0).getTitle()))
                continue;
            rows = addObjectArray(game.getTitle(), game.getPublicationYear().toString(), game.getMinPlayers().toString(), game.getMaxPlayer().toString());
        }
    }

    public Object[][] addObjectArray(String title, String releaseDate, String minPlayer, String maxPlayer){
        Object[][] obj = new Object[rows.length + 1][4];
        int size = rows.length;

        System.arraycopy(rows, 0, obj, 0, size);

        JButton button = new JButton(title);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Add Popup!");
                JOptionPane.showMessageDialog(button, "Push!");
            }
        });

        if(size > 0){
            obj[size - 1][0] = button;
            obj[size - 1][1] = releaseDate;
            obj[size - 1][2] = minPlayer;
            obj[size - 1][3] = maxPlayer;
        } else {
            obj[0][0] = button;
            obj[0][1] = releaseDate;
            obj[0][2] = minPlayer;
            obj[0][3] = maxPlayer;
        }

        return obj;
    }

    public String getColumnName(int column){
        return cols[column];
    }

    @Override
    public int getRowCount() {
        return rows.length;
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return rows[rowIndex][columnIndex];
    }

    public Class getColumnClass(int column){
        return getValueAt(0, column).getClass();
    }
}