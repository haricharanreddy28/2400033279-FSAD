package com.klu.anno;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.klu.config.AppConfig;
import com.klu.model.StudentAnno;

public class AnnotationMainApp {

    public static void main(String[] args) {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        StudentAnno student = context.getBean(StudentAnno.class);
        System.out.println(student);
    }
}
