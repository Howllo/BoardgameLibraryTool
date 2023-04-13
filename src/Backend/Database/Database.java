package Backend.Database;

import Backend.User.UserData;
import DataParsing.Game;
import DataParsing.XMLParserUtility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public final class Database {
    // Singleton Pattern
    private static final Database instance;

    static {
        try {
            instance = new Database();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Keeping data relative to the program.
    private final String gameDataXML = "bgg90Games.xml";
    private final String _userDataPath = "data\\user_data\\";
    private final File gameDataBase = new File(gameDataXML);
    private final String absPath = gameDataBase.getAbsolutePath();
    private final String gameDataPath = absPath.replace(gameDataXML, _userDataPath);
    private final HashMap<String, Game> gameHash = new HashMap<String, Game>();
    private ArrayList<Game> gameList;
    private final GameFilters gameFilters;
    private UserData userData;

    private Database() throws IOException {
        try{
            String _gameDataPath = "data\\game_data\\";
            String path = absPath.replace(gameDataXML, _gameDataPath + gameDataXML);
            XMLParserUtility parser = new XMLParserUtility(path);
            gameList = parser.retrieveGameList();
            for (Game game: gameList){
                gameHash.put(game.getGameId(), game);
            }
        } catch (FileNotFoundException e1){
            System.out.println("File not found.");
        } catch (IOException e2){
            System.out.println("Unable to parse..");
        }

        // Create Game Filter
        gameFilters = new GameFilters(gameList);
    }

    /**
     * Get the Instance of the Database singleton.
     * @return Return the Database object instance.
     */
    public static Database getInstance(){
        return instance;
    }

    /**
     * Get the GameHash of the database.
     * @return Returns the of Hashmap[Game ID String, Game].
     */
    public Game GetGameFromHash(String gameID){
        return gameHash.get(gameID);
    }

    /**
     * Get the GameList for O(n).
     * @return Returns a game ArrayList of game objects.
     */
    public ArrayList<Game> GetGameList(){
        return gameList;
    }

    /***
     * Gets game filter object that hold game filter lists.
     * @return Returns game filter object.
     */
    public GameFilters GetGameFilter(){
        return gameFilters;
    }

    /**
     * Used to set the user data to be saved.
     * @param userData Takes in an user data object at start to be set.
     */
    public void setUserData(UserData userData){
        this.userData = userData;
    }

    public String getGameDataPath(){
        return gameDataPath;
    }
}
