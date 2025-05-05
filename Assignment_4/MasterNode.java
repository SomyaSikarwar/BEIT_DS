// MasterNode.java
import java.io.*;
import java.net.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class MasterNode {
    private static final int PORT = 12345;
    private static final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");

    public static void main(String[] args) throws IOException {
        StringBuffer log = new StringBuffer();

        if (args.length != 1) {
            System.out.println("Usage: java MasterNode <number_of_slaves>");
            return;
        }

        int NUM_SLAVES = Integer.parseInt(args[0]);
        ServerSocket serverSocket = new ServerSocket(PORT);
        List<Socket> slaveSockets = new ArrayList<>();
        List<Long> slaveTimes = new ArrayList<>();

        log.append("[INFO] Master waiting for " + NUM_SLAVES + " slave connections...\n");
        System.out.print(log);
        log.setLength(0);

        while (slaveSockets.size() < NUM_SLAVES) {
            Socket socket = serverSocket.accept();
            slaveSockets.add(socket);
            log.append("[INFO] Slave " + (slaveSockets.size()) + " connected from: " + socket.getInetAddress() + "\n");
        }

        long masterTime = System.currentTimeMillis();
        log.append("\n[TIME] Master's current time: " + formatter.format(new Date(masterTime)) + "\n");

        // Collect slave times
        for (Socket s : slaveSockets) {
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            out.writeUTF("SEND_TIME");
            out.writeInt(slaveSockets.indexOf(s) + 1);
        }

        for (int i = 0; i < slaveSockets.size(); i++) {
            DataInputStream in = new DataInputStream(slaveSockets.get(i).getInputStream());
            long slaveTime = in.readLong();
            slaveTimes.add(slaveTime);
            log.append("[TIME] Slave " + (i+1) + " time: " + formatter.format(new Date(slaveTime)) + "\n");
        }

        // Calculate average
        long sum = masterTime;
        for (long time : slaveTimes) sum += time;
        long averageTime = sum / (NUM_SLAVES + 1);
        log.append("\n[SYNC] Adjusted time: " + formatter.format(new Date(averageTime)) + "\n");

        // Calculate and send offsets
        long masterOffset = averageTime - masterTime;
        log.append("[SYNC] Master offset: " + masterOffset + " ms\n");

        for (int i = 0; i < slaveSockets.size(); i++) {
            long offset = averageTime - slaveTimes.get(i);
            DataOutputStream out = new DataOutputStream(slaveSockets.get(i).getOutputStream());
            out.writeLong(offset);
            log.append("[SYNC] Sent offset " + offset + " ms to Slave " + (i+1) + "\n");
        }

        System.out.print(log);
        serverSocket.close();
    }
}