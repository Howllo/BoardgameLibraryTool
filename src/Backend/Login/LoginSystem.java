package Backend.Login;

import Backend.Database.Database;
import Backend.User.UserData;
import UserInterface.LoginSystem.LoginPanel;
import UserInterface.LoginSystem.SignupPanel;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class LoginSystem {
    /***
     * Used to progress the request of logging in.
     * @param username Takes in a username string to be processed.
     * @param password Takes in a password character array due to nature of String.
     */
    public static void loadUserData(String username, char[] password, LoginPanel loginPanel){
        UserData data = Database.getInstance().setUserData(username, password);

        if(data != null){
            loginPanel.setVisible(false);
            loginPanel.getParentFrame().addHomeMenu();
        } else {
            JOptionPane.showMessageDialog(null, "This account does not exist.");
            Error(username, password, data, loginPanel);
        }
    }

    /**
     * Create a new user account if the system is error free.
     * @param username Takes in a username to for the accessing the data in file directory
     * @param password Takes in a character array as password. to correctly access the account.
     * @param signupPanel Takes in an loginPanel to display error in the text.
     */
    public static void createNewUserData(String username, char[] password, SignupPanel signupPanel){
        // Check Length
        if(!Error(username, password, signupPanel, true)){
            return;
        }

        // Check Account Already Exist
        File testFile = new File(Database.getInstance().getGameDataPath() + username + ".xml");
        if(testFile.exists()){
            JOptionPane.showMessageDialog(null, "This account already exist.");
            Error(username, password, signupPanel, false);
            return;
        }

        // User Data
        boolean processData = Database.getInstance().createNewUserData(username, password);

        // Create New User - Entrance to HomeMenu
        if(processData){
            signupPanel.setVisible(false);
            signupPanel.getMainWindow().addHomeMenu();
        } else {
            Error(username, password, signupPanel, processData);
        }
    }

    /**
     * Error that will display if the input is incorrect.
     *
     * @param username   Take in a username to be checked for length.
     * @param password   Take in a password to be checked for length.
     * @param loginPanel Take in a login panel to display error.
     */
    private static void Error(String username, char[] password, UserData data, LoginPanel loginPanel){
        // Check User Length
        if(username.length() <= 3){
            loginPanel.GetUserNameTextField().setBorder(BorderFactory.createLineBorder(Color.red));
            loginPanel.GetUserNameLabel().setVisible(true);
            return;
        } else{
            loginPanel.GetUserNameTextField().setBorder(BorderFactory.createLineBorder(Color.decode(loginPanel.colorText)));
            loginPanel.GetUserNameLabel().setVisible(false);
        }

        // Check Password Length
        if(password.length <= 5){
            loginPanel.GetPasswordTextField().setBorder(BorderFactory.createLineBorder(Color.red));
            loginPanel.GetPasswordLabel().setVisible(true);
            return;
        } else{
            loginPanel.GetPasswordTextField().setBorder(BorderFactory.createLineBorder(Color.decode(loginPanel.colorText)));
            loginPanel.GetPasswordLabel().setVisible(false);
        }

        // Check if data is null
        if(data == null){
            loginPanel.GetUserNameTextField().setBorder(BorderFactory.createLineBorder(Color.red));
            loginPanel.GetUserNameLabel().setVisible(true);
            loginPanel.GetPasswordTextField().setBorder(BorderFactory.createLineBorder(Color.red));
            loginPanel.GetPasswordLabel().setVisible(true);
        }
    }

    /**
     * Error that will display if the input is incorrect.
     * @param username Take in a username to be checked for length.
     * @param password Take in a password to be checked for length.
     * @param signupPanel Take in a login panel to display error.
     * @return boolean whether it works.
     */
    private static boolean Error(String username, char[] password, SignupPanel signupPanel, boolean dataProcess){
        if(username.length() <= 3){
            signupPanel.getUserNameTextField().setBorder(BorderFactory.createLineBorder(Color.red));
            signupPanel.getUserNameLabel().setVisible(true);
            return false;
        } else{
            signupPanel.getUserNameTextField().setBorder(BorderFactory.createLineBorder(Color.decode(signupPanel.colorText)));
            signupPanel.getUserNameLabel().setVisible(false);
        }

        if(password.length <= 5){
            signupPanel.getPasswordTextField().setBorder(BorderFactory.createLineBorder(Color.red));
            signupPanel.getPasswordLabel().setVisible(true);
            return false;
        } else{
            signupPanel.getPasswordTextField().setBorder(BorderFactory.createLineBorder(Color.decode(signupPanel.colorText)));
            signupPanel.getPasswordLabel().setVisible(false);
        }

        if(!dataProcess){
            signupPanel.getUserNameTextField().setBorder(BorderFactory.createLineBorder(Color.red));
            signupPanel.getUserNameLabel().setVisible(true);
            signupPanel.getPasswordTextField().setBorder(BorderFactory.createLineBorder(Color.red));
            signupPanel.getPasswordLabel().setVisible(true);
        }
        return true;
    }
}