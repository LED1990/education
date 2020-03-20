package app.bootstrap;

import db.evaluation.springData.EvaluationDao;
import evaluation.EvaluationMock;
import model.evaluation.Evaluation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Class just for test to initialize h2 db with some startup data
 */
@Component
public class EvaluationDataInit implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(EvaluationDataInit.class);

    private final EvaluationDao evaluationDao;

    @Autowired
    public EvaluationDataInit(EvaluationDao evaluationDao) {
        this.evaluationDao = evaluationDao;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        logger.info("Bootstrapping evaluation data");

        List<Evaluation> mocks = EvaluationMock.prepareEvaluationObjectMock();
        for (Evaluation eval: mocks
             ) {
            evaluationDao.save(eval);
        }

    }


}
