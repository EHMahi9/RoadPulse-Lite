package manager;

import java.util.ArrayList;
import model.Road;

public class RoadManager {

    private ArrayList<Road> roads;

    public RoadManager() {

        roads = new ArrayList<>();

    }

    public void addRoad(Road road) {

        roads.add(road);

        System.out.println("Road added successfully.");

    }

    public void showAllRoads() {

        if (roads.isEmpty()) {

            System.out.println("No roads available.");

            return;

        }

        for (Road road : roads) {

            System.out.println("----------------------");
            System.out.println(road);

        }

    }

    public Road findRoadByName(String roadName) {

        for (Road road : roads) {

            if (road.getRoadName().equalsIgnoreCase(roadName)) {

                return road;

            }

        }

        return null;

    }

    public boolean updateRoadStatus(String roadName, String newStatus) {

        Road road = findRoadByName(roadName);

        if (road != null) {

            road.setStatus(newStatus);

            return true;

        }

        return false;

    }

    public boolean deleteRoad(String roadName) {

        Road road = findRoadByName(roadName);

        if (road != null) {

            roads.remove(road);

            return true;

        }

        return false;

    }

}