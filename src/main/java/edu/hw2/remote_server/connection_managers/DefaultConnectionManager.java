package edu.hw2.remote_server.connection_managers;

import edu.hw2.remote_server.connections.Connection;
import edu.hw2.remote_server.connections.FaultyConnection;
import edu.hw2.remote_server.connections.StableConnection;

public class DefaultConnectionManager implements ConnectionManager {
    private double probabilityOfFailure;
    private double connectionProbabilityOfFailure;
    private static final double DEFAULT_PROBABILITY_OF_FAILURE = 0.25;
    private static final double DEFAULT_CONNECTION_PROBABILITY_OF_FAILURE = 0.25;
    private static final double TRUE_PROBABILITY = 1.0;
    private static final double FALSE_PROBABILITY = 0.0;

    public DefaultConnectionManager() {
        this.probabilityOfFailure = DEFAULT_PROBABILITY_OF_FAILURE;
        this.connectionProbabilityOfFailure = DEFAULT_CONNECTION_PROBABILITY_OF_FAILURE;
    }

    public DefaultConnectionManager(double probabilityOfFailure, double connectionProbabilityOfFailure) {
        this.probabilityOfFailure = probabilityOfFailure;
        this.connectionProbabilityOfFailure = connectionProbabilityOfFailure;
    }

    @Override
    public Connection getConnection() {
        double probability = Math.random();
        if (probability < probabilityOfFailure) {
            return new FaultyConnection(connectionProbabilityOfFailure);
        } else {
            return new StableConnection();
        }
    }

    public void setProbabilityOfFailure(double probabilityOfFailure) {
        checkProbabilityValue(probabilityOfFailure);
        this.probabilityOfFailure = probabilityOfFailure;
    }

    public void setConnectionProbabilityOfFailure(double connectionProbabilityOfFailure) {
        checkProbabilityValue(connectionProbabilityOfFailure);
        this.connectionProbabilityOfFailure = connectionProbabilityOfFailure;
    }

    private void checkProbabilityValue(double probability) {
        if (probability < FALSE_PROBABILITY || probability > TRUE_PROBABILITY) {
            throw new IllegalArgumentException("Incorrect probability value!");
        }
    }
}
