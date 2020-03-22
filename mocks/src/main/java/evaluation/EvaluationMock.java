package evaluation;

import model.evaluation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EvaluationMock {

    public static List<Evaluation> prepareEvaluationObjectMock() {
        EvaluationSearchCriteria evaluationSearchCriteria = new EvaluationSearchCriteria("every", "case narrative1", "description1", "code1", "name1", "medicine1", "info1", 0, 5);
        EvaluationSearchCriteria evaluationSearchCriteria2 = new EvaluationSearchCriteria("comment", "case narrative2", "description2", "code2", "name2", "medicine2", "info2",0, 5);
        EvaluationSearchCriteria evaluationSearchCriteria3 = new EvaluationSearchCriteria("must", "case narrative3", "description3", "code3", "name3", "medicine3", "info3",0, 5);
        EvaluationSearchCriteria evaluationSearchCriteria4 = new EvaluationSearchCriteria("have", "seems fine", "description3", "code88", "name3", "medicine77", "info11",0, 5);
        EvaluationSearchCriteria evaluationSearchCriteria5 = new EvaluationSearchCriteria("different", "some case", "patient is fine", "code1", "name1", "medicine66", "info1",0, 5);
        EvaluationSearchCriteria evaluationSearchCriteria6 = new EvaluationSearchCriteria("value", "my narrative", "my desc", "code88", "name66", "medicine77", "info11",0, 5);
        EvaluationSearchCriteria evaluationSearchCriteria7 = new EvaluationSearchCriteria("for", "seems fine", "description3", "code11", "name66", "medicine77", "info11",0, 5);
        EvaluationSearchCriteria evaluationSearchCriteria8 = new EvaluationSearchCriteria("me", "all good", "my desc", "code11", "name66", "medicine66", "info88",0, 5);
        EvaluationSearchCriteria evaluationSearchCriteria9 = new EvaluationSearchCriteria("to", "seems fine", "description2", "code33", "name11", "medicine77", "info88",0, 5);
        EvaluationSearchCriteria evaluationSearchCriteria10 = new EvaluationSearchCriteria("be", "case case", "is it working", "code55", "name22", "medicine22", "info22",0, 5);
        EvaluationSearchCriteria evaluationSearchCriteria11 = new EvaluationSearchCriteria("able", "case case", "is it working", "code55", "name22", "medicine44", "info22",0, 5);
        EvaluationSearchCriteria evaluationSearchCriteria12 = new EvaluationSearchCriteria("to", "doing well", "description2", "code55", "name3", "medicine11", "info55",0, 5);
        EvaluationSearchCriteria evaluationSearchCriteria13 = new EvaluationSearchCriteria("check", "doing bad", "patient is fine", "code66", "name55", "medicine33", "info77",0, 5);
        EvaluationSearchCriteria evaluationSearchCriteria14 = new EvaluationSearchCriteria("if", "seems fine", "description3", "code11", "name3", "medicine77", "info11",0, 5);
        EvaluationSearchCriteria evaluationSearchCriteria15 = new EvaluationSearchCriteria("all", "seems fine", "is it working", "code88", "name66", "medicine3", "info1",0, 5);
        EvaluationSearchCriteria evaluationSearchCriteria16 = new EvaluationSearchCriteria("data", "seems fine", "description3", "code11", "name66", "medicine77", "info11",0, 5);
        EvaluationSearchCriteria evaluationSearchCriteria17 = new EvaluationSearchCriteria("is", "not to good", "description3", "code88", "name3", "medicine3", "info1",0, 5);
        EvaluationSearchCriteria evaluationSearchCriteria18 = new EvaluationSearchCriteria("present", "seems fine", "is it working", "code11", "name66", "medicine77", "info11",0, 5);

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
        result.add(EvaluationMock.prepareObject(evaluationSearchCriteria13));
        result.add(EvaluationMock.prepareObject(evaluationSearchCriteria14));
        result.add(EvaluationMock.prepareObject(evaluationSearchCriteria15));
        result.add(EvaluationMock.prepareObject(evaluationSearchCriteria16));
        result.add(EvaluationMock.prepareObject(evaluationSearchCriteria17));
        result.add(EvaluationMock.prepareObject(evaluationSearchCriteria18));

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
