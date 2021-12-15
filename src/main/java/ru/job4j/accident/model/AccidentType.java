package ru.job4j.accident.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@NoArgsConstructor
public class AccidentType {
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String name;

    public static AccidentType of(int id) {
        AccidentType type = new AccidentType();
        type.id = id;
        return type;
    }

    public static AccidentType of(int id, String name) {
        AccidentType type = of(id);
        type.name = name;
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AccidentType that = (AccidentType) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
