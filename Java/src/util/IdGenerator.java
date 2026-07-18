package util;

public class IdGenerator {

    private static int userId = 1;
    private static int roadId = 1;
    private static int incidentId = 1;
    private static int alertId = 1;

    private IdGenerator() {
        // Prevent object creation
    }

    public static void initialize(int nextUserId, int nextRoadId,
                                  int nextIncidentId, int nextAlertId) {
        userId = nextUserId;
        roadId = nextRoadId;
        incidentId = nextIncidentId;
        alertId = nextAlertId;
    }

    public static int nextUserId() {
        return userId++;
    }

    public static int nextRoadId() {
        return roadId++;
    }

    public static int nextIncidentId() {
        return incidentId++;
    }

    public static int nextAlertId() {
        return alertId++;
    }

    public static int getCurrentUserId() {
        return userId;
    }

    public static int getCurrentRoadId() {
        return roadId;
    }

    public static int getCurrentIncidentId() {
        return incidentId;
    }

    public static int getCurrentAlertId() {
        return alertId;
    }

}
