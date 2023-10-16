package edu.hw2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import edu.hw2.remote_server.PopularCommandExecutor;
import edu.hw2.remote_server.connection_managers.ConnectionManager;
import edu.hw2.remote_server.connection_managers.DefaultConnectionManager;
import edu.hw2.remote_server.ConnectionException;

public class RemoteServerTest {
    @Test
    @DisplayName("Вероятности ошибки подключения и подключателя нулевые")
    void testExecutorWithDefaultManagerWhenSuccessful() {
        double probabilityOfFailure = 0.0;
        double connectionProbabilityOfFailure = 0.0;
        ConnectionManager manager = new DefaultConnectionManager(
            probabilityOfFailure,
            connectionProbabilityOfFailure
        );
        int maxAttempts = 1;
        PopularCommandExecutor executor = new PopularCommandExecutor(
            manager,
            maxAttempts
        );
        String command = "echo hello";

        assertThatCode(() ->
            executor.tryExecute(command))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Вероятности ошибки подключения и подключателя равны единице")
    void testExecutorWithDefaultManagerWhenFailed() {
        double probabilityOfFailure = 1.0;
        double connectionProbabilityOfFailure = 1.0;
        ConnectionManager manager = new DefaultConnectionManager(
            probabilityOfFailure,
            connectionProbabilityOfFailure
        );
        int maxAttempts = 1;
        PopularCommandExecutor executor = new PopularCommandExecutor(
            manager,
            maxAttempts
        );
        String command = "echo hello";

        assertThatThrownBy(() ->
            executor.tryExecute(command))
            .hasCause(new ConnectionException());
    }
}
