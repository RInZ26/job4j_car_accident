package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.List;

@Service
public class AccidentService {
    @Autowired
    private WebApplicationContext webApplicationContext;

    public List<Accident> getAllAccidents() {
        return webApplicationContext.getBean(AccidentMem.class).getAllAccidents();
    }
}
