package UserInterface.LoginSystem;

import UserInterface.MainWindow;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntrancePanel extends JPanel {
    private final Dimension OriginalDimension;
    private Integer ipadHeight = 41;

    JButton signUpButton = new JButton("SIGN UP");
    JButton loginButton = new JButton("LOG IN");
    JButton guessLogin = new JButton("GUESS");
    GridBagConstraints c = new GridBagConstraints();

    // Main Menu
    MainWindow mainWindow;

    public EntrancePanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        setLayout(new GridBagLayout());
        OriginalDimension = new Dimension(getSize().width, getSize().height);
        setMaximumSize(getMinimumSize());
        setMinimumSize(getMinimumSize());
        setPreferredSize(getPreferredSize());
        setVisible(true);
        setBackground(Color.decode("#333333"));
        setLocation(375, 75);

        CreateSignupButton();
        CreateLoginButton();
        CreateGuessLoginButton();
    }

    /**
     * Create the signup button to take be used to create new accounts.
     */
    public void CreateSignupButton(){
        JPanel panel = new JPanel(new BorderLayout());
        signUpButton.setBackground(Color.decode("#0071bc"));
        signUpButton.setForeground(Color.white);
        signUpButton.setFocusPainted(false);
        panel.add(signUpButton);

        // Constraints
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_START;
        c.ipady = ipadHeight;
        c.ipadx = this.getPreferredSize().width;
        c.weightx = 0.5;
        c.gridy = 1;
        c.gridx = 1;

        add(signUpButton, c);

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                mainWindow.AddSignUpPanel();
            }
        });
    }

    /**
     * Used to log in to user accounts.
     */
    public void CreateLoginButton(){
        JPanel panel = new JPanel(new BorderLayout());
        loginButton.setBackground(Color.decode("#0071bc"));
        loginButton.setForeground(Color.white);
        loginButton.setFocusPainted(false);
        panel.add(loginButton);

        // Constraints
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        c.ipady = ipadHeight;

        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 4;
        c.gridheight = 4;

        add(panel, c);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                mainWindow.AddLoginPanel();
            }
        });
    }

    /**
     * Create button to be used to for accessing guess account.
     */
    public void CreateGuessLoginButton(){
        JPanel panel = new JPanel(new BorderLayout());
        guessLogin.setBackground(Color.decode("#0071bc"));
        guessLogin.setForeground(Color.white);
        guessLogin.setFocusPainted(false);
        panel.add(guessLogin);

        // Constraints
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = ipadHeight;
        c.ipadx = this.getPreferredSize().width;
        c.anchor = GridBagConstraints.PAGE_END;
        c.weighty = 1.0;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 3;

        add(panel, c);

        guessLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                mainWindow.addHomeMenu();
            }
        });
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(200, 200);
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(200, 200);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }
}
