import java.util.Date;

class User {
    private int userId;
    private String name;
    private String email;
    private String password;

    public User(int userId, String name, String email, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public boolean login() {
        System.out.println(name + " has logged in successfully.");
        return true;
    }

    public void logout() {
        System.out.println(name + " has logged out.");
    }

    public String getName() {
        return name;
    }
}

class Commuter extends User {
    private int trustScore;

    public Commuter(int userId, String name, String email, String password, int trustScore) {
        super(userId, name, email, password);
        this.trustScore = trustScore;
    }

    public void submitIncident(String type, float latitude, float longitude) {
        System.out.println(getName() + " submitted a new incident: " + type);
    }

    public void upvoteIncident(int incidentId) {
        System.out.println(getName() + " upvoted incident ID: " + incidentId);
    }

    public void viewMap() {
        System.out.println(getName() + " is viewing the live traffic map.");
    }
}

class Admin extends User {
    private int adminLevel;

    public Admin(int userId, String name, String email, String password, int adminLevel) {
        super(userId, name, email, password);
        this.adminLevel = adminLevel;
    }

    public void verifyReport(int incidentId, String status) {
        System.out.println("Admin " + getName() + " marked incident " + incidentId + " as " + status);
    }

    public void publishAlert(String title, String message) {
        System.out.println("Public Alert Published: " + title);
    }

    public void manageUsers() {
        System.out.println("Admin " + getName() + " is managing user accounts.");
    }
}

class Incident {
    private int incidentId;
    private String type;
    private float latitude;
    private float longitude;
    private String status;
    private Date createdAt;

    public Incident(int incidentId, String type, float latitude, float longitude) {
        this.incidentId = incidentId;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = "Pending";
        this.createdAt = new Date();
    }

    public boolean saveReport() {
        System.out.println("Incident " + incidentId + " saved to database.");
        return true;
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
        System.out.println("Incident " + incidentId + " status updated to " + this.status);
    }

    public void autoExpire() {
        this.status = "Expired";
        System.out.println("Incident " + incidentId + " has automatically expired.");
    }
}

class PublicAlert {
    private int alertId;
    private String title;
    private String message;
    private Date validUntil;

    public PublicAlert(int alertId, String title, String message, Date validUntil) {
        this.alertId = alertId;
        this.title = title;
        this.message = message;
        this.validUntil = validUntil;
    }

    public void broadcastAlert() {
        System.out.println("Broadcasting Alert: " + title + " - " + message);
    }

    public void deleteAlert() {
        System.out.println("Alert " + alertId + " has been deleted.");
    }
}

public class RoadPulseTest {
    public static void main(String[] args) {
        User user = new User(1, "Alice", "alice@example.com", "password123");
        user.login();
        user.logout();

        Commuter commuter = new Commuter(2, "Bob", "bob@example.com", "securePass", 80);
        commuter.login();
        commuter.submitIncident("Pothole", 24.8607f, 67.0011f);
        commuter.upvoteIncident(101);
        commuter.viewMap();
        commuter.logout();

        Admin admin = new Admin(3, "Carol", "carol@example.com", "adminPass", 5);
        admin.login();
        admin.verifyReport(101, "Verified");
        admin.publishAlert("Road Closed", "Main Street is closed due to maintenance.");
        admin.manageUsers();
        admin.logout();

        Incident incident = new Incident(101, "Pothole", 24.8607f, 67.0011f);
        incident.saveReport();
        incident.updateStatus("In Progress");
        incident.autoExpire();

        PublicAlert alert = new PublicAlert(1, "Flood Warning", "Heavy rain expected in the area.", new Date());
        alert.broadcastAlert();
        alert.deleteAlert();
    }
}