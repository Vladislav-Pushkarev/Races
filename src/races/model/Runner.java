package races.model;

import races.Commons;

import javax.swing.*;
import java.awt.*;

public abstract class Runner implements Commons, Runnable {
    volatile int x;
    int y;
    int oneStep = DEFAULT_RUNNER_SPEED;
    boolean isStopped = true;
    String name;
    Race race;

    Runner(int x, int y, Race race) {
        this.race = race;
        this.x = x;
        this.y = y;
    }

    public abstract void move();

    public void haste() {
        x += DEFAULT_RUNNER_HASTE;
        move();
    }

    //draw movement in JPanel
    public abstract void draw(Graphics g, JPanel observer);

    public String getName() {
        return name;
    }

    public boolean isStopped() {
        return isStopped;
    }

    //Determines if the specified point is contained within the region defined by Runner
    public boolean isClicked(Point point) {
        return isClicked(point.x, point.y);
    }

    boolean isFinished() {
        return x < FINISH;
    }

    void goToStart() {
        isStopped = true;
        x = 0;
    }

    int getX() {
        return x;
    }

    void setName(String name) {
        this.name = name;
    }

    //Determines if the specified coordinates is contained within the region defined by Runner
    private boolean isClicked(int x, int y) {
        return x >= this.x &&
                x < this.x + RUNNER_WIDTH &&
                y >= this.y &&
                y < this.y + RUNNER_HEIGHT;
    }
}
