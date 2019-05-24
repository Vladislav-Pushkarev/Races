package races.view;

import races.model.Race;

import javax.swing.*;
import java.awt.*;

public class RaceInfoPanel extends JPanel {
    private static JLabel leaderLabel;
    private static JTextArea leaderBoardLabel;
    private static Race race;

    RaceInfoPanel(GamePanel gamePanel) {
        race = gamePanel.getRace();
        initRaceInfoPanel();
    }

    public static void showCurrentLeader() {
        String leaderName = "UNDEFINED";
        if (race.getCurrentLeader() != null) {
            leaderName = race.getCurrentLeader().getName();
        }
        leaderLabel.setText("Current leader: " + leaderName);
    }

    public static void showLeaderBoard(String leaderBoard) {
        leaderLabel.setText("Race finished");
        leaderBoardLabel.setText(leaderBoard);
    }

    private void initRaceInfoPanel() {
        setLayout(new BorderLayout());
        setFocusable(true);
        Font font = new Font("Arial Black", Font.BOLD, 15);
        leaderLabel = new JLabel("**** Current leader: UNDEFINED ****");
        leaderLabel.setFont(font);

        leaderBoardLabel = new JTextArea();
        leaderBoardLabel.setFont(font);

        add(BorderLayout.NORTH, leaderLabel);
        add(BorderLayout.CENTER, leaderBoardLabel);
    }
}