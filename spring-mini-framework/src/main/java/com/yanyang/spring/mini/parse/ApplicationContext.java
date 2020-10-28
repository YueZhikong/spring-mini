package com.yanyang.spring.mini.parse;

import com.yanyang.spring.mini.Main;
import com.yanyang.spring.mini.pojo.Category;
import org.dom4j.*;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplicationContext {
    private static Map<String,Object> beanSave = new HashMap<String, Object>();
    public static void parseXml(File file){
        try{
            SAXReader reader = new SAXReader();

            Document doc = reader.read(file);
            List<Node> nodes = doc.selectNodes("/beans/bean");
            for (Node node:nodes){
                Element element = (Element)node;
                List<Attribute> attributes =element.attributes();
                String beanName = null;
                Class clazz=null;
                for (Attribute attribute:attributes){
                    if (attribute.getName().equals("name")){
                        beanName = attribute.getValue();
                        continue;
                    }
                    if (attribute.getName().equals("class")){
                        clazz = Class.forName(attribute.getValue());
                    }
                }
                if (clazz==null){
                    continue;
                }
                Object object = clazz.newInstance();
                List<Element> elements = element.elements();
                Map<String,String> beanProperties = new HashMap<String, String>();
                for (Element e:elements){
                    String property = null;
                    String value = null;
                    for (Attribute attribute:e.attributes()){
                        if (attribute.getName().equals("name")){
                            property = attribute.getValue();
                            continue;
                        }
                        if (attribute.getName().equals("value")){
                            value = attribute.getValue();
                        }
                    }
                    if (property != null&& value !=null){
                        beanProperties.put(property,value);
                    }
                }

               for (Field field:clazz.getDeclaredFields()){
                   if (beanProperties.get(field.getName())!=null){
                       field.setAccessible(true);
                       field.set(object,beanProperties.get(field.getName()));
                   }
               }
               beanSave.put(beanName,object);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
    public static Object getBean(String name){
        return beanSave.get(name);
    }
}
