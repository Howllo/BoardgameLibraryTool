package Backend.Database;

import DataParsing.Game;

//<<<<<<< parser_update
//=======

//>>>>>>> master
import java.util.ArrayList;
import java.util.Comparator;

public class GameFilters {
    private ArrayList<Game> oldestToNewestReleaseGameFilter = new ArrayList<>();
    private ArrayList<Game> newestToOldestReleaseGameFilter = new ArrayList<>();
    private ArrayList<Game> aToZNameGameFilter = new ArrayList<>();
    private ArrayList<Game> zToANameGameFilter = new ArrayList<>();
    private ArrayList<Game> smallestToLargeMinPlayerGameFilter = new ArrayList<>();
    private ArrayList<Game> largestToSmallestMinPLayerGameFilter = new ArrayList<>();
    private ArrayList<Game> smallestToLargestMaxPlayerGameFilter = new ArrayList<>();
    private ArrayList<Game> largestToSmallestMaxPlayerGameFilter = new ArrayList<>();
    /***
     * Create all the filter array list within the contructor.
     * Requires: Array List of game objects.
     * If array list is null then return early.
     * @param games Takes in a game object array list to be to process.
     */
    public GameFilters(ArrayList<Game> games){
        if(games == null) return;

        // Release Date Filters
        CreateOldestToNewestReleaseGameFilter(games);
        CreateNewestToOldestReleaseGameFilter(games);

        // Title Filters
        CreateAToZNameGameFilter(games);
        CreateZToANameGameFilter(games);

        // Min PLayer Filters
        CreateSmallestToLargestMinPlayerGameFilter(games);
        CreateLargestToSmallestMinPLayerFilter(games);

        // Max Player Filters
        CreateSmallestToLargestMaxPlayerGameFilter(games);
        CreateLargestToSmallestMaxPlayerGameFilter(games);
    }

    /***
     * Create a sorted array list based on year release from oldest to newest.
     * Clones the original array list and set it to this object array list.
     * @param games Takes in an array list of game objects.
     */
    private void CreateOldestToNewestReleaseGameFilter(ArrayList<Game> games){
        oldestToNewestReleaseGameFilter = new ArrayList<>(games);

        oldestToNewestReleaseGameFilter.sort(new Comparator<Game>() {
            @Override
            public int compare(Game o1, Game o2) {
                return o1.getPublicationYear().compareTo(o2.getPublicationYear());
            }
        });
    }

    /***
     * Create a sorted list of newest to oldest release.
     * Clones the original array list and set it to this object array list.
     * @param games Takes in an array list of game objects.
     */
    private void CreateNewestToOldestReleaseGameFilter(ArrayList<Game> games){
        newestToOldestReleaseGameFilter = new ArrayList<>(games);

        newestToOldestReleaseGameFilter.sort(new Comparator<Game>() {
            @Override
            public int compare(Game o1, Game o2) {
                return o2.getPublicationYear().compareTo(o1.getPublicationYear());
            }
        });
    }

    /***
     * Create a sorted list of title start from A to Z.
     * Clones the original array list and set it to this object array list.
     * @param games Takes in an array list of game objects.
     */
    private void CreateAToZNameGameFilter(ArrayList<Game> games){
        aToZNameGameFilter = new ArrayList<>(games);

        aToZNameGameFilter.sort(new Comparator<Game>() {
            @Override
            public int compare(Game o1, Game o2) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
        });
    }

    /***
     * Create a sorted list of title that start with Z and goes to A.
     * Clones the original array list and set it to this object array list.
     * @param games Takes in an array list of game objects.
     */
    private void CreateZToANameGameFilter(ArrayList<Game> games){
        zToANameGameFilter = new ArrayList<>(games);

        aToZNameGameFilter.sort(new Comparator<Game>() {
            @Override
            public int compare(Game o1, Game o2) {
                return o2.getTitle().compareTo(o1.getTitle());
            }
        });
    }

    /***
     * Create a sorted list of smallest to largest min players.
     * Clones the original array list and set it to this object array list.
     * @param games Takes in an array list of game objects.
     */
    private void CreateSmallestToLargestMinPlayerGameFilter(ArrayList<Game> games){
        smallestToLargeMinPlayerGameFilter = new ArrayList<>(games);

        smallestToLargeMinPlayerGameFilter.sort(new Comparator<Game>() {
            @Override
            public int compare(Game o1, Game o2) {
                return o1.getMinPlayers().compareTo(o2.getMinPlayers());
            }
        });
    }

