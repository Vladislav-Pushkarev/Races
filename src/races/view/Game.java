package races.view;

import races.Commons;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame implements Commons {
    private MainMenu mainMenu;

    public static void main(String[] args) {
        Game game = new Game();
        game.initUI();
    }

    //close Main menu and open Game panel
    void openGamePanel(int nTracks) {
        remove(mainMenu);

        GamePanel gamePanel = new GamePanel(nTracks);

        RaceInfoPanel raceInfoPanel = new RaceInfoPanel(gamePanel);

        JPanel wrapper1 = new JPanel(new BorderLayout());
        wrapper1.add(gamePanel);
        JScrollPane scroll = new JScrollPane(wrapper1);
        JScrollPane scroll2 = new JScrollPane(raceInfoPanel);
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        add(split, BorderLayout.CENTER);
        split.setLeftComponent(scroll);
        split.setRightComponent(scroll2);

        pack();
    }

    private void initUI() {
        mainMenu = new MainMenu(this);

        setTitle("Cockroach Races");
        add(mainMenu);
        pack();
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
