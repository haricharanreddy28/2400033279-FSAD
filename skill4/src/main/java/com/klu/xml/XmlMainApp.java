package com.klu.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.klu.model.Student;

public class XmlMainApp {

    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("beans.xml");

        Student student = context.getBean("student", Student.class);
        System.out.println(student);
    }
}
