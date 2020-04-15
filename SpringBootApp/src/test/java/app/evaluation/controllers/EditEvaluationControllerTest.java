package app.evaluation.controllers;

import app.evaluation.services.interfaces.EvaluationService;
import model.evaluation.forms.ClassificationForm;
import model.evaluation.forms.EvaluationForm;
import model.evaluation.forms.IndicationForm;
import model.evaluation.forms.UndesirableActionForm;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class EditEvaluationControllerTest {

    @InjectMocks
    private EditEvaluationController editEvaluationController;

    @Mock
    private EvaluationService evaluationService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(editEvaluationController).build();
    }

    @Test
    public void getEvaluationToEdit() throws Exception {
        //when
        when(evaluationService.getFormById(any())).thenReturn(Optional.of(new EvaluationForm()));

        //then
        mockMvc.perform(get("/evaluation/v1/edit/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("evaluationForm"))
                .andExpect(view().name("edit"));
    }

    @Test
    public void addNewAction() throws Exception {
        mockMvc.perform((post("/evaluation/v1/edit/update")).param("addAction", "1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("evaluationForm"))
                .andExpect(model().attribute("evaluationForm", hasProperty("undesirableActions", hasSize(1))))
                .andExpect(view().name("fragments/editEvaluationFragments::evaluation-form"));
    }

    @Test
    public void removeAction() throws Exception {
        //given
        EvaluationForm evaluationForm = new EvaluationForm();
        evaluationForm.setUndesirableActions(new ArrayList<>());
        evaluationForm.getUndesirableActions().add(new UndesirableActionForm());
        //then
        mockMvc.perform((post("/evaluation/v1/edit/update"))
                .param("removeAction", "0").flashAttr("evaluationForm", evaluationForm))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("evaluationForm"))
                .andExpect(model().attribute("evaluationForm", hasProperty("undesirableActions")))
                .andExpect(model().attribute("evaluationForm", hasProperty("undesirableActions", iterableWithSize(0))))
                .andExpect(view().name("fragments/editEvaluationFragments::evaluation-form"));
    }

    @Test
    public void addNewClassification() throws Exception {
        //given
        EvaluationForm evaluationForm = new EvaluationForm();
        evaluationForm.setUndesirableActions(new ArrayList<>());
        evaluationForm.getUndesirableActions().add(new UndesirableActionForm());

        //when
        mockMvc.perform((post("/evaluation/v1/edit/update"))
                .param("addClassification", "0").flashAttr("evaluationForm", evaluationForm))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("evaluationForm"))
                .andExpect(model().attribute("evaluationForm", hasProperty("undesirableActions")))
                .andExpect(view().name("fragments/editEvaluationFragments::evaluation-form"));
        //then
        Assert.assertTrue(evaluationForm.getUndesirableActions().get(0).getClassifications().size() == 1);
    }

    @Test
    public void removeClassification() throws Exception {
        //given
        EvaluationForm evaluationForm = new EvaluationForm();
        evaluationForm.setUndesirableActions(new ArrayList<>());
        evaluationForm.getUndesirableActions().add(new UndesirableActionForm());
        evaluationForm.getUndesirableActions().get(0).setClassifications(new ArrayList<>());
        evaluationForm.getUndesirableActions().get(0).getClassifications().add(new ClassificationForm());

        //when
        mockMvc.perform((post("/evaluation/v1/edit/update"))
                .param("removeClassification", "0;0").flashAttr("evaluationForm", evaluationForm))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("evaluationForm"))
                .andExpect(model().attribute("evaluationForm", hasProperty("undesirableActions")))
                .andExpect(view().name("fragments/editEvaluationFragments::evaluation-form"));
        //then
        Assert.assertTrue(evaluationForm.getUndesirableActions().get(0).getClassifications().size() == 0);
    }

    @Test
    public void addNewIndication() throws Exception {
        //given
        EvaluationForm evaluationForm = new EvaluationForm();
        evaluationForm.setUndesirableActions(new ArrayList<>());
        evaluationForm.getUndesirableActions().add(new UndesirableActionForm());

        //when
        mockMvc.perform((post("/evaluation/v1/edit/update"))
                .param("addIndication", "0").flashAttr("evaluationForm", evaluationForm))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("evaluationForm"))
                .andExpect(model().attribute("evaluationForm", hasProperty("undesirableActions")))
                .andExpect(view().name("fragments/editEvaluationFragments::evaluation-form"));
        //then
        Assert.assertTrue(evaluationForm.getUndesirableActions().get(0).getIndications().size() == 1);
    }

    @Test
    public void removeIndication() throws Exception {
        //given
        EvaluationForm evaluationForm = new EvaluationForm();
        evaluationForm.setUndesirableActions(new ArrayList<>());
        evaluationForm.getUndesirableActions().add(new UndesirableActionForm());
        evaluationForm.getUndesirableActions().get(0).setIndications(new ArrayList<>());
        evaluationForm.getUndesirableActions().get(0).getIndications().add(new IndicationForm());

        //when
        mockMvc.perform((post("/evaluation/v1/edit/update"))
                .param("removeIndication", "0;0").flashAttr("evaluationForm", evaluationForm))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("evaluationForm"))
                .andExpect(model().attribute("evaluationForm", hasProperty("undesirableActions")))
                .andExpect(view().name("fragments/editEvaluationFragments::evaluation-form"));
        //then
        Assert.assertTrue(evaluationForm.getUndesirableActions().get(0).getIndications().size() == 0);
    }

    @Test
    public void saveChanges() throws Exception {
        //when
        mockMvc.perform((post("/evaluation/v1/edit/save")))
                .andExpect(view().name("redirect:/evaluation/v1/search?page=0"));

        //then
        verify(evaluationService, times(1)).updateEvaluation(any());
    }

}