package UserInterface.HomeMenu;

import Backend.Database.Database;
import DataParsing.Game;
import UserInterface.GameDisplay.GameLayout;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// Credit goes to Java2s: http://www.java2s.com/Code/Java/Swing-Components/ButtonTableExample.htm
class JTableButtonRenderer extends JButton implements TableCellRenderer{
    private TableCellRenderer tableCellRenderer;

    public JTableButtonRenderer(TableCellRenderer renderer){
        tableCellRenderer = renderer;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if(isSelected){
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else {
            setForeground(Color.white);
            setBackground(Color.decode("#0071bc"));
        }

        setText((value == null) ? "" : value.toString());
        return this;
    }
}

public class JTableButton extends DefaultCellEditor {
    protected JButton button;
    private String label;
    private boolean isPushed;
    private boolean displayPopup;

    JTableButton(JCheckBox checkBox){
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        button.setForeground(Color.white);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //button.setText();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if(isSelected){
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(Color.white);
            button.setBackground(Color.decode("#0071bc"));
        }
        label = (value == null) ? "" : value.toString();
        button.setText(label);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!displayPopup){
                    Game game =  Database.getInstance().GetGameFromHashTitle(label);
                    GameLayout layout = new GameLayout(game);
                    layout.setVisible(true);
                    displayPopup = true;
                }
            }
        });

        displayPopup = false;
        isPushed = true;
        return button;
    }

    public Object getCellEditorValue(){
        if(isPushed){
            //JOptionPane.showMessageDialog(button, label + " Test");
        }
        isPushed = false;
        return new String(label);
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    protected void fireEditingStopped(){
        super.fireEditingStopped();
    }
}