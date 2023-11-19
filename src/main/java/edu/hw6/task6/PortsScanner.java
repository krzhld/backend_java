package edu.hw6.task6;

import java.io.CharArrayReader;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.Random;
import java.util.random.RandomGenerator;

public class PortsScanner {
    private PortsScanner() {

    }

    private static final int MIN_PORT = 0;
    private static final int MAX_PORT = 49151;

    public static boolean isPortFree(int port) throws IOException {
        if ((port < MIN_PORT) || (port > MAX_PORT)) {
            throw new IllegalArgumentException("Invalid port: " + port);
        }

        try (var serverSocket = new ServerSocket(port);
             var datagramSocket = new DatagramSocket(port)) {
            return true;
        } catch (IOException e) {
            return false;
        }

    }

    public static String getInfoAboutPort(int port)  throws MalformedURLException {
        try (var serverSocket = new ServerSocket(port);
             var datagramSocket = new DatagramSocket(port)) {
            return datagramSocket.toString();
        } catch (IOException e) {
            return "hello";
        }
    }

    public static void main(String[] args) throws IOException {
        //for (int i = 0; i < 1024; ++i) System.out.println(i + ": " + isPortFree(i));
        System.out.println(getInfoAboutPort(134));
    }
}
