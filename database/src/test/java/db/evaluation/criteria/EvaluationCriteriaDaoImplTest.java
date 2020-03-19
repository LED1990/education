package db.evaluation.criteria;

import db.evaluation.config.DbTestConfig;
import db.evaluation.mocks.EvaluationMocks;
import db.evaluation.springData.EvaluationDao;
import model.evaluation.Evaluation;
import model.evaluation.EvaluationSearchCriteria;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DbTestConfig.class, loader = AnnotationConfigContextLoader.class)
@DataJpaTest
        //this needs spring configuration in package or package above!
class EvaluationCriteriaDaoImplTest {

    @Resource
    private EvaluationDao evaluationDao;

    @Test
    void getAllEntitiesShouldReturnAll() {
        EvaluationSearchCriteria evaluationSearchCriteria = new EvaluationSearchCriteria("comment", "case narrative", "description", "code", "name", "medicine", "info");
        evaluationDao.save(EvaluationMocks.prepareObject(evaluationSearchCriteria));
        List<Evaluation> result = evaluationDao.getAllEntities();
        Assert.assertTrue(!result.isEmpty());
    }

    @Test
    void getAllEntitiesShouldReturnDistinct() {
        for (Evaluation obj : EvaluationMocks.prepareEvaluationObjectMock()
                ) {
            evaluationDao.save(obj);
        }

        EvaluationSearchCriteria evaluationSearchCriteria = new EvaluationSearchCriteria("comment hitman", null, null, "code changed", null, null, null);

        List<Evaluation> result = evaluationDao.getByCriteria(evaluationSearchCriteria);
        Set<Evaluation> resultSet = new HashSet<>(result);
        Assert.assertTrue(resultSet.size() == result.size());
        Assert.assertTrue(!result.isEmpty());
    }
}