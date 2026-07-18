package model;

public class Incident {

    private int incidentId;
    private String incidentType;
    private String description;
    private String reportTime;
    private String status;

    private Road road;
    private Commuter reporter;

    public Incident() {

    }

    public Incident(int incidentId,
                    String incidentType,
                    String description,
                    String reportTime,
                    String status,
                    Road road,
                    Commuter reporter) {

        this.incidentId = incidentId;
        this.incidentType = incidentType;
        this.description = description;
        this.reportTime = reportTime;
        this.status = status;
        this.road = road;
        this.reporter = reporter;

    }

    public int getIncidentId() {

        return incidentId;

    }

    public void setIncidentId(int incidentId) {

        this.incidentId = incidentId;

    }

    public String getIncidentType() {

        return incidentType;

    }

    public void setIncidentType(String incidentType) {

        this.incidentType = incidentType;

    }

    public String getDescription() {

        return description;

    }

    public void setDescription(String description) {

        this.description = description;

    }

    public String getReportTime() {

        return reportTime;

    }

    public void setReportTime(String reportTime) {

        this.reportTime = reportTime;

    }

    public String getStatus() {

        return status;

    }

    public void setStatus(String status) {

        this.status = status;

    }

    public Road getRoad() {

        return road;

    }

    public void setRoad(Road road) {

        this.road = road;

    }

    public Commuter getReporter() {

        return reporter;

    }

    public void setReporter(Commuter reporter) {

        this.reporter = reporter;

    }

    @Override
    public String toString() {

        return "Incident ID : " + incidentId +
                "\nType : " + incidentType +
                "\nDescription : " + description +
                "\nReport Time : " + reportTime +
                "\nStatus : " + status +
                "\nRoad : " + road.getRoadName() +
                "\nReported By : " + reporter.getName();

    }

}