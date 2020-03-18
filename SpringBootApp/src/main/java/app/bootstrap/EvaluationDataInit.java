package app.bootstrap;

import evaluations.Evaluation;
import evaluations.UndesirableAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springData.EvaluationDao;

import java.util.HashSet;
import java.util.Set;

@Component
public class EvaluationDataInit implements CommandLineRunner {

    private final EvaluationDao evaluationDao;

    @Autowired
    public EvaluationDataInit(EvaluationDao evaluationDao) {
        this.evaluationDao = evaluationDao;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Bootsrapping evaluation data");
        Evaluation evaluation = new Evaluation();
        evaluation.setCaseNarrative("test case narrative");
        evaluation.setComment("test comment");

        Set<UndesirableAction> undesirableActionSet = new HashSet<>();
        undesirableActionSet.add(new UndesirableAction("destription to action 1"));
        undesirableActionSet.add(new UndesirableAction("destription to action 2"));

        evaluation.setUndesirableActions(undesirableActionSet);

        evaluationDao.save(evaluation);
    }
}
