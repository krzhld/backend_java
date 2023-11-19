package edu.hw6.task6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("EmptyBlock")
public class PortsScanner {
    public PortsScanner() {
        busyPorts = new ArrayList<>();
    }

    private final List<Port> busyPorts;
    private static final int MIN_PORT = 0;
    private static final int MAX_PORT = 49151;

    private void checkTCPPort(int port) {
        try (ServerSocket socket = new ServerSocket(port)) {
        } catch (IOException e) {
            busyPorts.add(new Port(Protocol.TCP, port, PortsKnown.getInfoByPort(port)));
        }
    }

    private void checkUDPPort(int port) {
        try (DatagramSocket socket = new DatagramSocket(port)) {
        } catch (IOException e) {
            busyPorts.add(new Port(Protocol.UDP, port, PortsKnown.getInfoByPort(port)));
        }
    }

    public void checkAllPorts() {
        for (int i = MIN_PORT; i <= MAX_PORT; i++) {
            checkTCPPort(i);
            checkUDPPort(i);
        }
    }

    public List<Port> getBusyPorts() {
        return busyPorts;
    }

    public String printOccupiedPorts() {
        if (busyPorts.isEmpty()) {
            checkAllPorts();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Протокол\tПорт\tСервис\n");
        busyPorts.forEach(v -> stringBuilder
            .append(v.protocol())
            .append("\t")
            .append(v.port())
            .append("\t")
            .append(v.info())
            .append("\n"));
        return stringBuilder.toString();
    }
}
