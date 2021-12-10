package ru.job4j.accident.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.WebApplicationContext;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

import java.util.List;

@Controller
public class IndexControl {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @GetMapping("/")
    public String index(Model model) {
        List<Accident> accidents = webApplicationContext.getBean(AccidentService.class)
                .getAllAccidents();
        model.addAttribute("accidents", accidents);
        return "index";
    }
}