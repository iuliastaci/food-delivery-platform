package main.model;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AuditLog {
    private static final String AUDIT_LOG_FILE = "audit_log.csv";

    public static void logAction(String action) {
        try (FileWriter writer = new FileWriter(AUDIT_LOG_FILE, true)) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timestamp.getTime()));
            writer.append(action).append(",").append(formattedDate).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void viewAuditLog() {
        // Implementation to read and display the audit log
        // Read from CSV file
        // Display logic here
        logAction("viewAuditLog");
    }
}

