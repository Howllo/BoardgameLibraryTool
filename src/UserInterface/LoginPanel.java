package UserInterface;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    public LoginPanel(){
        setSize(800, 500);
        setBackground(Color.decode("#333333"));
        CreateButton();
    }

    private void CreateButton(){
        JButton loginButton = new JButton();
        loginButton.setBackground(Color.decode("#0071bc"));
        loginButton.setText("LOG IN");
        loginButton.setForeground(Color.white);
        add(loginButton);
    }
}