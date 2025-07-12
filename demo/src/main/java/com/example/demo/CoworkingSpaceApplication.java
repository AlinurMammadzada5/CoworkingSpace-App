package com.example.demo;

import com.example.demo.UI.Menu;
import com.example.demo.config.SpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CoworkingSpaceApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SpringConfig.class);

        Menu menu = context.getBean(Menu.class);
        menu.showMenu();

        context.close();
    }
}
