package ru.job4j.accident.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccidentControl {
    private final AccidentService accidentService;

    @Autowired
    public AccidentControl(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", accidentService.findAllTypes());
        model.addAttribute("rules", accidentService.findAllRules());

        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        accidentService.saveAccident(accident, req.getParameterValues("rIds"));
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("editedAccident", accidentService.findAccidentById(id));
        model.addAttribute("types", accidentService.findAllTypes());
        model.addAttribute("rules", accidentService.findAllRules());

        return "accident/edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Accident accident, HttpServletRequest req) {
        accidentService.updateAccident(accident, req.getParameterValues("rIds"));
        return "redirect:/";
    }
}

