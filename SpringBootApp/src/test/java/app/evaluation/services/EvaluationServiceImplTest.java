package app.evaluation.services;

import db.evaluation.springData.EvaluationDao;
import model.evaluation.Evaluation;
import model.evaluation.EvaluationForm;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class EvaluationServiceImplTest {

    private static final String CASE_NARRATIVE = "case";
    private static final String COMMENT = "comment";
    @InjectMocks
    private EvaluationServiceImpl evaluationService;

    @Mock
    private EvaluationDao evaluationDao;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getFormById() throws Exception {
        //when
        when(evaluationDao.findById(1L)).thenReturn(Optional.of(new Evaluation(COMMENT, CASE_NARRATIVE, new HashSet<>())));

        Optional<EvaluationForm> result = evaluationService.getFormById(1L);

        //then
        Assert.assertTrue(result.isPresent());
        Assert.assertTrue(result.get().getCaseNarrative().equals(CASE_NARRATIVE));
        Assert.assertTrue(result.get().getComment().equals(COMMENT));
        Assert.assertTrue(result.get().getUndesirableActions() != null);
    }

    @Test(expected = RuntimeException.class)
    public void getFormByIdNotFound() throws Exception {
        //when
        when(evaluationDao.findById(any())).thenReturn(null);

        evaluationService.getFormById(any());

    }
}