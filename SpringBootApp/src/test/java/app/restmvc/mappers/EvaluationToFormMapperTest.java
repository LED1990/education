package app.restmvc.mappers;

import model.evaluation.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;

public class EvaluationToFormMapperTest {


    static final long ID = 1L;
    static final String CLASS_NAME = "name";
    static final String MED = "med";
    static final String DESCRIPTION = "description";
    static final String CASE_NARRATIVE = "Case narrative";
    static final String COMMENT = "comment";

    private EvaluationToFormMapper evaluationToFormMapper = EvaluationToFormMapper.INSTANCE;

    @Test
    public void evaluationToForm() throws Exception {
        //given
        Classification classification = new Classification();
        classification.setId(ID);
        classification.setName(CLASS_NAME);

        Indication indication = new Indication();
        indication.setMedicineName(MED);
        indication.setId(ID);

        UndesirableAction undesirableAction = new UndesirableAction();
        undesirableAction.setDescription(DESCRIPTION);
        undesirableAction.setId(ID);
        undesirableAction.setClassifications(new HashSet<>(Collections.singletonList(classification)));
        undesirableAction.setIndications(new HashSet<>(Collections.singletonList(indication)));

        Evaluation evaluation = new Evaluation();
        evaluation.setId(ID);
        evaluation.setCaseNarrative(CASE_NARRATIVE);
        evaluation.setComment(COMMENT);
        evaluation.setUndesirableActions(new HashSet<>(Collections.singletonList(undesirableAction)));

        //when
        EvaluationForm evaluationForm = evaluationToFormMapper.evaluationToForm(evaluation);

        //then
        Assert.assertTrue(evaluationForm.getId() == ID);
        Assert.assertTrue(Objects.equals(evaluationForm.getCaseNarrative(), CASE_NARRATIVE));
        Assert.assertTrue(Objects.equals(evaluationForm.getComment(), COMMENT));
        Assert.assertTrue(evaluationForm.getUndesirableActions() != null && !evaluationForm.getUndesirableActions().isEmpty());
        Assert.assertTrue(Objects.equals(evaluationForm.getUndesirableActions().get(0).getId(), ID));
        Assert.assertTrue(Objects.equals(evaluationForm.getUndesirableActions().get(0).getDescription(), DESCRIPTION));
        Assert.assertTrue(Objects.equals(evaluationForm.getUndesirableActions().get(0).getClassifications().get(0).getId(), ID));
        Assert.assertTrue(Objects.equals(evaluationForm.getUndesirableActions().get(0).getClassifications().get(0).getName(), CLASS_NAME));
        Assert.assertTrue(Objects.equals(evaluationForm.getUndesirableActions().get(0).getIndications().get(0).getMedicineName(), MED));
        Assert.assertTrue(Objects.equals(evaluationForm.getUndesirableActions().get(0).getIndications().get(0).getId(), ID));

    }

}