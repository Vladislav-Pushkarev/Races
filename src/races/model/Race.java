package races.model;

import races.Commons;
import races.view.RaceInfoPanel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Race implements Commons {
    private static final String RESTART_MESSAGE = "Race have been restarted";
    private final StringBuilder leaderBoard = new StringBuilder();
    private final int nRunners;
    private static int raceCounter;
    private int score = 0;
    private boolean isStarted = false;
    private List<Runner> runnerList;
    private ExecutorService service;
    private Runner currentLeader;

    public Race(int nRunners) {
        this.nRunners = nRunners;
        initRace();
    }

    //start ExecutorThreadPool
    public void start() {
        raceCounter++;
        if (isStarted) {
            stop();

            System.out.println(RESTART_MESSAGE);
            service = Executors.newCachedThreadPool();
        }
        isStarted = true;

        for (Runner runner : runnerList) {
            try {
                service.submit(runner);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        service.shutdown();
    }

    public void changeName(int runnerIndex, String name) {
        runnerList.get(runnerIndex).setName(name);
    }

    public Runner getCurrentLeader() {
        return currentLeader;
    }

    public List<Runner> getRunnerList() {
        return runnerList;
    }

    void checkLeader(Runner runner) {
        if (currentLeader == null) {
            currentLeader = runner;
        }
        if (runner.getX() > currentLeader.getX()) {
            currentLeader = runner;
            RaceInfoPanel.showCurrentLeader();
        }
    }

    synchronized void makeLeaderBoard(String name) {
        if (score == 0) {
            leaderBoard.append("\n")
                    .append("     Leader board of Race №")
                    .append(raceCounter)
                    .append("\n\n");
        }
        score++;
        synchronized (leaderBoard) {
            leaderBoard.append(score)
                    .append(" : ")
                    .append(name)
                    .append("\n");
        }
        //if all runners finished print leaderboard
        if (score == nRunners) {
            System.out.println(leaderBoard.toString());
            RaceInfoPanel.showLeaderBoard(leaderBoard.toString());
            leaderBoard.setLength(0); //clear text
            score = 0;
        }
    }

    private void stop() {
        for (Runner runner : runnerList) {
            runner.goToStart();
        }
        try {
            service.shutdown();
            service.awaitTermination(2, TimeUnit.SECONDS);
        } catch (InterruptedException ignore) {
            //ignore exception
        } finally {
            service.shutdownNow();
        }
    }

    private void initRace() {
        runnerList = new ArrayList<>(nRunners);
        service = Executors.newCachedThreadPool();

        int trackPosition = 0;
        for (int i = 0; i < nRunners; i++) {

            String name = "Racer №" + (i + 1);
            Runner runner = new Cockroach(0, trackPosition, this);
            runner.setName(name);
            runnerList.add(runner);
            trackPosition += TRACK_WIDTH;
        }
    }

}
