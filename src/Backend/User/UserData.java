package Backend.User;

import java.util.ArrayList;

public class UserData {
    public String userName = "";
    public char[] password;
    public ArrayList<Review> userReviews = new ArrayList<>();

    public UserData(String incomingUserName, char[] incomingPassword, ArrayList<Review> reviews) {
        userName = incomingUserName;
        password = incomingPassword;
        userReviews = reviews;
    }
}
