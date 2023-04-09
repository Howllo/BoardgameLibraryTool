package Backend.Database;

import DataParsing.Game;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GameFilters {
    ArrayList<Game> oldestToNewestReleaseGameFilter = new ArrayList<>();
    ArrayList<Game> newestToOldestReleaseGameFilter = new ArrayList<>();
    ArrayList<Game> aToZNameGameFilter = new ArrayList<>();
    ArrayList<Game> zToANameGameFilter = new ArrayList<>();
    ArrayList<Game> smallestToLargeMinPlayerGameFilter = new ArrayList<>();
    ArrayList<Game> largestToSmallestMinPLayerGameFilter = new ArrayList<>();
    ArrayList<Game> smallestToLargestMaxPlayerGameFilter = new ArrayList<>();
    ArrayList<Game> largestToSmallestMaxPlayerGameFilter = new ArrayList<>();

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

    private void CreateOldestToNewestReleaseGameFilter(ArrayList<Game> games){
        oldestToNewestReleaseGameFilter = (ArrayList<Game>) games.clone();

        Collections.sort(oldestToNewestReleaseGameFilter, new Comparator<Game>() {
            @Override
            public int compare(Game o1, Game o2) {
                return o1.getPublicationYear().compareTo(o2.getPublicationYear());
            }
        });
    }

    private void CreateNewestToOldestReleaseGameFilter(ArrayList<Game> games){
        newestToOldestReleaseGameFilter = (ArrayList<Game>) games.clone();

        Collections.sort(newestToOldestReleaseGameFilter, new Comparator<Game>() {
            @Override
            public int compare(Game o1, Game o2) {
                return o2.getPublicationYear().compareTo(o1.getPublicationYear());
            }
        });
    }

    private void CreateAToZNameGameFilter(ArrayList<Game> games){
        aToZNameGameFilter = (ArrayList<Game>) games.clone();

        Collections.sort(aToZNameGameFilter, new Comparator<Game>() {
            @Override
            public int compare(Game o1, Game o2) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
        });
    }

    private void CreateZToANameGameFilter(ArrayList<Game> games){
        zToANameGameFilter = (ArrayList<Game>) games.clone();

        Collections.sort(aToZNameGameFilter, new Comparator<Game>() {
            @Override
            public int compare(Game o1, Game o2) {
                return o2.getTitle().compareTo(o1.getTitle());
            }
        });
    }

    private void CreateSmallestToLargestMinPlayerGameFilter(ArrayList<Game> games){
        smallestToLargeMinPlayerGameFilter = (ArrayList<Game>) games.clone();

        Collections.sort(smallestToLargeMinPlayerGameFilter, new Comparator<Game>() {
            @Override
            public int compare(Game o1, Game o2) {
                return o1.getMinPlayers().compareTo(o2.getMinPlayers());
            }
        });
    }

    private void CreateLargestToSmallestMinPLayerFilter(ArrayList<Game> games){
        largestToSmallestMinPLayerGameFilter = (ArrayList<Game>) games.clone();

        Collections.sort(smallestToLargeMinPlayerGameFilter, new Comparator<Game>() {
            @Override
            public int compare(Game o1, Game o2) {
                return o2.getMinPlayers().compareTo(o1.getMinPlayers());
            }
        });
    }

    private void CreateSmallestToLargestMaxPlayerGameFilter(ArrayList<Game> games){
        smallestToLargestMaxPlayerGameFilter = (ArrayList<Game>) games.clone();

        Collections.sort(smallestToLargestMaxPlayerGameFilter, new Comparator<Game>() {
            @Override
            public int compare(Game o1, Game o2) {
                return o1.getMaxPlayer().compareTo(o2.getMaxPlayer());
            }
        });
    }

    private void CreateLargestToSmallestMaxPlayerGameFilter(ArrayList<Game> games){
        largestToSmallestMaxPlayerGameFilter = (ArrayList<Game>) games.clone();

        Collections.sort(largestToSmallestMaxPlayerGameFilter, new Comparator<Game>() {
            @Override
            public int compare(Game o1, Game o2) {
                return o2.getMaxPlayer().compareTo(o1.getMaxPlayer());
            }
        });
    }

    public ArrayList<Game> GetOldToNewReleaseFilter(){
        return oldestToNewestReleaseGameFilter;
    }

    public ArrayList<Game> GetNewToOldReleaseFilter() { return newestToOldestReleaseGameFilter; }

    public ArrayList<Game> GetAToZNameFilter(){
        return aToZNameGameFilter;
    }

    public ArrayList<Game> GetZToANameFilter() { return zToANameGameFilter; }

    public ArrayList<Game> GetSmallestToLargestMinPlayerFilter(){
        return smallestToLargeMinPlayerGameFilter;
    }

    public ArrayList<Game> GetLargestToSmallestMinPLayerFilter() { return largestToSmallestMinPLayerGameFilter; }

    public ArrayList<Game> GetSmallestToLargestMaxPlayerFilter(){
        return smallestToLargestMaxPlayerGameFilter;
    }

    public ArrayList<Game> GetLargestToSmallestMaxPlayerFilter() { return largestToSmallestMaxPlayerGameFilter; }
}