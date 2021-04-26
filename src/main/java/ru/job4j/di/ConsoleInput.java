package ru.job4j.di;

import java.util.Scanner;

public class ConsoleInput {

    public static final Scanner SCANNER = new Scanner(System.in);

    public String askStr(String question) {
        System.out.println(question);
        return SCANNER.nextLine();
    }
}
