package app.reactive.services.interfaces;

import model.mongo.Computer;
import reactor.core.publisher.Flux;

public interface ComputersReactiveService {

    Flux<Computer> getAllComputers();
}
