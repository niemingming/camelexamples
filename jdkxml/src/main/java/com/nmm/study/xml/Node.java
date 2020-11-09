package com.nmm.study.xml;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;
import javax.xml.bind.annotation.XmlRootElement;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.Arrays;

@XmlRootElement(name = "project",namespace = "ser")
public class Node {

    public static void main(String[] args) throws Exception{
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        JavaDynamicComplierManager complierManager = new JavaDynamicComplierManager(compiler.getStandardFileManager(null,null,null));
        JavaCompiler.CompilationTask task = compiler.getTask(null,complierManager,null,null,null,getComplieFile());
        if (task.call()) {
            Class clazz = complierManager.getClassLoader(null).loadClass("com.nmm.study.Test");
            Object obj = clazz.newInstance();
            Method hello = clazz.getDeclaredMethod("test",String.class);
            hello.invoke(obj,"world");
        }else {
            System.out.println("编译失败！");
        }
    }

    private static Iterable<? extends JavaFileObject> getComplieFile() throws URISyntaxException {

        String code = "package com.nmm.study;\n" +
                "public class Test{\n" +
                "public void test(String name){\n" +
                "System.out.println(\"hello \" + name);\n" +
                "}\n" +
                "}\n";

        JavaSourceObject object = new JavaSourceObject("Test.java",code);
        return Arrays.asList(object);

    }


}
