package app.bootstrap;

import model.evaluation.Evaluation;
import model.evaluation.Indication;
import model.evaluation.UndesirableAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import db.springData.EvaluationDao;

import java.util.HashSet;
import java.util.Set;

@Component
public class EvaluationDataInit implements CommandLineRunner {
    Logger logger = LoggerFactory.getLogger(EvaluationDataInit.class);

    private final EvaluationDao evaluationDao;

    @Autowired
    public EvaluationDataInit(EvaluationDao evaluationDao) {
        this.evaluationDao = evaluationDao;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Bootsrapping evaluation data");
        Evaluation evaluation = new Evaluation();
        evaluation.setCaseNarrative("test case narrative");
        evaluation.setComment("test comment");

        Set<UndesirableAction> undesirableActionSet = new HashSet<>();
        undesirableActionSet.add(new UndesirableAction("destription to action 1", evaluation));
        undesirableActionSet.add(new UndesirableAction("destription to action 2", evaluation));

        Set<Indication> indicationSet = new HashSet<>();
        indicationSet.add(new Indication("medicine 1", "info 1", (UndesirableAction) undesirableActionSet.toArray()[0]));
        indicationSet.add(new Indication("medicine 2", "info 2", (UndesirableAction) undesirableActionSet.toArray()[0]));

        Set<Indication> indicationSetTwo = new HashSet<>();
        indicationSetTwo.add(new Indication("medicine 3", "info 3", (UndesirableAction) undesirableActionSet.toArray()[1]));
        indicationSetTwo.add(new Indication("medicine 4", "info 4", (UndesirableAction) undesirableActionSet.toArray()[1]));

        ((UndesirableAction) undesirableActionSet.toArray()[0]).setIndications(indicationSet);
        ((UndesirableAction) undesirableActionSet.toArray()[1]).setIndications(indicationSetTwo);

        evaluation.setUndesirableActions(undesirableActionSet);

        evaluationDao.save(evaluation);
    }
}
