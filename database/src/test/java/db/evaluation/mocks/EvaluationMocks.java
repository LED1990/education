package db.evaluation.mocks;

import model.evaluation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EvaluationMocks {

    //Evaluation
    private String comment;
    private String CaseNarrative;

    //UndesirableAction
    private String description;

    //Classification
    private String code;
    private String name;

    //Indication
    private String mdeicineName;
    private String info;


    public static List<Evaluation> prepareEvaluationObjectMock() {
        EvaluationSearchCriteria evaluationSearchCriteria = new EvaluationSearchCriteria("comment1", "case narrative1", "description1", "code1", "name1", "medicine1", "info1");
        EvaluationSearchCriteria evaluationSearchCriteria2 = new EvaluationSearchCriteria("comment2", "case narrative2", "description2", "code2", "name2", "medicine2", "info2");
        EvaluationSearchCriteria evaluationSearchCriteria3 = new EvaluationSearchCriteria("comment3", "case narrative3", "description3", "code3", "name3", "medicine3", "info3");

        List<Evaluation> result = new ArrayList<>();
        result.add(EvaluationMocks.prepareObject(evaluationSearchCriteria));
        result.add(EvaluationMocks.prepareObject(evaluationSearchCriteria2));
        result.add(EvaluationMocks.prepareObject(evaluationSearchCriteria3));

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
