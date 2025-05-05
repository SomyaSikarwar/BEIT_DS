// SlaveNode.java
import java.io.*;
import java.net.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class SlaveNode {
    private static final String MASTER_IP = "localhost";
    private static final int PORT = 12345;
    private static final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: java SlaveNode <time_offset_in_minutes>");
            return;
        }

        StringBuffer log = new StringBuffer();

        // Initialize with offset
        int offsetMinutes = Integer.parseInt(args[0]);
        long offsetMillis = (long) offsetMinutes * 60 * 1000;
        long localTime = System.currentTimeMillis() + offsetMillis;

        // Connect to master
        Socket socket = new Socket(MASTER_IP, PORT);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        String msg = in.readUTF();
        int processID = in.readInt();

        log.append(String.format("[INFO] Slave : %d started with %d minutes offset\n", processID, offsetMinutes));
        log.append(String.format("[TIME] Initial local time: %s\n", formatter.format(new Date(localTime))));

        if ("SEND_TIME".equals(msg)) {
            out.writeLong(localTime);
            log.append(String.format("[SYNC] Process : %d Sent local time to master\n", processID));
        }

        // Receive offset from master
        long offset = in.readLong();
        long adjustedTime = localTime + offset;

        log.append(String.format("[SYNC] Process : %d received offset of %d ms\n", processID, offset));
        log.append(String.format("[FINAL] Synchronized time at Process %d: %s\n",
                processID, formatter.format(new Date(adjustedTime))));

        log.append("\n");
        System.out.print(log);
        socket.close();
    }
}