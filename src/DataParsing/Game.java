package DataParsing;

import Backend.Database.Database;
import Backend.User.Review;

import java.util.ArrayList;

/**
 * Represents a single DataParsing.Game with data from BoardGameGeek.com
 * This is a very primitive game class, used to illustrate how to gather
 * information from the Java Document Object Model (DOM).
 * See main program for description of file contents
 */
public class Game {
    /**
     * Constructor to populate a new DataParsing.Game object with retrieved data
     * @param title  The full title of the game
     * @param tnuri  A URI with the location of the game's thumbnail image file
     * @param pubyear Publication Year
     * @param id BGG id key
     */
    public Game(String title, String tnuri, String description, Integer pubyear, String id, Integer minPlayers, Integer maxPlayer) {
        this.title = title;
        this.thumbnailUri = tnuri;
        this.description = description;
        this.publicationYear = pubyear;
        this.id = id;
        this.minPlayers = minPlayers;
        this.maxPlayer = maxPlayer;
        this.reviews = new ArrayList<>();
    }

    /**
     * @return A printable string containing the DataParsing.Game's field values
     */
    public String toString() {
        return "[" + title + ", "+ publicationYear +  ", BGG ID: " + id + "," + " Min Players: " + minPlayers + "," +" Max Players: " + maxPlayer + "," + " Description: " + description + "]";
    }
    //----------- private attributes of a DataParsing.Game ------------------------------------
    private final String  title;           // Game title
    private final String  thumbnailUri;    // Link / address of game image thumbnail, not used yet
    private final Integer publicationYear; // date of publication
    private final String  id;              // board game geek unique game id key
    private final Integer minPlayers;     // min players
    private final Integer maxPlayer;      // max players
    private final String description;
    private final ArrayList<Review> reviews;

    /**
     * Get Game ID.
     * @return String object of the game id.
     */
    public String getGameId(){
        return id;
    }

    /**
     * Get the thumbnail url.
     * @return String object of the game thumbnail url.
     */
    public String getThumbnailUrl() {
        return thumbnailUri;
    }

    /**
     * Get the release year of the board game.
     * @return Integer of the publication year.
     */
    public Integer getPublicationYear() {
        return publicationYear;
    }

    /**
     * Get the minimum players for the game.
     * @return Integer of the minimum player for the game.
     */
    public Integer getMinPlayers() {
        return minPlayers;
    }

    /**
     * Get the max player for the game.
     * @return Integer of the max player for the game.
     */
    public Integer getMaxPlayer() {
        return maxPlayer;
    }

    /**
     * Get the title of the game.
     * @return String object of the title.
     */
    public String getTitle(){
        return title;
    }

    /**
     * Get the description of the game.
     * @return String object of the description from the game.
     */
    public String getDescription(){
        return description;
    }

    /**
     * Get the reviews for the game.
     * @return ArrayList of review objects.
     */
    public ArrayList<Review> GetReviews(){
        return reviews;
    }

    /**
     * Set reviews.
     * @param review Takes in a review object to be added to reviews.
     */
    public void setReviews(Review review){
        for(Review review1: reviews){
            if(review1.getGameID().equals(review.getGameID())){
                review1.setScore(review.getPlayerScore());
                review1.setReviewText(review.getReviewText());
                return;
            }
        }

        reviews.add(review);
    }
}