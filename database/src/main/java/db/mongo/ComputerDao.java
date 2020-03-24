package db.mongo;

import model.mongo.Computer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputerDao extends CrudRepository<Computer, String> {
}
