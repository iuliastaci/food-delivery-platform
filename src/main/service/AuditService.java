package main.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AuditService {
    private static final String FILE_PATH = "audit_log.csv";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void logAction(String action) {
    }

    public static void viewAuditLog() {
    }

    public void logTransaction(String action, String details) {
        try (FileWriter fw = new FileWriter(FILE_PATH, true);
             PrintWriter pw = new PrintWriter(fw)) {

            String timestamp = DATE_FORMAT.format(new Date());
            pw.println(timestamp + "," + action + "," + details);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
