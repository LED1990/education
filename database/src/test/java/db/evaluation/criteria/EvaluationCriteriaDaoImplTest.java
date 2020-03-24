package db.evaluation.criteria;

import db.evaluation.config.DbTestConfig;
import db.jpa.evaluation.springData.EvaluationDao;
import jpa.EvaluationMock;
import model.evaluation.Evaluation;
import model.evaluation.EvaluationResults;
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
import java.util.Optional;
import java.util.Set;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DbTestConfig.class, loader = AnnotationConfigContextLoader.class)
@DataJpaTest//this needs spring configuration in package or package above!
class EvaluationCriteriaDaoImplTest {

    @Resource //vs @Autowired?
    private EvaluationDao evaluationDao;

    @Test
    void getAllEntitiesShouldReturnAllTest() {
        //given
        for (Evaluation obj: EvaluationMock.prepareEvaluationObjectMock()
             ) {
            evaluationDao.save(obj);
        }
        //when
        Optional<List<Evaluation>> result = evaluationDao.getAllEntities();
        //than
        Assert.assertTrue(result.isPresent());
        Assert.assertTrue(result.get().size() == 18);
    }

    @Test
    void getAllEntitiesShouldReturnDistinctTest() {
        //given
        for (Evaluation obj : EvaluationMock.prepareEvaluationObjectMock()
                ) {
            evaluationDao.save(obj);
        }
//        EvaluationSearchCriteria evaluationSearchCriteria = new EvaluationSearchCriteria("comment1", "case narrative2", "description2", "code1", "name2", "medicine1", "info2", 0 ,5);
//        EvaluationSearchCriteria evaluationSearchCriteria = new EvaluationSearchCriteria(null, null, null, null, null, null, null, 0 ,5);
        EvaluationSearchCriteria evaluationSearchCriteria = new EvaluationSearchCriteria(null, null, null, null, null, null, null, 0 ,5);

        //when
        Optional<EvaluationResults> result = evaluationDao.getByCriteria(evaluationSearchCriteria);
        //than
        Assert.assertTrue(result.isPresent());
        Assert.assertTrue(result.get().getResults() != null);
        Assert.assertTrue(!result.get().getResults().isEmpty());
        Set<Evaluation> resultSet = new HashSet<>(result.get().getResults());
        Assert.assertTrue(resultSet.size() == result.get().getResults().size());
    }
}