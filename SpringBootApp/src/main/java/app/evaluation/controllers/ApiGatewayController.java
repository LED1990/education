package app.evaluation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApiGatewayController {

    @GetMapping("/")
    public String searchDataWithPagination() {
        return "redirect:/evaluation/v1/search?page=0";
    }
}