    /**
     * Create a sorted array list from largest to smallest min players.
     * Clones the original array list and set it to this object array list.
     * @param games Takes in an array list of game objects.
     */
    private void CreateLargestToSmallestMinPLayerFilter(ArrayList<Game> games){
        largestToSmallestMinPLayerGameFilter = new ArrayList<>(games);

        smallestToLargeMinPlayerGameFilter.sort(new Comparator<Game>() {
            @Override
            public int compare(Game o1, Game o2) {
                return o2.getMinPlayers().compareTo(o1.getMinPlayers());
            }
        });
    }

    /***
     * Create a sorted list of smallest to largest of max player.
     * Clones the original array list and set it to this object array list.
     * @param games Takes in an array list of game objects.
     */
    private void CreateSmallestToLargestMaxPlayerGameFilter(ArrayList<Game> games){
        smallestToLargestMaxPlayerGameFilter = new ArrayList<>(games);

        smallestToLargestMaxPlayerGameFilter.sort(new Comparator<Game>() {
            @Override
            public int compare(Game o1, Game o2) {
                return o1.getMaxPlayer().compareTo(o2.getMaxPlayer());
            }
        });
    }

    /**
     * Creates a list of largest to smallest max player list.
     * Clones the original array list and set it to this object array list.
     * @param games Takes in an array list of game objects.
     */
    private void CreateLargestToSmallestMaxPlayerGameFilter(ArrayList<Game> games){
        largestToSmallestMaxPlayerGameFilter = new ArrayList<>(games);

        largestToSmallestMaxPlayerGameFilter.sort(new Comparator<Game>() {
            @Override
            public int compare(Game o1, Game o2) {
                return o2.getMaxPlayer().compareTo(o1.getMaxPlayer());
            }
        });
    }

    /**
     * Used to get the array list of oldest to newest games.
     * @return Array list of game objects from oldest to newest.
     */
    public ArrayList<Game> GetOldToNewReleaseFilter(){
        return oldestToNewestReleaseGameFilter;
    }

    /**
     * Gets the newest to oldest array list of games
     * @return Array list of game object from newest to oldest.
     */
    public ArrayList<Game> GetNewToOldReleaseFilter(){
        return newestToOldestReleaseGameFilter;
    }

    /**
     * Gets an array list of A to Z title filter.
     * @return Returns an array list of game object that is sorted from A to Z title.
     */
    public ArrayList<Game> GetAToZNameFilter(){
        return aToZNameGameFilter;
    }

    /**
     * Get an array list of Z to A titles.
     * @return Array list of Z to A title game objects.
     */
    public ArrayList<Game> GetZToANameFilter(){
        return zToANameGameFilter;
    }

    /**
     * Get an array list of Games object for min player.
     * @return Returns smallest to largest min player filter.
     */
    public ArrayList<Game> GetSmallestToLargestMinPlayerFilter(){
        return smallestToLargeMinPlayerGameFilter;
    }

    /**
     * Get an array list of game objects that is sorted for min players.
     * @return Returns an array list of sorted based off of largest to smallest min players.
     */
    public ArrayList<Game> GetLargestToSmallestMinPLayerFilter(){
        return largestToSmallestMinPLayerGameFilter;
    }

    /**
     * Get an array list of game object that is sorted for max players.
     * @return Return an array list of smallest to largest max player filter.
     */
    public ArrayList<Game> GetSmallestToLargestMaxPlayerFilter(){
        return smallestToLargestMaxPlayerGameFilter;
    }

//<<<<<<< parser_update
    /**
     * Get an array list of largest to smallest max player filter
     * @return An array list of game object from largest to smallest.
     */
   // public ArrayList<Game> GetLargestToSmallestMaxPlayerFilter(){
 //       return largestToSmallestMaxPlayerGameFilter;
   // }
//}
//=======
   // public ArrayList<Game> GetLargestToSmallestMaxPlayerFilter() { return largestToSmallestMaxPlayerGameFilter; }
}
//>>>>>>> master
