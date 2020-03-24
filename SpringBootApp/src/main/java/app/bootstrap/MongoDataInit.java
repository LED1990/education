package app.bootstrap;

import db.mongo.ComputerDao;
import lombok.extern.slf4j.Slf4j;
import mongo.ComputerMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MongoDataInit implements CommandLineRunner{

    private ComputerDao computerDao;

    @Autowired
    public MongoDataInit(ComputerDao computerDao) {
        this.computerDao = computerDao;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Bootstrapping mongo data");

        computerDao.saveAll(ComputerMock.mockComputers());

        computerDao.findAll();

    }
}
