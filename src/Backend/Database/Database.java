package Backend.Database;

import Backend.User.UserData;
import DataParsing.Game;
import DataParsing.UserDataParser;
import DataParsing.XMLParserUtility;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
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

    //region Data Director Pathing
    private final String gameDataXML = "bgg90Games.xml";
    private final String _userDataPath = "data\\user_data\\";
    private final File gameDataBase = new File(gameDataXML);
    private final String absPath = gameDataBase.getAbsolutePath();
    private final String gameDataPath = absPath.replace(gameDataXML, _userDataPath);
    private final HashMap<String, Game> gameHashID = new HashMap<>();
    private final HashMap<String, Game> gameHashMapTitle = new HashMap<>();
    //endregion

    private ArrayList<Game> gameList;
    private final GameFilters gameFilters;
    private UserData userData;
    private UserDataParser userDataParser;

    private Database() throws IOException {

        // Create XML Parser Utility
        try{
            String _gameDataPath = "data\\game_data\\";
            String path = absPath.replace(gameDataXML, _gameDataPath + gameDataXML);
            XMLParserUtility parser = new XMLParserUtility(path);
            gameList = parser.retrieveGameList();
            for (Game game: gameList){
                gameHashID.put(game.getGameId(), game);
                gameHashMapTitle.put(game.getTitle(), game);
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
     * Get game from a hash map using the game id.
     * @return the of Hashmap[Game ID String, Game].
     */
    public Game GetGameFromHashGameID(String gameID){
        return gameHashID.get(gameID);
    }

    /**
     *
     * @param title Get game from a hash map by using the game title.
     * @return the game from HashMap[Game Title, Game].
     */
    public Game GetGameFromHashTitle(String title){
        return gameHashMapTitle.get(title);
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
     * Create a new user for the current file.
     * @param username Takes in a string username.
     * @param password Takes in a character array for password.
     */
    public boolean createNewUserData(String username, char[] password){
        UserData newUser = new UserData(username, password, -1, new ArrayList<>());

        // Create User Data Parser
        try{
            userDataParser = new UserDataParser(gameDataPath + username + ".xml");
        } catch (FileNotFoundException e){
            System.out.println("User Data Parser File Error: " + e.getMessage());
        } catch (IOException e){
            System.out.println("User Data Parser IO Error: " + e.getMessage());
        }

        try{
            userData = newUser;
            return userDataParser.createUserXML(newUser);
        } catch (ParserConfigurationException e){
            System.out.println("Error creating new user XML: " + e.getMessage());
            return false;
        } catch (TransformerException e) {
            System.out.println("Transformer error: " + e.getMessage());
            return false;
        } catch (FileNotFoundException fnfe){
            System.out.println("File not found: " + fnfe.getMessage());
            return false;
        }
    }

    /**
     * Load user and set the userdata object.
     * @param username Takes in username to search through directory to return userdata object.
     */
    public UserData setUserData(String username, char[] password){
        // Create User Data Parser
        try{
            if(userDataParser == null)
                userDataParser = new UserDataParser(gameDataPath + username + ".xml");
        } catch (FileNotFoundException e){
            System.out.println("User Data Parser File Error: " + e.getMessage());
        } catch (IOException e){
            System.out.println("User Data Parser IO Error: " + e.getMessage());
        }

        if(userDataParser != null){
            userData = userDataParser.getUser(username, password);
        }
        return userData;
    }

    /**
     * Get the data path for user data.
     * @return a string of the game data path.
     */
    public String getGameDataPath(){
        return gameDataPath;
    }

    /**
     * Get the user data object.
     * @return the UserData object.
     */
    public UserData getUserData(){
        return userData;
    }

    /**
     * Save the user data to a XML file.
     */
    public void SaveUserData(){
        try{
            userDataParser.createUserXML(userData);
        } catch (ParserConfigurationException e){
            System.out.println("Creating User XML Error: " + e.getMessage());
        } catch (TransformerException e) {
            System.out.println("Transformer error: " + e.getMessage());
        } catch (FileNotFoundException fnfe){
            System.out.println("File not found: " + fnfe.getMessage());
        }
    }
}
