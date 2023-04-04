package DataParsing;

import java.util.Comparator;

/**
 * Represents a single DataParsing.Game with data from BoardGameGeek.com
 * This is a very primitive game class, used to illustrate how to gather
 * information from the Java Document Object Model (DOM).
 * See main program for description of file contents
 */
public class Game implements Comparator {
    /**
     * Constructor to populate a new DataParsing.Game object with retrieved data
     * @param title  The full title of the game
     * @param tnuri  A URI with the location of the game's thumbnail image file
     * @param pubyear Publication Year
     * @param rank BGG rank
     * @param id BGG id key
     */
    public Game(String title, String tnuri, Integer pubyear, Integer rank, String id, Integer minPlayers, Integer maxPlayer) {
        this.title = title;
        this.thumbnailUri = tnuri;
        this.publicationYear = pubyear;
        this.rank = rank;
        this.id = id;
        this.minPlayers = minPlayers;
        this.maxPlayer = maxPlayer;
    }

    /**
     * @return A printable string containing the DataParsing.Game's field values
     */
    public String toString() {
        return "[" + title + ", "+ publicationYear + ", BGG Rank: " + rank + ", BGG ID: " + id + "]";
    }

    //----------- private attributes of a DataParsing.Game ------------------------------------

    private String  title;           // game title
    private String  thumbnailUri;    // Link / address of game image thumbnail, not used yet
    private Integer publicationYear; // date of publication
    private Integer rank;            // board game geek current rank
    private String  id;              // board game geek unique game id key
    private Integer minPlayers;     // min players
    private Integer maxPlayer;      // max players

    public String getGameId(){
        return id;
    }

    @Override
    public int compare(Object o1, Object o2) {
        Game gameOne = (Game) o1;
        Game gameTwo = (Game) o2;

        if(gameOne.title.compareTo(gameTwo.title) > 0)
            return 1;
        else if (gameOne.title.equals(gameTwo.title))
            return 0;
        else
            return -1;
    }
}
