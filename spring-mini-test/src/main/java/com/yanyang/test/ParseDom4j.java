package com.yanyang.test;


import org.dom4j.*;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;
import java.util.List;

public class ParseDom4j {

    public static void main(String[] args) {
        // 执行dom4j解析xml方法
        parseDom4j(new File("D:\\dom4j.xml"));
    }

    public static void parseDom4j(File file) {
        try {
            // 创建一个SAXReader解析器
            SAXReader reader = new SAXReader();

            // 读取xml文件,转换成Document结点
            Document doc = reader.read(file);

            // 递归打印xml文档信息
//            StringBuffer buffer = new StringBuffer();
//            parseElement(doc.getRootElement(), buffer);
//            System.out.println(buffer.toString());
            List<Node> students = doc.selectNodes("//property");
            System.out.println(students.size());
//            Element root = doc.getRootElement();
//            System.out.println(root.getName());
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void parseElement(Element element, StringBuffer buffer) {
        buffer.append("<"+element.getName());
        List<Attribute> attrs = element.attributes();
        if (attrs != null) {
            for (Attribute attr : attrs) {
                buffer.append(" "+attr.getName()+"=\""+attr.getValue()+"\"");
            }
        }
        buffer.append(">");

        Iterator<Node> iterator = element.nodeIterator();
        while (iterator.hasNext()) {
            Node node = iterator.next();
            if (node instanceof Element) {
                Element eleNode = (Element) node;
                parseElement(eleNode, buffer);
            }
            if (node instanceof Text) {
                Text text = (Text) node;
                buffer.append(text.getText());
            }
            if (node instanceof CDATA) {
                CDATA dataNode = (CDATA) node;
                buffer.append(dataNode.getText());
            }
            if (node instanceof Comment) {
                Comment comNode = (Comment) node;
                buffer.append(comNode.getText());
            }
        }
        buffer.append("</"+element.getName()+">");
    }
}
