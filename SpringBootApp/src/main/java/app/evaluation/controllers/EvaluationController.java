package app.evaluation.controllers;

import app.evaluation.services.interfaces.EvaluationSearchService;
import db.evaluation.springData.EvaluationDao;
import model.evaluation.Evaluation;
import model.evaluation.EvaluationSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("evaluation/v1/")
public class EvaluationController {


    private EvaluationSearchService evaluationSearchService;

    @Autowired
    public EvaluationController(EvaluationSearchService evaluationSearchService) {
        this.evaluationSearchService = evaluationSearchService;
    }

    @RequestMapping({"", "/"})
    public String getIndexPage(Model model){
        Optional<List<Evaluation>> result = evaluationSearchService.getAll();
        result.ifPresent(evaluations -> model.addAttribute("evalList", evaluations));
        return "evaluation/index";
    }
}
