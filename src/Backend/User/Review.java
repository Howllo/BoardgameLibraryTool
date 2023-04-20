package Backend.User;

import Backend.Database.Database;

import java.io.Serializable;

public class Review implements Serializable {
    private Integer playerID = 0;
    private Integer playerScore = 0;
    private String playerText = "";
    private String gameID = "";
    private String gameTitle = "";

    public Review(Integer playerID, Integer playerScore, String reviewText, String gameID){
        this.playerID = playerID;
        setScore(playerScore);
        this.playerText = reviewText;
        this.gameID = gameID;
        this.gameTitle = Database.getInstance().GetGameFromHashGameID(this.gameID).getTitle();
    }

    /**
     * Set the player score make sure it is within the 0-10 scale.
     * @param score Takes in an int to set the playerScore
     */
    public void setScore(Integer score){
        if(score > 10){
            playerScore = 10;
            return;
        } else if (score < 0){
            playerScore = 0;
            return;
        }
        playerScore = score;
    }

    /**
     * Get the player score from the object.
     * @return an int of the player score.
     */
    public Integer getPlayerScore(){
        return playerScore;
    }

    /**
     * Set the player id of the review object
     * @param playerID Integer object to set the ID.
     */
    public void setPlayerID(Integer playerID){
        this.playerID = playerID;
    }

    /**
     * Get the player ID.
     * @return Integer object of the player ID.
     */
    public Integer getPlayerID(){
        return playerID;
    }

    /**
     * Set the review text of the object.
     * @param playerText Takes in a string object to set the text.
     */
    public void setReviewText(String playerText){
        this.playerText = playerText;
    }

    /**
     * Get the review text.
     * @return a String object of the review.
     */
    public String getReviewText(){
        return playerText;
    }

    /**
     * Set the game id of the review object.
     * @param gameID Takes in Integer object to set the game id.
     */
    public void setGameID(String  gameID){
        this.gameID = gameID;
    }

    /**
     * Get the game id of the review object.
     * @return an Integer object of the game id.
     */
    public String  getGameID(){
        return gameID;
    }

    /**
     * Get the game title of the object.
     * @return an String of the title.
     */
    public String getTitle(){
        return gameTitle;
    }
}
