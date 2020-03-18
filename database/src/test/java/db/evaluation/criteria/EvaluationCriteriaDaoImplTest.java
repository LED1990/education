package db.evaluation.criteria;

import db.evaluation.config.DbTestConfig;
import db.evaluation.mocks.EvaluationMocks;
import db.evaluation.springData.EvaluationDao;
import model.evaluation.Evaluation;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DbTestConfig.class, loader = AnnotationConfigContextLoader.class)
@DataJpaTest //this needs spring configuration in package or package above!
class EvaluationCriteriaDaoImplTest {

    @Resource
    private EvaluationDao evaluationDao;

    @Test
    void getAllEntitiesShouldReturnAll(){
        evaluationDao.save(EvaluationMocks.prepareEvaluationObjectMock());
        List<Evaluation> result = evaluationDao.getAllEntities();
        Assert.assertTrue(!result.isEmpty());
    }
}