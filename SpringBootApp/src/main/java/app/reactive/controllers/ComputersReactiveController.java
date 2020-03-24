package app.reactive.controllers;

import app.reactive.services.interfaces.ComputersReactiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("reactive/computers/v1/")
public class ComputersReactiveController {

    private ComputersReactiveService computersReactiveService;

    @Autowired
    public ComputersReactiveController(ComputersReactiveService computersReactiveService) {
        this.computersReactiveService = computersReactiveService;
    }

    @GetMapping("all")
    public String getAllComputers(Model model){

        //difference between traditional coding is that request to mongoDB is triggered when block() is called
        model.addAttribute("computers", computersReactiveService.getAllComputers());

        return "/computer/computers";
    }


}
