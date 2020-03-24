package db.mongo;

import model.mongo.Computer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputerReactiveDao extends ReactiveMongoRepository<Computer, String> {
}
