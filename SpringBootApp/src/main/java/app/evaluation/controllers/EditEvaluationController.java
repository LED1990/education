package app.evaluation.controllers;

import app.evaluation.services.interfaces.EvaluationService;
import model.evaluation.forms.ClassificationForm;
import model.evaluation.forms.EvaluationForm;
import model.evaluation.forms.IndicationForm;
import model.evaluation.forms.UndesirableActionForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

/**
 * Controller for view updates without page reload
 */

@Controller
@RequestMapping("evaluation/v1/edit")
public class EditEvaluationController {

    private EvaluationService evaluationService;

    @Autowired
    public EditEvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @GetMapping
    @RequestMapping("{id}")
    public String getEvaluationToEdit(Model model, @PathVariable Long id) {
        Optional<EvaluationForm> result = evaluationService.getFormById(id);
        result.ifPresent(evaluationForm -> model.addAttribute("evaluationForm", evaluationForm));
        return "edit";
    }

    @PostMapping
    @RequestMapping(value = "/update", params = {"addAction"})
    public String addNewAction(final EvaluationForm evaluationForm, final BindingResult bindingResult) {
        IndicationForm newIndicationForm = IndicationForm.builder()
                .medicineName("default med name")
                .info("default info")
                .build();
        ClassificationForm newClassificationForm = ClassificationForm.builder()
                .code("default code")
                .name("default name")
                .build();
        UndesirableActionForm newAction = UndesirableActionForm.builder()
                .description("default description")
                .classifications(Collections.singletonList(newClassificationForm))
                .indications(Collections.singletonList(newIndicationForm))
                .build();
        if (evaluationForm.getUndesirableActions() == null){
            evaluationForm.setUndesirableActions(new ArrayList<>());
        }
        evaluationForm.getUndesirableActions().add(newAction);
        return "fragments/editEvaluationFragments::evaluation-form";
    }

    @PostMapping
    @RequestMapping(value = "/update", params = {"removeAction"})
    public String removeAction(final EvaluationForm evaluationForm, final BindingResult bindingResult, final HttpServletRequest req) {
        final Integer actionIndex = Integer.valueOf(req.getParameter("removeAction"));
        evaluationForm.getUndesirableActions().remove((int)actionIndex);
        return "fragments/editEvaluationFragments::evaluation-form";
    }

    @PostMapping
    @RequestMapping(value = "/update", params = {"addClassification"})
    public String addNewClassification(final EvaluationForm evaluationForm, final BindingResult bindingResult, final HttpServletRequest req) {
        final Integer actionIndex = Integer.valueOf(req.getParameter("addClassification"));
        ClassificationForm newClassificationForm = ClassificationForm.builder()
                .code("default code")
                .name("default name")
                .build();
        if (evaluationForm.getUndesirableActions().get(actionIndex).getClassifications() == null) {
            evaluationForm.getUndesirableActions().get(actionIndex).setClassifications(new ArrayList<>());
        }
        evaluationForm.getUndesirableActions().get(actionIndex).getClassifications().add(newClassificationForm);
        return "fragments/editEvaluationFragments::evaluation-form";
    }

    @PostMapping
    @RequestMapping(value = "/update", params = {"removeClassification"})
    public String removeClassification(final EvaluationForm evaluationForm, final BindingResult bindingResult, final HttpServletRequest req) {
        final String value = req.getParameter("removeClassification");
        if (!StringUtils.isEmpty(value)) {
            evaluationForm.getUndesirableActions().get(Integer.parseInt(value.split(";")[0])).getClassifications().remove(Integer.parseInt(value.split(";")[1]));
        }
        return "fragments/editEvaluationFragments::evaluation-form";
    }

    @PostMapping
    @RequestMapping(value = "/update", params = {"addIndication"})
    public String addNewCIndication(final EvaluationForm evaluationForm, final BindingResult bindingResult, final HttpServletRequest req) {
        final Integer actionIndex = Integer.valueOf(req.getParameter("addIndication"));
        IndicationForm newClassificationForm = IndicationForm.builder()
                .info("default info")
                .medicineName("default med name")
                .build();
        if (evaluationForm.getUndesirableActions().get(actionIndex).getIndications() == null) {
            evaluationForm.getUndesirableActions().get(actionIndex).setIndications(new ArrayList<>());
        }
        evaluationForm.getUndesirableActions().get(actionIndex).getIndications().add(newClassificationForm);
        return "fragments/editEvaluationFragments::evaluation-form";
    }

    @PostMapping
    @RequestMapping(value = "/update", params = {"removeIndication"})
    public String removeIndication(final EvaluationForm evaluationForm, final BindingResult bindingResult, final HttpServletRequest req) {
        final String value = req.getParameter("removeIndication");
        if (!StringUtils.isEmpty(value)) {
            evaluationForm.getUndesirableActions().get(Integer.parseInt(value.split(";")[0])).getIndications().remove(Integer.parseInt(value.split(";")[1]));
        }
        return "fragments/editEvaluationFragments::evaluation-form";
    }

    @PostMapping
    @RequestMapping(value = "/save")
    public String saveChanges(@ModelAttribute("evaluation") EvaluationForm evaluationForm) {
        evaluationService.updateEvaluation(evaluationForm);
        return "redirect:/evaluation/v1/search?page=0";
    }
}
