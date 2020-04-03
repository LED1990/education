package app.evaluation.controllers;

import app.evaluation.services.interfaces.EvaluationSearchService;
import app.evaluation.services.interfaces.EvaluationService;
import model.evaluation.Evaluation;
import model.evaluation.EvaluationForm;
import model.evaluation.EvaluationSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("evaluation/v1/")
public class EvaluationController {


    private EvaluationSearchService evaluationSearchService;
    private EvaluationService evaluationService;
    private EvaluationSearchCriteria searchCriteria = new EvaluationSearchCriteria();


    @Autowired
    public EvaluationController(EvaluationSearchService evaluationSearchService, EvaluationService evaluationService) {
        this.evaluationSearchService = evaluationSearchService;
        this.evaluationService = evaluationService;
    }

    @GetMapping("search")
    public String searchDataWithPagination(Model model, @RequestParam("page") Integer page) {
        Optional<Page<Evaluation>> result = evaluationSearchService.getByCriteria(searchCriteria, page - 1);
        result.ifPresent(evaluations -> model.addAttribute("evalList", evaluations.getContent()));
        result.ifPresent(evaluations -> model.addAttribute("totalElements", evaluations.getTotalElements()));
        result.ifPresent(evaluations -> model.addAttribute("pageInfo", evaluations));
        model.addAttribute("searchCriteria", searchCriteria);
        return "evaluation/evaluations";
    }

    @PostMapping("search")
    public String searchData(Model model, @ModelAttribute("searchCriteria") EvaluationSearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
        Optional<Page<Evaluation>> result = evaluationSearchService.getByCriteria(searchCriteria, 0);
        result.ifPresent(evaluations -> model.addAttribute("evalList", evaluations.getContent()));
        result.ifPresent(evaluations -> model.addAttribute("totalElements", evaluations.getTotalElements()));
        result.ifPresent(evaluations -> model.addAttribute("pageInfo", evaluations));
        return "evaluation/evaluations";
    }

    @GetMapping
    @RequestMapping("edit/{id}")
    public String getEvaluationToEdit(Model model, @PathVariable Long id) {
        Optional<EvaluationForm> result = evaluationService.getFormById(id);
        result.ifPresent(evaluationForm -> model.addAttribute("evaluationForm", evaluationForm));
        return "evaluation/edit";
    }

    @PostMapping
    @RequestMapping("edit")
    public String editEvaluation(@ModelAttribute("evaluation") EvaluationForm evaluationForm) {
//        evaluationSearchService.getFormById(id).ifPresent(evaluation -> model.addAttribute("evaluation", evaluation));
        return "";//todo redirect to search
    }
}
