package edu.hw2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import edu.hw2.remote_server.PopularCommandExecutor;
import edu.hw2.remote_server.connection_managers.ConnectionManager;
import edu.hw2.remote_server.connection_managers.DefaultConnectionManager;
import edu.hw2.remote_server.connection_managers.FaultyConnectionManager;
import edu.hw2.remote_server.ConnectionException;

public class RemoteServerTest {
    static Arguments[] maxAttempts() {
        return new Arguments[] {Arguments.of(1), Arguments.of(2), Arguments.of(3),
            Arguments.of(4), Arguments.of(5)};
    }

    @ParameterizedTest
    @MethodSource("maxAttempts")
    @DisplayName("Вероятности ошибки подключения и подключателя нулевые. ConnectionManager обычный.")
    void testExecutorWithDefaultManagerWhenSuccessful(int maxAttempt) {
        double probabilityOfFailure = 0.0;
        double connectionProbabilityOfFailure = 0.0;
        ConnectionManager manager = new DefaultConnectionManager(
            probabilityOfFailure,
            connectionProbabilityOfFailure
        );
        PopularCommandExecutor executor = new PopularCommandExecutor(
            manager,
            maxAttempt
        );
        String command = "echo hello";

        assertThatCode(() ->
            executor.tryExecute(command))
            .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @MethodSource("maxAttempts")
    @DisplayName("Вероятности ошибки подключения и подключателя равны единице. ConnectionManager обычный.")
    void testExecutorWithDefaultManagerWhenFailed(int maxAttempt) {
        double probabilityOfFailure = 1.0;
        double connectionProbabilityOfFailure = 1.0;
        ConnectionManager manager = new DefaultConnectionManager(
            probabilityOfFailure,
            connectionProbabilityOfFailure
        );
        PopularCommandExecutor executor = new PopularCommandExecutor(
            manager,
            maxAttempt
        );
        String command = "echo hello";

        assertThatThrownBy(() ->
            executor.tryExecute(command))
            .hasCause(new ConnectionException());
    }

    @ParameterizedTest
    @MethodSource("maxAttempts")
    @DisplayName("Вероятность ошибки подключения равна 1. ConnectionManager проблемный.")
    void testExecutorWithFaultyManagerWhenFailed(int maxAttempt) {
        double connectionProbabilityOfFailure = 1.0;
        ConnectionManager manager = new FaultyConnectionManager(
            connectionProbabilityOfFailure
        );
        PopularCommandExecutor executor = new PopularCommandExecutor(
            manager,
            maxAttempt
        );
        String command = "echo hello";

        assertThatThrownBy(() ->
            executor.tryExecute(command))
            .hasCause(new ConnectionException());
    }

    @ParameterizedTest
    @MethodSource("maxAttempts")
    @DisplayName("Вероятность ошибки подключения нулевая. ConnectionManager проблемный.")
    void testExecutorWithFaultyManagerWhenSuccessful(int maxAttempt) {
        double connectionProbabilityOfFailure = 0.0;
        ConnectionManager manager = new FaultyConnectionManager(
            connectionProbabilityOfFailure
        );
        PopularCommandExecutor executor = new PopularCommandExecutor(
            manager,
            maxAttempt
        );
        String command = "echo hello";

        assertThatCode(() ->
            executor.tryExecute(command))
            .doesNotThrowAnyException();
    }
}
