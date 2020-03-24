package app.reactive.services;

import app.reactive.services.interfaces.ComputersReactiveService;
import db.mongo.ComputerReactiveDao;
import model.mongo.Computer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ComputersReactiveServiceImpl implements ComputersReactiveService {

    private ComputerReactiveDao computerReactiveDao;

    @Autowired
    public ComputersReactiveServiceImpl(ComputerReactiveDao computerReactiveDao) {
        this.computerReactiveDao = computerReactiveDao;
    }

    @Override
    public Flux<Computer> getAllComputers() {
        return computerReactiveDao.findAll();
    }
}
