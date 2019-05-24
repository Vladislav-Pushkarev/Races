package races.view;

import races.Commons;
import races.model.Race;
import races.model.Runner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements MouseListener, Commons {
    private List<JTextField> textFieldList;
    private Track track;
    private boolean isStarted = false;
    private final int nRunners;
    private Race race;

    GamePanel(int nRunners) {
        this.nRunners = nRunners;
        initGamePanel();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        track.draw(g);
        for (Runner runner : race.getRunnerList()) {
            runner.draw(g, this);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (Runner runner : race.getRunnerList()) {
            if (!runner.isStopped() && e.getClickCount() == CLICKS_TO_HASTE) {
                if (runner.isClicked(e.getPoint())) {
                    runner.haste();
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    Race getRace() {
        return race;
    }

    private void initGamePanel() {
        setLayout(null);
        textFieldList = new ArrayList<>(nRunners);
        setPanelSize();
        setFocusable(true);
        addMouseListener(this);
        setStartButton();

        this.race = new Race(nRunners);

        textFieldList = new ArrayList<>(nRunners);
        track = new Track(nRunners);

        createNameFields();
    }

    private void showRunnersNames() {
        List<Runner> runnerList = race.getRunnerList();
        for (int i = 0; i < nRunners; i++) {
            JLabel label = new JLabel(runnerList.get(i).getName());
            label.setBounds(FINISH, (TRACK_WIDTH) * (i + 1) - 30, 100, 30);
            label.setSize(new Dimension(100, 30));

            add(label);
        }
    }

    private void setPanelSize() {
        if (nRunners > 10) {
            setPreferredSize(new Dimension(GAME_WINDOW_WIDTH, TRACK_WIDTH * nRunners));
        } else {
            setPreferredSize(new Dimension(GAME_WINDOW_WIDTH, WINDOW_HEIGHT));
        }
    }

    private void setStartButton() {
        JButton button = new JButton("Start/Restart");
        Font font = new Font("Arial Black", Font.BOLD, 12);
        button.setFont(font);

        // create and add action listener
        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (JTextField textField : textFieldList) {
                    remove(textField);
                }
                if (!isStarted) {
                    showRunnersNames();
                }
                race.start();
            }
        };
        button.addActionListener(action);
        button.setBounds(760, 40, 130, 40);

        add(button);
    }

    private void createNameFields() {
        List<Runner> runnerList = race.getRunnerList();
        for (int i = 0; i < nRunners; i++) {
            JTextField textField = new JTextField(runnerList.get(i).getName());
            textField.setBounds(TRACK_LENGTH, (TRACK_WIDTH) * (i + 1) - 30, 100, 30);
            textField.setSize(new Dimension(100, 30));
            textFieldList.add(textField);

            // create and add action listener
            int runnerIndex = i;
            Action action = new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    race.changeName(runnerIndex, textField.getText());
                }
            };

            textField.addActionListener(action);
            add(textField);
        }
    }
}