package DataParsing;

import java.util.Comparator;

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
    //TODO: Create a ArrayList of Review objects

    public String getGameId(){
        return id;
    }

    public String getThumbnailUrl() {
        return thumbnailUri;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public Integer getMinPlayers() {
        return minPlayers;
    }

    public Integer getMaxPlayer() {
        return maxPlayer;
    }

    public String getTitle(){
        return title;
    }
}