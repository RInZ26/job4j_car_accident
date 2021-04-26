package ru.job4j.di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringDI {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("ru.job4j.di");
        context.refresh();

        StartUI ui = context.getBean(StartUI.class);
        StartUI another = context.getBean(StartUI.class);
        ui.add("Petr Arsentev");
        another.add("Ivan ivanov");
        System.out.println("origin:");
        ui.print();
        System.out.println("another:");
        another.print();
        System.out.println(ui.getInput().askStr("Spring is here?"));
    }
}