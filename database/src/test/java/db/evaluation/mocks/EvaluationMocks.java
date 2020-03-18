package db.evaluation.mocks;

import model.evaluation.Classification;
import model.evaluation.Evaluation;
import model.evaluation.Indication;
import model.evaluation.UndesirableAction;

import java.util.HashSet;
import java.util.Set;

public class EvaluationMocks {

    public static Evaluation prepareEvaluationObjectMock(){
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

        Set<Classification> classificationSet = new HashSet<>();
        classificationSet.add(new Classification("code 1", "name 1", (UndesirableAction) undesirableActionSet.toArray()[0]));
        classificationSet.add(new Classification("code 2", "name 2", (UndesirableAction) undesirableActionSet.toArray()[0]));

        Set<Classification> classificationSetTwo = new HashSet<>();
        classificationSetTwo.add(new Classification("code 3", "name 3", (UndesirableAction) undesirableActionSet.toArray()[1]));
        classificationSetTwo.add(new Classification("code 4", "name 4", (UndesirableAction) undesirableActionSet.toArray()[1]));

        ((UndesirableAction) undesirableActionSet.toArray()[0]).setClassifications(classificationSet);
        ((UndesirableAction) undesirableActionSet.toArray()[1]).setClassifications(classificationSetTwo);

        evaluation.setUndesirableActions(undesirableActionSet);

        return evaluation;
    }
}
