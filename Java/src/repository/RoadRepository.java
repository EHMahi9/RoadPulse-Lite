package repository;

import java.util.List;
import model.Road;

public interface RoadRepository {

    boolean save(Road road);

    Road findById(int roadId);

    List<Road> findAll();

    boolean update(Road road);

    boolean delete(int roadId);

}