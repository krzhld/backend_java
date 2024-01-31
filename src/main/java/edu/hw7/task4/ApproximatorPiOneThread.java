package edu.hw7.task4;

import java.util.concurrent.ThreadLocalRandom;

public class ApproximatorPiOneThread extends Thread {

    private final static double RADIUS = 0.5;
    private final static double CENTER_X = 0.5;
    private final static double CENTER_Y = 0.5;
    private final ThreadLocalRandom random;
    private final int numberIterations;
    private int numberPointsInCircle;

    public ApproximatorPiOneThread(int numberIterations) {
        this.numberIterations = numberIterations;
        random = ThreadLocalRandom.current();
    }

    @Override
    public void run() {
        for (int i = 0; i < numberIterations; i++) {
            double x = random.nextDouble();
            double y = random.nextDouble();
            if (isPointInCircle(x, y)) {
                ++numberPointsInCircle;
            }
        }
    }

    private boolean isPointInCircle(double x, double y) {
        double distance = Math.sqrt(Math.pow(x - CENTER_X, 2) + Math.pow(y - CENTER_Y, 2));
        return (distance <= RADIUS);
    }

    public int getNumberIterations() {
        return numberIterations;
    }

    public int getCircleCount() {
        return numberPointsInCircle;
    }
}
