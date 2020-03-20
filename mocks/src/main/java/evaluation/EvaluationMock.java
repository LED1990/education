package evaluation;

import model.evaluation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EvaluationMock {

    public static List<Evaluation> prepareEvaluationObjectMock() {
        EvaluationSearchCriteria evaluationSearchCriteria = new EvaluationSearchCriteria("comment1", "case narrative1", "description1", "code1", "name1", "medicine1", "info1");
        EvaluationSearchCriteria evaluationSearchCriteria2 = new EvaluationSearchCriteria("comment2", "case narrative2", "description2", "code2", "name2", "medicine2", "info2");
        EvaluationSearchCriteria evaluationSearchCriteria3 = new EvaluationSearchCriteria("comment3", "case narrative3", "description3", "code3", "name3", "medicine3", "info3");
        EvaluationSearchCriteria evaluationSearchCriteria4 = new EvaluationSearchCriteria("works fine", "some case", "patient is fine", "code1", "name1", "medicine66", "info1");
        EvaluationSearchCriteria evaluationSearchCriteria5 = new EvaluationSearchCriteria("works fine", "my narrative", "my desc", "code88", "name66", "medicine77", "info11");
        EvaluationSearchCriteria evaluationSearchCriteria6 = new EvaluationSearchCriteria("hitman", "seems fine", "description3", "code11", "name66", "medicine77", "info11");
        EvaluationSearchCriteria evaluationSearchCriteria7 = new EvaluationSearchCriteria("hitman", "all good", "my desc", "code11", "name66", "medicine66", "info88");
        EvaluationSearchCriteria evaluationSearchCriteria8 = new EvaluationSearchCriteria("works", "seems fine", "description2", "code33", "name11", "medicine77", "info88");
        EvaluationSearchCriteria evaluationSearchCriteria9 = new EvaluationSearchCriteria("going good", "case case", "is it working", "code55", "name22", "medicine22", "info22");
        EvaluationSearchCriteria evaluationSearchCriteria10 = new EvaluationSearchCriteria("going well", "case case", "is it working", "code55", "name22", "medicine44", "info22");
        EvaluationSearchCriteria evaluationSearchCriteria11 = new EvaluationSearchCriteria("stopped", "doing well", "description2", "code55", "name44", "medicine11", "info55");
        EvaluationSearchCriteria evaluationSearchCriteria12 = new EvaluationSearchCriteria("stopped", "doing bad", "patient is fine", "code66", "name55", "medicine33", "info77");

        List<Evaluation> result = new ArrayList<>();
        result.add(EvaluationMock.prepareObject(evaluationSearchCriteria));
        result.add(EvaluationMock.prepareObject(evaluationSearchCriteria2));
        result.add(EvaluationMock.prepareObject(evaluationSearchCriteria3));
        result.add(EvaluationMock.prepareObject(evaluationSearchCriteria4));
        result.add(EvaluationMock.prepareObject(evaluationSearchCriteria5));
        result.add(EvaluationMock.prepareObject(evaluationSearchCriteria6));
        result.add(EvaluationMock.prepareObject(evaluationSearchCriteria7));
        result.add(EvaluationMock.prepareObject(evaluationSearchCriteria8));
        result.add(EvaluationMock.prepareObject(evaluationSearchCriteria9));
        result.add(EvaluationMock.prepareObject(evaluationSearchCriteria10));
        result.add(EvaluationMock.prepareObject(evaluationSearchCriteria11));
        result.add(EvaluationMock.prepareObject(evaluationSearchCriteria12));

        return result;
    }

    public static Evaluation prepareObject(EvaluationSearchCriteria evaluationSearchCriteria) {
        Evaluation evaluation = new Evaluation();
        evaluation.setCaseNarrative(evaluationSearchCriteria.getCaseNarrative());
        evaluation.setComment(evaluationSearchCriteria.getComment());

        Set<UndesirableAction> undesirableActionSet = new HashSet<>();
        undesirableActionSet.add(new UndesirableAction(evaluationSearchCriteria.getDescription(), evaluation));
        undesirableActionSet.add(new UndesirableAction(evaluationSearchCriteria.getDescription(), evaluation));

        Set<Indication> indicationSet = new HashSet<>();
        indicationSet.add(new Indication(evaluationSearchCriteria.getMedicineName(),
                evaluationSearchCriteria.getInfo(),
                (UndesirableAction) undesirableActionSet.toArray()[0]));
        indicationSet.add(new Indication(evaluationSearchCriteria.getMedicineName(),
                evaluationSearchCriteria.getInfo(),
                (UndesirableAction) undesirableActionSet.toArray()[0]));

        Set<Indication> indicationSetTwo = new HashSet<>();
        indicationSetTwo.add(new Indication(evaluationSearchCriteria.getMedicineName(),
                evaluationSearchCriteria.getInfo(),
                (UndesirableAction) undesirableActionSet.toArray()[1]));
        indicationSetTwo.add(new Indication(evaluationSearchCriteria.getMedicineName(),
                evaluationSearchCriteria.getInfo(),
                (UndesirableAction) undesirableActionSet.toArray()[1]));

        ((UndesirableAction) undesirableActionSet.toArray()[0]).setIndications(indicationSet);
        ((UndesirableAction) undesirableActionSet.toArray()[1]).setIndications(indicationSetTwo);

        Set<Classification> classificationSet = new HashSet<>();
        classificationSet.add(new Classification(evaluationSearchCriteria.getCode(),
                evaluationSearchCriteria.getName(),
                (UndesirableAction) undesirableActionSet.toArray()[0]));
        classificationSet.add(new Classification(evaluationSearchCriteria.getCode(),
                evaluationSearchCriteria.getName(),
                (UndesirableAction) undesirableActionSet.toArray()[0]));

        Set<Classification> classificationSetTwo = new HashSet<>();
        classificationSetTwo.add(new Classification(evaluationSearchCriteria.getCode(),
                evaluationSearchCriteria.getName(),
                (UndesirableAction) undesirableActionSet.toArray()[1]));
        classificationSetTwo.add(new Classification(evaluationSearchCriteria.getCode(),
                evaluationSearchCriteria.getName(),
                (UndesirableAction) undesirableActionSet.toArray()[1]));

        ((UndesirableAction) undesirableActionSet.toArray()[0]).setClassifications(classificationSet);
        ((UndesirableAction) undesirableActionSet.toArray()[1]).setClassifications(classificationSetTwo);

        evaluation.setUndesirableActions(undesirableActionSet);

        return evaluation;
    }
}
