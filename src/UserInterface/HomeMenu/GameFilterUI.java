package UserInterface.HomeMenu;

import DataParsing.Game;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameFilterUI extends JScrollPane {
    ArrayList<GameRowPanel> gameRowPanels = new ArrayList<>();

    GameFilterUI(ArrayList<Game> games){
        setLocation(375, 75);
        getViewport().setBackground(Color.decode("#333333"));
        setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);

        for (Game game : games) {
            GameRowPanel createGameRow = new GameRowPanel(game, this);
            gameRowPanels.add(createGameRow);
            getViewport().add(createGameRow, null);
        }
        setVisible(true);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 500);
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(600, 500);
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(600, 500);
    }
}
