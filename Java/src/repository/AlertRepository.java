package repository;

import java.util.List;
import model.PublicAlert;

public interface AlertRepository {

    boolean save(PublicAlert alert);

    PublicAlert findById(int alertId);

    List<PublicAlert> findAll();

    boolean update(PublicAlert alert);

    boolean delete(int alertId);

}