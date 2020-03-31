package app.jms.controllers;

import app.jms.services.SimpleJmsMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/jms")
public class SimpleJmsMessageController {

    private SimpleJmsMessageService simpleJmsMessageService;

    @Autowired
    public SimpleJmsMessageController(SimpleJmsMessageService simpleJmsMessageService) {
        this.simpleJmsMessageService = simpleJmsMessageService;
    }

    @RequestMapping("all")
    public String getAllMessages(Model model) {
        simpleJmsMessageService.getAllMessages().ifPresent(simpleJmsMessages -> model.addAttribute("msgs", simpleJmsMessages));
        return "jms/jms_view";
    }
}
