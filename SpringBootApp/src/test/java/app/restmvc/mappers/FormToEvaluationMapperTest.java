package app.restmvc.mappers;

import model.evaluation.Evaluation;
import model.evaluation.forms.ClassificationForm;
import model.evaluation.forms.EvaluationForm;
import model.evaluation.forms.IndicationForm;
import model.evaluation.forms.UndesirableActionForm;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import static app.restmvc.mappers.EvaluationToFormMapperTest.*;

public class FormToEvaluationMapperTest {

    private FormToEvaluationMapper formToEvaluationMapper = FormToEvaluationMapper.INSTANCE;

    @Test
    public void formToEvaluation() throws Exception {
        //given
        ClassificationForm classificationForm = new ClassificationForm();
        classificationForm.setId(ID);
        classificationForm.setName(CLASS_NAME);

        IndicationForm indicationFormn = new IndicationForm();
        indicationFormn.setMedicineName(MED);
        indicationFormn.setId(ID);

        UndesirableActionForm undesirableAction = new UndesirableActionForm();
        undesirableAction.setDescription(DESCRIPTION);
        undesirableAction.setId(ID);
        undesirableAction.setClassifications(new ArrayList<>(Collections.singletonList(classificationForm)));
        undesirableAction.setIndications(new ArrayList<>(Collections.singletonList(indicationFormn)));

        EvaluationForm evaluation = new EvaluationForm();
        evaluation.setId(ID);
        evaluation.setCaseNarrative(CASE_NARRATIVE);
        evaluation.setComment(COMMENT);
        evaluation.setUndesirableActions(new ArrayList<>(Collections.singletonList(undesirableAction)));

        //when
        Evaluation result = formToEvaluationMapper.formToEvaluation(evaluation);

        //then
        Assert.assertTrue(result.getId() == ID);
        Assert.assertTrue(Objects.equals(result.getCaseNarrative(), CASE_NARRATIVE));
        Assert.assertTrue(Objects.equals(result.getComment(), COMMENT));
        Assert.assertTrue(result.getUndesirableActions() != null && !result.getUndesirableActions().isEmpty());
        Assert.assertTrue(result.getUndesirableActions().stream().anyMatch(action -> action.getId() == ID));
        Assert.assertTrue(result.getUndesirableActions().stream().anyMatch(action -> action.getDescription().equals(DESCRIPTION)));
        Assert.assertTrue(result.getUndesirableActions().stream().anyMatch(action -> action.getClassifications().stream().anyMatch(cl -> cl.getId() == ID)));
        Assert.assertTrue(result.getUndesirableActions().stream().anyMatch(action -> action.getClassifications().stream().anyMatch(cl -> cl.getName().equals(CLASS_NAME))));
        Assert.assertTrue(result.getUndesirableActions().stream().anyMatch(action -> action.getIndications().stream().anyMatch(ind -> ind.getMedicineName().equals(MED))));
        Assert.assertTrue(result.getUndesirableActions().stream().anyMatch(action -> action.getIndications().stream().anyMatch(ind -> ind.getId().equals(ID))));
    }

}