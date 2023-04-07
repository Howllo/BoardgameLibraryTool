package Backend.Database;

import DataParsing.Game;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

public class GameFilters {
    ArrayList<Game> releaseGameFilter = new ArrayList<>();
    ArrayList<Game> nameGameFilter = new ArrayList<>();
    ArrayList<Game> minPlayerGameFilter = new ArrayList<>();
    ArrayList<Game> maxPlayerGameFilter = new ArrayList<>();

    public GameFilters(ArrayList<Game> games){
        CreateReleaseGameFilter(games);
        CreateNameGameFilter(games);
        CreateMinPlayerGameFilter(games);
        CreateMaxPlayerGameFilter(games);
    }

    private void CreateReleaseGameFilter(ArrayList<Game> games){
        releaseGameFilter = (ArrayList<Game>) games.clone();

        games.sort(Comparator.comparing(Game::getPublicationYear));
    }

    private void CreateNameGameFilter(ArrayList<Game> games){
        nameGameFilter = (ArrayList<Game>) games.clone();

    }

    private void CreateMinPlayerGameFilter(ArrayList<Game> games){
        minPlayerGameFilter = (ArrayList<Game>) games.clone();

    }

    private void CreateMaxPlayerGameFilter(ArrayList<Game> games){
        maxPlayerGameFilter = (ArrayList<Game>) games.clone();
    }

    public ArrayList<Game> GetReleaseGameFilter(){
        return releaseGameFilter;
    }

    public ArrayList<Game> GetNameGameFilter(){
        return nameGameFilter;
    }

    public ArrayList<Game> GetMinPlayerGameFilter(){
        return minPlayerGameFilter;
    }

    public ArrayList<Game> GetMaxPlayerGameFilter(){
        return maxPlayerGameFilter;
    }
}