package com.yanyang.test;

import com.yanyang.spring.mini.parse.ApplicationContext;
import com.yanyang.spring.mini.pojo.Category;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        String classPath = Main.class.getResource("/").getPath();
        File file = new File(classPath + "applicationContext.xml");
        ApplicationContext.parseXml(file);
        Category category = (Category)ApplicationContext.getBean("c");
        System.out.println(category.toString());
    }
}
