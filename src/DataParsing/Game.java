package DataParsing;

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
     * @param rank BGG rank
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

    private String  title;           // game title
    private String  thumbnailUri;    // Link / address of game image thumbnail, not used yet

    private String description;
    private Integer publicationYear; // date of publication
    private Integer rank;            // board game geek current rank
    private String  id;              // board game geek unique game id key
    private Integer minPlayers;     // min players
    private Integer maxPlayer;      // max players

    public String getGameId(){
        return id;
    }
}
