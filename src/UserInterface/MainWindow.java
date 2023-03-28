package UserInterface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainWindow {
    private final JFrame window;

    public MainWindow(){
        window = new JFrame("Board Game Library");
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.decode("#4d4d4d"));
        window.setLayout(null);
        AddLoginPanel();
    }

    /**
     * Forces the main window to display to the user.
     */
    public void show(){ window.setVisible(true); }

    /**
     * Display a login panel.
     */
    public void AddLoginPanel(){
        JPanel content = new JPanel(new GridBagLayout());
        content.setBorder(new EmptyBorder(20,20, 20, 20));
        content.setBackground(Color.decode("#4d4d4d"));
        window.setContentPane(content);
        window.add(new LoginPanel(window));
        window.setSize(1280, 720);
    }
}
