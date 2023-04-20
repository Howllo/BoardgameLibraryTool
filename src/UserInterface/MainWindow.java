package UserInterface;

import UserInterface.HomeMenu.HomePanel;
import UserInterface.LoginSystem.EntrancePanel;
import UserInterface.LoginSystem.LoginPanel;
import UserInterface.LoginSystem.SignupPanel;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainWindow extends JFrame {

    public MainWindow(){
        setTitle("Board Game Library");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.decode("#4d4d4d"));
        setLayout(null);
        AddEntrancePanel();
        //setResizable(false);
    }

    /**
     * Display entrance panel.
     */
    public void AddEntrancePanel(){
        JPanel content = new JPanel(new GridBagLayout());
        content.setBorder(new EmptyBorder(20, 20, 20, 20));
        content.setBackground(Color.decode("#4d4d4d"));
        setContentPane(content);
        add(new EntrancePanel(this));
        setSize(1280, 720);
    }

    /**
     * Display a login panel.
     */
    public void AddLoginPanel(){
        JPanel content = new JPanel(new GridBagLayout());
        content.setBorder(new EmptyBorder(20,20, 20, 20));
        content.setBackground(Color.decode("#4d4d4d"));
        setContentPane(content);
        add(new LoginPanel(this));
        setSize(1280, 720);
    }

    /**
     * Displays sign up panel.
     */
    public void AddSignUpPanel(){
        JPanel content = new JPanel(new GridBagLayout());
        content.setBorder(new EmptyBorder(20,20, 20, 20));
        content.setBackground(Color.decode("#4d4d4d"));
        setContentPane(content);
        add(new SignupPanel(this));
        setSize(1280, 720);
    }
    
    /**
     * Display home menu panel.
     */
    public void addHomeMenu(){
        HomePanel home_Window = new HomePanel(this);
        JPanel content = new JPanel(new GridBagLayout());
        content.setBorder(new EmptyBorder(20,20, 20, 20));
        content.setBackground(Color.decode("#4d4d4d"));
        setContentPane(content);
        add(home_Window);
        setSize(1280, 720);
    }
}
