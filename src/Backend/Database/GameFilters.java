package Backend.Database;

import DataParsing.Game;

import java.lang.reflect.Array;
import java.util.ArrayList;

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

    }

    private void CreateNameGameFilter(ArrayList<Game> games){

    }

    private void CreateMinPlayerGameFilter(ArrayList<Game> games){

    }

    private void CreateMaxPlayerGameFilter(ArrayList<Game> games){

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