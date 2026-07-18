package repository.memory;

import model.Road;
import repository.RoadRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRoadRepository implements RoadRepository {

    private final List<Road> roads;

    public InMemoryRoadRepository() {
        roads = new ArrayList<>();
    }

    @Override
    public boolean save(Road road) {
        return roads.add(road);
    }

    @Override
    public Road findById(int roadId) {

        for (Road road : roads) {

            if (road.getRoadId() == roadId) {
                return road;
            }

        }

        return null;
    }

    @Override
    public List<Road> findAll() {
        return roads;
    }

    @Override
    public boolean update(Road updatedRoad) {

        for (int i = 0; i < roads.size(); i++) {

            if (roads.get(i).getRoadId() == updatedRoad.getRoadId()) {

                roads.set(i, updatedRoad);
                return true;

            }

        }

        return false;
    }

    @Override
    public boolean delete(int roadId) {

        Road road = findById(roadId);

        if (road != null) {

            roads.remove(road);
            return true;

        }

        return false;
    }
}