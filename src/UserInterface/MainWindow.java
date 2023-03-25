package UserInterface;

import javax.swing.*;
import java.awt.*;

public class MainWindow {
    private final JFrame window;

    public MainWindow(){
        window = new JFrame("Board Game Library");
        window.setSize(1280, 720);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.decode("#4d4d4d"));
        AddLoginPanel();
    }

    /**
     * Forces the main window to display to the user.
     */
    public void show(){
        window.setVisible(true);
    }

    /**
     * Display a login panel.
     */
    public void AddLoginPanel(){
        LoginPanel loginPanel = new LoginPanel();
        window.setLayout(new BorderLayout());
        window.add(loginPanel, BorderLayout.CENTER);
        loginPanel.setVisible(true);
    }
}
