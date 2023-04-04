package Backend.Login;

import DataParsing.Database;
import UserInterface.LoginSystem.LoginPanel;

import javax.swing.*;
import java.awt.*;

public class LoginSystem {

    /***
     * Used to progress the request of logging in.
     * @param username Takes in a username string to be processed.
     * @param password Takes in a password character array due to nature of String.
     */
    public static void LoadUserData(String username, char[] password, LoginPanel loginPanel){
        Database database = Database.getInstance();
        System.out.println("Load From XML File.");

        loginPanel.GetUserNameTextField().setBorder(BorderFactory.createLineBorder(Color.red));
        loginPanel.GetPasswordTextField().setBorder(BorderFactory.createLineBorder(Color.red));
        loginPanel.GetUserNameLabel().setVisible(true);
        loginPanel.GetPasswordLabel().setVisible(true);
    }

    public static void CreateNewUserData(String username, char[] password, LoginPanel loginPanel){
        if(username.length() <= 3){
            loginPanel.GetUserNameLabel().setBorder(BorderFactory.createLineBorder(Color.red));
            return;
        } else
            loginPanel.GetUserNameLabel().setBorder(BorderFactory.createLineBorder(Color.decode(loginPanel.colorText)));

        if(password.length <= 5){
            loginPanel.GetPasswordTextField().setBorder(BorderFactory.createLineBorder(Color.red));
            return;
        } else
            loginPanel.GetPasswordTextField().setBorder(BorderFactory.createLineBorder(Color.decode(loginPanel.colorText)));
    }
}
