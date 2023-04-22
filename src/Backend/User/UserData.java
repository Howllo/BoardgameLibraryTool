package Backend.User;

import Backend.Database.Database;
import DataParsing.Game;

import java.io.File;
import java.util.ArrayList;

public class UserData {
    public Integer userID;
    public String userName = "";
    public char[] password;
    public ArrayList<Review> userReviews = new ArrayList<>();

    public UserData(String incomingUserName, char[] incomingPassword, Integer inUserID, ArrayList<Review> reviews) {
        userName = incomingUserName;
        password = incomingPassword;
        userReviews = reviews;

        // Create a new ID or use one from file.
        if(inUserID == -1){
            userID = filesInDirectory(Database.getInstance().getGameDataPath());
        } else {
            userID = inUserID;
        }
    }

    /**
     * Check get total count of all the user data in the directory to create user data IDs.
     * @param fileLocation Takes in a file location to check all the files.
     * @return Integer of total amount of files in user_data directory.
     */
    public Integer filesInDirectory(String fileLocation){
        File directory = new File(fileLocation);
        int count = 0;
        for(File file : directory.listFiles())
        {
            if(file.isFile())
                count++;
        }
        return count;
    }

    /**
     * Adds new review to the data objects.
     * @param review Takes in a Review object to be added.
     */
    public void addNewReview(Review review){
        for(Review review1 : userReviews){
            if(review1.getGameID().equals(review.getGameID())){
                review1.setPlayerScore(review.getPlayerScore());
                review1.setReviewText(review.getReviewText());
                return;
            }
        }
        userReviews.add(review);
    }

    /**
     * Get the review based on a game ID.
     * @param game Takes in a game to get the game ID.
     * @return a review object of the review.
     */
    public final Review GetReviewBasedOnGameID(Game game){
        for(Review review: userReviews){
            if(review.getGameID().equals(game.getGameId())){
                return review;
            }
        }
        return null;
    }
}
