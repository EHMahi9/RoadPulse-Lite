package util;

import model.Admin;
import model.Commuter;
import model.Incident;
import model.PublicAlert;
import model.Road;
import model.User;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles saving and loading application data to/from text files.
 * Data is stored as CSV in the data/ directory.
 */
public class DataFileManager {

    private static final Path DATA_DIR = Paths.get("data");

    private static final Path USERS_FILE = DATA_DIR.resolve("users.csv");
    private static final Path ROADS_FILE = DATA_DIR.resolve("roads.csv");
    private static final Path INCIDENTS_FILE = DATA_DIR.resolve("incidents.csv");
    private static final Path ALERTS_FILE = DATA_DIR.resolve("alerts.csv");
    private static final Path ID_FILE = DATA_DIR.resolve("id_gen.txt");

    private DataFileManager() {
    }

    // ========== Initialization ==========

    public static void ensureDataDir() throws IOException {
        Files.createDirectories(DATA_DIR);
    }

    public static boolean dataExists() {
        return Files.exists(USERS_FILE);
    }

    // ========== ID Generator Persistence ==========

    public static void saveIdState(int nextUserId, int nextRoadId,
                                   int nextIncidentId, int nextAlertId) throws IOException {
        String content = nextUserId + "," + nextRoadId + ","
                + nextIncidentId + "," + nextAlertId;
        Files.writeString(ID_FILE, content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public static int[] loadIdState() throws IOException {
        if (!Files.exists(ID_FILE)) {
            return new int[]{1, 1, 1, 1};
        }
        String content = Files.readString(ID_FILE).trim();
        String[] parts = content.split(",");
        return new int[]{
                Integer.parseInt(parts[0]),
                Integer.parseInt(parts[1]),
                Integer.parseInt(parts[2]),
                Integer.parseInt(parts[3])
        };
    }

    // ========== Users ==========

    public static void saveUsers(List<User> users) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("userId,name,email,password,type,adminLevel,trustScore");

        for (User u : users) {
            StringBuilder sb = new StringBuilder();
            sb.append(u.getUserId()).append(",");
            sb.append(escapeCsv(u.getName())).append(",");
            sb.append(escapeCsv(u.getEmail())).append(",");
            sb.append(escapeCsv(u.getPassword())).append(",");

            if (u instanceof Admin) {
                Admin a = (Admin) u;
                sb.append("admin,").append(a.getAdminLevel()).append(",0");
            } else if (u instanceof Commuter) {
                Commuter c = (Commuter) u;
                sb.append("commuter,0,").append(c.getTrustScore());
            } else {
                sb.append("user,0,0");
            }

            lines.add(sb.toString());
        }

        Files.write(USERS_FILE, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public static List<User> loadUsers() throws IOException {
        List<User> users = new ArrayList<>();

        if (!Files.exists(USERS_FILE)) {
            return users;
        }

        List<String> lines = Files.readAllLines(USERS_FILE);

        for (int i = 1; i < lines.size(); i++) {

            String line = lines.get(i).trim();
            if (line.isEmpty()) continue;

            String[] parts = parseCsvLine(line);
            if (parts.length < 6) continue;

            int id = Integer.parseInt(parts[0]);
            String name = parts[1];
            String email = parts[2];
            String password = parts[3];
            String type = parts[4];

            if ("admin".equals(type)) {
                int level = Integer.parseInt(parts[5]);
                users.add(new Admin(id, name, email, password, level));
            } else if ("commuter".equals(type)) {
                int score = Integer.parseInt(parts[6]);
                users.add(new Commuter(id, name, email, password, score));
            }

        }

        return users;
    }

    // ========== Roads ==========

    public static void saveRoads(List<Road> roads) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("roadId,roadName,district,status");

        for (Road r : roads) {
            lines.add(r.getRoadId() + ","
                    + escapeCsv(r.getRoadName()) + ","
                    + escapeCsv(r.getDistrict()) + ","
                    + escapeCsv(r.getStatus()));
        }

        Files.write(ROADS_FILE, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public static List<Road> loadRoads() throws IOException {
        List<Road> roads = new ArrayList<>();

        if (!Files.exists(ROADS_FILE)) {
            return roads;
        }

        List<String> lines = Files.readAllLines(ROADS_FILE);

        for (int i = 1; i < lines.size(); i++) {

            String line = lines.get(i).trim();
            if (line.isEmpty()) continue;

            String[] parts = parseCsvLine(line);
            if (parts.length < 4) continue;

            roads.add(new Road(
                    Integer.parseInt(parts[0]),
                    parts[1], parts[2], parts[3]
            ));

        }

        return roads;
    }

    // ========== Incidents ==========

    public static void saveIncidents(List<Incident> incidents) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("incidentId,type,description,reportTime,status,roadId,reporterId");

        for (Incident i : incidents) {
            lines.add(i.getIncidentId() + ","
                    + escapeCsv(i.getIncidentType()) + ","
                    + escapeCsv(i.getDescription()) + ","
                    + escapeCsv(i.getReportTime()) + ","
                    + escapeCsv(i.getStatus()) + ","
                    + i.getRoad().getRoadId() + ","
                    + i.getReporter().getUserId());
        }

        Files.write(INCIDENTS_FILE, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public static List<String[]> loadIncidentRows() throws IOException {
        List<String[]> rows = new ArrayList<>();

        if (!Files.exists(INCIDENTS_FILE)) {
            return rows;
        }

        List<String> lines = Files.readAllLines(INCIDENTS_FILE);

        for (int i = 1; i < lines.size(); i++) {

            String line = lines.get(i).trim();
            if (line.isEmpty()) continue;

            rows.add(parseCsvLine(line));

        }

        return rows;
    }

    // ========== Alerts ==========

    public static void saveAlerts(List<PublicAlert> alerts) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("alertId,title,message,publishDate,roadId");

        for (PublicAlert a : alerts) {
            lines.add(a.getAlertId() + ","
                    + escapeCsv(a.getTitle()) + ","
                    + escapeCsv(a.getMessage()) + ","
                    + escapeCsv(a.getPublishDate()) + ","
                    + a.getRoad().getRoadId());
        }

        Files.write(ALERTS_FILE, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public static List<String[]> loadAlertRows() throws IOException {
        List<String[]> rows = new ArrayList<>();

        if (!Files.exists(ALERTS_FILE)) {
            return rows;
        }

        List<String> lines = Files.readAllLines(ALERTS_FILE);

        for (int i = 1; i < lines.size(); i++) {

            String line = lines.get(i).trim();
            if (line.isEmpty()) continue;

            rows.add(parseCsvLine(line));

        }

        return rows;
    }

    // ========== CSV Helpers ==========

    private static String escapeCsv(String value) {
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }

    private static String[] parseCsvLine(String line) {
        List<String> fields = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '"') {
                if (inQuotes && i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    current.append('"');
                    i++;
                } else {
                    inQuotes = !inQuotes;
                }
            } else if (c == ',' && !inQuotes) {
                fields.add(current.toString().trim());
                current = new StringBuilder();
            } else {
                current.append(c);
            }
        }

        fields.add(current.toString().trim());
        return fields.toArray(new String[0]);
    }

}
