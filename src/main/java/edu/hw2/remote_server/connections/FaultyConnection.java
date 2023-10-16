package edu.hw2.remote_server.connections;

import edu.hw2.remote_server.ConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {

    private double probabilityOfFailure;

    private static final double DEFAULT_PROBABILITY_OF_FAILURE = 0.25;
    private static final double TRUE_PROBABILITY = 1.0;
    private static final double FALSE_PROBABILITY = 0.0;
    private static final Logger LOGGER = LogManager.getLogger();

    public FaultyConnection() {
        this.probabilityOfFailure = DEFAULT_PROBABILITY_OF_FAILURE;
    }

    public FaultyConnection(double probabilityOfFailure) {
        this.probabilityOfFailure = probabilityOfFailure;
    }

    @Override
    public void execute(String command) {
        double probability = Math.random();
        if (probability < probabilityOfFailure) {
            throw new ConnectionException();
        }
        LOGGER.info(String.format("Command '%s' executed successfully!", command));
    }

    public void setProbabilityOfFailure(double probabilityOfFailure) {
        checkProbabilityValue(probabilityOfFailure);
        this.probabilityOfFailure = probabilityOfFailure;
    }

    private void checkProbabilityValue(double probability) {
        if (probability < FALSE_PROBABILITY || probability > TRUE_PROBABILITY) {
            throw new IllegalArgumentException("Incorrect probability value!");
        }
    }

    @Override
    public void close() throws Exception {
    }
}
