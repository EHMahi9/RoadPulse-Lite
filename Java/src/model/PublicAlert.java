package model;

public class PublicAlert {

    private int alertId;
    private String title;
    private String message;
    private String publishDate;

    private Road road;

    public PublicAlert() {

    }

    public PublicAlert(int alertId,
                       String title,
                       String message,
                       String publishDate,
                       Road road) {

        this.alertId = alertId;
        this.title = title;
        this.message = message;
        this.publishDate = publishDate;
        this.road = road;

    }

    public int getAlertId() {

        return alertId;

    }

    public void setAlertId(int alertId) {

        this.alertId = alertId;

    }

    public String getTitle() {

        return title;

    }

    public void setTitle(String title) {

        this.title = title;

    }

    public String getMessage() {

        return message;

    }

    public void setMessage(String message) {

        this.message = message;

    }

    public String getPublishDate() {

        return publishDate;

    }

    public void setPublishDate(String publishDate) {

        this.publishDate = publishDate;

    }

    public Road getRoad() {

        return road;

    }

    public void setRoad(Road road) {

        this.road = road;

    }

    @Override
    public String toString() {

        return "Alert ID : " + alertId +
                "\nTitle : " + title +
                "\nMessage : " + message +
                "\nPublish Date : " + publishDate +
                "\nRoad : " + road.getRoadName();

    }

}