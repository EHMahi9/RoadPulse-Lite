package repository;

import java.util.List;
import model.Incident;

public interface IncidentRepository {

    boolean save(Incident incident);

    Incident findById(int incidentId);

    List<Incident> findAll();

    boolean update(Incident incident);

    boolean delete(int incidentId);

}