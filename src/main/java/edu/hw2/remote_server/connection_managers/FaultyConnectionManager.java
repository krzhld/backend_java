package edu.hw2.remote_server.connection_managers;

import edu.hw2.remote_server.connections.Connection;
import edu.hw2.remote_server.connections.FaultyConnection;

public class FaultyConnectionManager implements ConnectionManager {

    private static final double DEFAULT_CONNECTION_PROBABILITY_OF_FAILURE = 0.25;
    private static final double TRUE_PROBABILITY = 1.0;
    private static final double FALSE_PROBABILITY = 0.0;
    private double connectionProbabilityOfFailure;

    public FaultyConnectionManager() {
        this.connectionProbabilityOfFailure = DEFAULT_CONNECTION_PROBABILITY_OF_FAILURE;
    }

    public FaultyConnectionManager(double connectionProbabilityOfFailure) {
        this.connectionProbabilityOfFailure = connectionProbabilityOfFailure;
    }

    @Override
    public Connection getConnection() {
        return new FaultyConnection(connectionProbabilityOfFailure);
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
