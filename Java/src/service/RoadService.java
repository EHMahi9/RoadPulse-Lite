package service;

import java.util.List;
import model.Road;
import repository.RoadRepository;

public class RoadService {

    private final RoadRepository roadRepository;

    public RoadService(RoadRepository roadRepository) {
        this.roadRepository = roadRepository;
    }

    /**
     * Add a new road.
     */
    public boolean addRoad(Road road) {

        if (road == null) {
            return false;
        }

        if (roadRepository.findById(road.getRoadId()) != null) {
            return false;
        }

        return roadRepository.save(road);
    }

    /**
     * Get all roads.
     */
    public List<Road> getAllRoads() {
        return roadRepository.findAll();
    }

    /**
     * Find road by ID.
     */
    public Road findRoadById(int roadId) {
        return roadRepository.findById(roadId);
    }

    /**
     * Update road.
     */
    public boolean updateRoad(Road road) {

        if (road == null) {
            return false;
        }

        return roadRepository.update(road);
    }

    /**
     * Delete road.
     */
    public boolean deleteRoad(int roadId) {
        return roadRepository.delete(roadId);
    }

    /**
     * Update only the road status.
     */
    public boolean updateRoadStatus(int roadId, String newStatus) {

        Road road = roadRepository.findById(roadId);

        if (road == null) {
            return false;
        }

        road.setStatus(newStatus);

        return roadRepository.update(road);
    }

}