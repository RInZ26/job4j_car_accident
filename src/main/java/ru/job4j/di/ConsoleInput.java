package ru.job4j.di;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleInput {

    public static final Scanner SCANNER = new Scanner(System.in);

    public String askStr(String question) {
        System.out.println(question);
        return SCANNER.nextLine();
    }
}
