package UserInterface.LoginSystem;

import Backend.Login.LoginSystem;
import UserInterface.DimensionCompare;
import UserInterface.JTextButton;
import UserInterface.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Arrays;
import java.util.Comparator;

public class SignupPanel extends JPanel {
    private final Dimension OriginalDimension;

    // Text Fields
    private final JPanel textFieldPanel;
    private final JTextField usernameField = new JTextField("Username...");
    private final JLabel userNameLabel = new JLabel("Invalid Username");
    private final JPasswordField passwordField = new JPasswordField("Password...");
    private final JLabel passwordLabel = new JLabel("Invalid Password");
    public final String colorText = "#676767";

    // Remember Me
    private final JCheckBox rememberMeLogin = new JCheckBox("Remember Me");

    // Forgot Password
    private final JTextButton forgotButton = new JTextButton("Forgot Password");

    // Log in Button
    private final JButton loginButton = new JButton();

    private final MainWindow parentFrame;

    public SignupPanel(MainWindow parent){
        OriginalDimension = new Dimension(getSize().width, getSize().height);
        parentFrame = parent;
        setMaximumSize(getMinimumSize());
        setMinimumSize(getMinimumSize());
        setPreferredSize(getPreferredSize());
        setVisible(true);
        setBackground(Color.decode("#333333"));
        setLocation(375, 75);
        textFieldPanel  = new JPanel(new BorderLayout());
        passwordField.setEchoChar((char)0);

        // Set Labels
        userNameLabel.setVisible(false);
        passwordLabel.setVisible(false);

        // Add Components in order
        CreateTextField();
        CreateSecondRow();
        CreateButton();
    }

    private void CreateTextField(){
        JPanel userNameHolder = new JPanel(new GridLayout(2, 9));
        JPanel passwordHolder = new JPanel(new GridLayout(2, 9));

        textFieldPanel.setSize(getSize().width / 2, getSize().height / 2);
        textFieldPanel.setSize(OriginalDimension.width, textFieldPanel.getHeight());
        textFieldPanel.setBackground(Color.decode("#333333"));
        userNameHolder.setBackground(Color.decode("#333333"));
        passwordHolder.setBackground(Color.decode("#333333"));

        // Set Background
        usernameField.setOpaque(false);
        passwordField.setOpaque(false);
        usernameField.setBorder(BorderFactory.createLineBorder(Color.decode(colorText)));
        passwordField.setBorder(BorderFactory.createLineBorder(Color.decode(colorText)));

        // Set Label
        userNameLabel.setForeground(Color.RED);
        passwordLabel.setForeground(Color.RED);

        // Set Text
        Font newFront = new Font(usernameField.getFont().getName(), usernameField.getFont().getStyle(), 24);
        usernameField.setForeground(Color.decode("#808080"));
        passwordField.setForeground(Color.decode("#808080"));
        usernameField.setFont(newFront);
        passwordField.setFont(newFront);

        // Set Size
        //usernameField.setColumns(10);
        passwordField.setColumns(5);

        // Add to Panel
        userNameHolder.add(usernameField);
        userNameHolder.add(userNameLabel);
        passwordHolder.add(passwordField);
        passwordHolder.add(passwordLabel);

        // Add to Text Field
        textFieldPanel.add(userNameHolder, BorderLayout.PAGE_START);
        textFieldPanel.add(passwordHolder, BorderLayout.CENTER);

        // Add To Login Panel
        add(textFieldPanel, BorderLayout.PAGE_START);

        // Action
        usernameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(usernameField.getText().equals("Username...")){
                    usernameField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });

        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                char[] testArray = {'P', 'a', 's','s','w','o','r','d','.','.','.'};
                if(Arrays.equals(passwordField.getPassword(), testArray)){
                    passwordField.setText("");
                    passwordField.setEchoChar('*');
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });
    }

    private void CreateSecondRow(){
        JPanel secondPanel = new JPanel(new GridLayout(1, 50));

        // Remove the WhiteBox
        rememberMeLogin.setOpaque(false);

        // Set Outline
        rememberMeLogin.setFocusPainted(false);

        // Set Text Color
        rememberMeLogin.setForeground(Color.decode("#808080"));

        // Set Colors
        forgotButton.setOnMouseClick(Color.decode("#636363"));
        forgotButton.setOnMouseHover(Color.decode("#828282"));
        forgotButton.setOnMouseExit(Color.decode("#d7d7d7"));

        // Set Panels
        secondPanel.setBackground(Color.decode("#333333"));
        secondPanel.add(rememberMeLogin, BorderLayout.LINE_START);
        secondPanel.add(forgotButton, BorderLayout.LINE_END);
        secondPanel.setVisible(true);

        // Add Component
        add(secondPanel, BorderLayout.PAGE_END);
    }

    private void CreateButton(){
        JPanel panel = new JPanel(new BorderLayout());
        loginButton.setBackground(Color.decode("#0071bc"));
        loginButton.setText("SIGN UP");
        loginButton.setForeground(Color.white);
        loginButton.setFocusPainted(false);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginSystem.createNewUserData(usernameField.getText(), passwordField.getPassword(), SignupPanel.this);
            }
        });
        panel.add(loginButton, BorderLayout.SOUTH);
        add(panel);
    }

    @Override
    public Dimension getMinimumSize() {
        return resizePanel();
    }

    @Override
    public Dimension getMaximumSize() {
        return resizePanel();
    }

    @Override
    public Dimension getPreferredSize() {
        return resizePanel();
    }

    private Dimension resizePanel(){
        final double reduceSizeValue = 0.25;

        Dimension parentDimension = parentFrame.getSize();
        Dimension rDimension = getSize();
        Comparator<Dimension> dimCompare = new DimensionCompare();

        if(dimCompare.compare(parentDimension, rDimension) < 0){
            rDimension.setSize( parentDimension.width - (parentDimension.width * reduceSizeValue),
                    parentDimension.height - (parentDimension.height * reduceSizeValue) );

            // Font Resize
            int fontSize = (int) (usernameField.getFont().getSize() - (usernameField.getFont().getSize() * reduceSizeValue));
            Font newFront = new Font(usernameField.getFont().getName(), usernameField.getFont().getStyle(), fontSize);
            usernameField.setFont(newFront);
            passwordField.setFont(newFront);

            // Set Column
            int columnSize = (int) (usernameField.getColumns() - (usernameField.getColumns() * reduceSizeValue));
            usernameField.setColumns(columnSize);
            passwordField.setColumns(columnSize);

        } else if(dimCompare.compare(parentDimension, rDimension) > 0){
            rDimension.setSize(500, 500);
            Font newFront = new Font(usernameField.getFont().getName(), usernameField.getFont().getStyle(), 36);

            usernameField.setFont(newFront);
            passwordField.setFont(newFront);

            usernameField.setColumns(15);
            passwordField.setColumns(15);
        }

        return rDimension;
    }

    @Override
    public void paint(Graphics g) {
        setSize(resizePanel());
        super.paint(g);
    }

    public JLabel getUserNameLabel(){
        return userNameLabel;
    }

    public JLabel getPasswordLabel(){
        return passwordLabel;
    }

    public JTextField getUserNameTextField(){
        return usernameField;
    }

    public JTextField getPasswordTextField(){
        return passwordField;
    }

    public MainWindow getMainWindow(){
        return parentFrame;
    }
}