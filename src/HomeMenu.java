import DataParsing.Database;
import UserInterface.HomeLayout;

import javax.swing.*;

// REMOVE THIS WHEN HOME MENU IS COMPLETE
public class HomeMenu {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                Database.getInstance();
                HomeLayout home_Window = new HomeLayout();
                home_Window.show();
            }
        });

    }
}
