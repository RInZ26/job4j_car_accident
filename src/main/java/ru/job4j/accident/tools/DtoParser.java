package ru.job4j.accident.tools;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.dto.AccidentDto;

@Component
@NoArgsConstructor
public class DtoParser {

    public Accident parseAccident(AccidentDto accidentDto) {
        Accident accident = Accident.of(accidentDto.getName(), accidentDto.getText(),
                accidentDto.getAddress(), accidentDto.getType(), accidentDto.getRules());
        accident.setId(accident.getId());
        return accident;
    }
}
