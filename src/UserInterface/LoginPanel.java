package UserInterface;

import DataParsing.Database;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;

public class LoginPanel extends JPanel {
    private Dimension OriginalDimension;

    // Text Fields
    private JPanel textFieldPanel = new JPanel();
    private final JTextField usernameField = new JTextField("");
    private final JLabel userNameLabel = new JLabel("Username");
    private final JPasswordField passwordField = new JPasswordField("");
    private final JLabel passwordLabel = new JLabel("Password");

    // Remember Me
    JCheckBox rememberMeLogin = new JCheckBox("Remember Me");

    // Forgot Password
    JTextButton forgotButton = new JTextButton("Forgot Password");

    // Log in Button
    JButton loginButton = new JButton();

    JFrame parentFrame;

    public LoginPanel(JFrame parent){
        OriginalDimension = new Dimension(getSize().width, getSize().height);

        parentFrame = parent;

        setMaximumSize(getMinimumSize());
        setMinimumSize(getMinimumSize());
        setPreferredSize(getPreferredSize());
        setVisible(true);
        setBackground(Color.decode("#333333"));
        setLocation(375, 75);

        // Add Components in order
        CreateTextField();
        CreateCheckbox();
        CreateForgetPassword();
        CreateButton();
    }

    private void CreateTextField(){
        textFieldPanel.setSize(OriginalDimension.width, textFieldPanel.getHeight());
        textFieldPanel.setBackground(Color.decode("#333333"));


        // Set Fill
        usernameField.setHorizontalAlignment(JTextField.CENTER);

        // Set Background
        usernameField.setOpaque(false);
        passwordField.setOpaque(false);
        //usernameField.setBorder(BorderFactory.createEmptyBorder());
        //passwordField.setBorder(BorderFactory.createEmptyBorder());

        // Set Text
        Font newFront = new Font(usernameField.getFont().getName(), usernameField.getFont().getStyle(), 36);
        usernameField.setForeground(Color.decode("#808080"));
        passwordField.setForeground(Color.decode("#808080"));
        usernameField.setFont(newFront);
        passwordField.setFont(newFront);

        // Set Size
        usernameField.setColumns(15);
        passwordField.setColumns(15);

        // Add to Panel
        textFieldPanel.add(usernameField);
        textFieldPanel.add(userNameLabel);
        textFieldPanel.add(passwordField);
        textFieldPanel.add(passwordLabel);
        add(textFieldPanel);
    }

    private void CreateCheckbox(){
        // Remove the WhiteBox
        rememberMeLogin.setOpaque(false);

        // Set Outline
        rememberMeLogin.setFocusPainted(false);

        // Set Text Color
        rememberMeLogin.setForeground(Color.decode("#808080"));

        // Add Component
        add(rememberMeLogin);
    }

    private void CreateForgetPassword(){
        // Set Colors
        forgotButton.setOnMouseClick(Color.decode("#636363"));
        forgotButton.setOnMouseHover(Color.decode("#828282"));
        forgotButton.setOnMouseExit(Color.decode("#d7d7d7"));

        // Add Component
        add(forgotButton);
    }

    private void CreateButton(){
        loginButton.setBackground(Color.decode("#0071bc"));
        loginButton.setText("LOG IN");
        loginButton.setForeground(Color.white);
        loginButton.setFocusPainted(false);
        add(loginButton);
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
}