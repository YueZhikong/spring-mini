package com.yanyang.test;


import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.*;

public class CreateDom4j {

    public static void main(String[] args) {
        // 执行dom4j生成xml方法
        createDom4j(new File("D:\\dom4j.xml"));
    }

    public static void createDom4j(File file) {
        try {
            // 创建一个Document实例
            Document doc = DocumentHelper.createDocument();

            // 添加根节点
            Element root = doc.addElement("root");

            // 在根节点下添加第一个子节点
            Element oneChildElement= root.addElement("person").addAttribute("attr", "root noe");
            oneChildElement.addElement("people")
                    .addAttribute("attr", "child one")
                    .addText("person one child one");
            oneChildElement.addElement("people")
                    .addAttribute("attr", "child two")
                    .addText("person one child two");

            // 在根节点下添加第一个子节点
            Element twoChildElement= root.addElement("person").addAttribute("attr", "root two");
            twoChildElement.addElement("people")
                    .addAttribute("attr", "child one")
                    .addText("person two child one");
            twoChildElement.addElement("people")
                    .addAttribute("attr", "child two")
                    .addText("person two child two");

            // xml格式化样式
             OutputFormat format = OutputFormat.createPrettyPrint(); // 默认样式

            // 自定义xml样式
//            OutputFormat format = new OutputFormat();
//            format.setIndentSize(2);  // 行缩进
//            format.setNewlines(true); // 一个结点为一行
//            format.setTrimText(true); // 去重空格
//            format.setPadText(true);
//            format.setNewLineAfterDeclaration(false); // 放置xml文件中第二行为空白行

            // 输出xml文件
            XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
            writer.write(doc);
            System.out.println("dom4j CreateDom4j success!");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
