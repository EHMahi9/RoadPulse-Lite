package model;

public class Road {

    private int roadId;
    private String roadName;
    private String district;
    private String status;

    public Road() {

    }

    public Road(int roadId, String roadName, String district, String status) {

        this.roadId = roadId;
        this.roadName = roadName;
        this.district = district;
        this.status = status;

    }

    public int getRoadId() {

        return roadId;

    }

    public void setRoadId(int roadId) {

        this.roadId = roadId;

    }

    public String getRoadName() {

        return roadName;

    }

    public void setRoadName(String roadName) {

        this.roadName = roadName;

    }

    public String getDistrict() {

        return district;

    }

    public void setDistrict(String district) {

        this.district = district;

    }

    public String getStatus() {

        return status;

    }

    public void setStatus(String status) {

        this.status = status;

    }

    @Override
    public String toString() {

        return "Road ID : " + roadId +
                "\nRoad Name : " + roadName +
                "\nDistrict : " + district +
                "\nStatus : " + status;

    }

}