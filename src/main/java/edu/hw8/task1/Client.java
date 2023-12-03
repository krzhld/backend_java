package edu.hw8.task1;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Closeable {

    private final Socket socket;
    private final BufferedReader bufferedReader;
    private final PrintWriter printWriter;

    public Client(String host, int port) {
        try {
            this.socket = new Socket(host, port);
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.printWriter = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String sendMessage(String message) {
        String reply;
        try {
            printWriter.println(message);
            reply = bufferedReader.readLine();
        } catch (IOException e) {
            close();
            throw new RuntimeException(e);
        }
        return reply;
    }

    @Override
    public void close() {
        try {
            if (socket != null) {
                printWriter.close();
                bufferedReader.close();
                socket.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
