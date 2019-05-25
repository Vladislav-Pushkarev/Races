package races.model;

import races.Commons;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Cockroach extends Runner implements Runnable, Commons {
    private final String imgMove = "/cockroach_move.gif";
    private final String imgStop = "/cockroach_stop.gif";
    private Image currentImage;
    private Image moveImg;
    private Image standImg;

    Cockroach(int x, int y, Race race) {
        super(x, y, race);

        moveImg = Toolkit.getDefaultToolkit().getImage(getClass().getResource(imgMove));
        currentImage = moveImg;
        standImg = Toolkit.getDefaultToolkit().getImage(getClass().getResource(imgStop));
    }

    @Override
    public void draw(Graphics g, JPanel observer) {
        g.drawImage(currentImage, getX(), getY(), RUNNER_WIDTH, RUNNER_HEIGHT, observer);
    }

    @Override
    public void run() {
        setStopped(false);
        while (!isStopped() && isFinished()) {
            move();
            try {
                int random = (int) (Math.random() * MAX_DELAY);
                TimeUnit.MILLISECONDS.sleep(random);
            } catch (InterruptedException ignore) {
                //ignore exception
            }
        }
    }

    @Override
    public void move() {
        if (getX() < FINISH) {
            setX(getX() + getOneStep());
            getRace().checkLeader(this);
        }

        if (getX() >= FINISH) {
            setStopped(true);
            setX(FINISH);
            currentImage = standImg;
            getRace().makeLeaderBoard(getName());
        }
    }

    @Override
    void goToStart() {
        super.goToStart();
        currentImage = moveImg;
    }
}
