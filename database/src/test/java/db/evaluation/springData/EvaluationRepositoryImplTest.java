package db.evaluation.springData;

import db.evaluation.config.DbTestConfig;
import evaluation.EvaluationMock;
import model.evaluation.Evaluation;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.annotation.Resource;
import java.util.Optional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DbTestConfig.class, loader = AnnotationConfigContextLoader.class)
@DataJpaTest//this needs spring configuration in package or package above!
public class EvaluationRepositoryImplTest {

    private static final String UPDATED_DESCRIPTION = "Updated description";
    private static final String UPDATED_NAME = "Updated name";
    private static final String UPDATED_MEDICINE_NAME = "Updated medicine name";

    @Resource
    private EvaluationDao evaluationDao;

    @Test
    public void updateEvaluation() throws Exception {
        //given
        Evaluation evaluation = EvaluationMock.prepareSingleObject();
        evaluationDao.save(evaluation);

        evaluation.getUndesirableActions().removeIf(undesirableAction -> undesirableAction.getId().equals(1L));
        evaluation.getUndesirableActions().forEach(undesirableAction -> {
            undesirableAction.setDescription(UPDATED_DESCRIPTION);
            undesirableAction.getClassifications().forEach(classification -> classification.setName(UPDATED_NAME));
            undesirableAction.getIndications().forEach(indication -> indication.setMedicineName(UPDATED_MEDICINE_NAME));
        });

        //when
        Long id = evaluationDao.updateEvaluation(evaluation);
        Optional<Evaluation> result = evaluationDao.findById(id);

        //then
        Assert.assertTrue(result.isPresent());
        Assert.assertTrue(result.get().getUndesirableActions().size() == 1);
        Assert.assertTrue(result.get().getUndesirableActions().stream().anyMatch(undesirableAction -> undesirableAction.getDescription().equals(UPDATED_DESCRIPTION)));
        Assert.assertTrue(result.get().getUndesirableActions().stream().anyMatch(undesirableAction -> undesirableAction.getClassifications().stream().anyMatch(classification -> classification.getName().equals(UPDATED_NAME))));
        Assert.assertTrue(result.get().getUndesirableActions().stream().anyMatch(undesirableAction -> undesirableAction.getIndications().stream().anyMatch(indication -> indication.getMedicineName().equals(UPDATED_MEDICINE_NAME))));

    }

}