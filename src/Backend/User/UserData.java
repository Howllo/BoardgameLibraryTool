package Backend.User;

import Backend.Database.Database;

import java.io.File;
import java.util.ArrayList;

public class UserData {
    public Integer userID;
    public String userName = "";
    public char[] password;
    public ArrayList<Review> userReviews = new ArrayList<>();

    public UserData(String incomingUserName, char[] incomingPassword, ArrayList<Review> reviews) {
        userName = incomingUserName;
        password = incomingPassword;
        userReviews = reviews;
        userID = filesInDirectory(Database.getInstance().getGameDataPath());
    }

    public int filesInDirectory(String fileLocation){
        File directory = new File(fileLocation);
        int count = 0;
        for(File file : directory.listFiles())
        {
            if(file.isFile())
                count++;
        }
        return count;
    }
}
