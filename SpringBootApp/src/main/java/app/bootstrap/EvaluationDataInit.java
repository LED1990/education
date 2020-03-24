package app.bootstrap;

import db.jpa.evaluation.springData.EvaluationDao;
import jpa.EvaluationMock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class just for test to initialize h2 db with some startup data
 */
@Slf4j
@Component
public class EvaluationDataInit implements CommandLineRunner {

    private final EvaluationDao evaluationDao;

    @Autowired
    public EvaluationDataInit(EvaluationDao evaluationDao) {
        this.evaluationDao = evaluationDao;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        log.info("Bootstrapping jpa data");
        evaluationDao.saveAll(EvaluationMock.prepareEvaluationObjectMock());
    }


}
