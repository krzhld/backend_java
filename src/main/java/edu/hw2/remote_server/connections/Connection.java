package edu.hw2.remote_server.connections;

public interface Connection extends AutoCloseable {
    void execute(String command);
}
