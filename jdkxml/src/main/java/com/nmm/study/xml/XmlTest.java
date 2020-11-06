package com.nmm.study.xml;

import com.alibaba.fastjson.JSONObject;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.dom.DOMText;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import javax.print.Doc;
import java.io.IOException;
import java.io.StringWriter;

public class XmlTest {

    public static void main(String[] args) throws IOException, DocumentException {
        Element element = DocumentFactory.getInstance().createElement("ser:sayHello");
        element.addNamespace("ser","http://webservice.study.nmm.com/");
        element.addElement("name").addText("world");

        System.out.println(element.toString());

        StringWriter sw = new StringWriter();
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");

        XMLWriter writer = new XMLWriter(sw,format);
        writer.write(element);
        writer.close();

        System.out.println(sw.toString());

        String name = DocumentHelper.parseText("<name>ddd</name>").getRootElement().getText();
        System.out.println(name);

        System.out.println(JSONObject.parseArray("[true,2,3]").get(0).getClass());


    }
}
