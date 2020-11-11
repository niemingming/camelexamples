package com.nmm.dubbo.util;

/**
 * 生产源码,后期用freemark来代替
 */
public class SourceCreator {

    public static String sourceCode(String className,String methodName, String ... argTyps) {

        StringBuilder stringBuilder = new StringBuilder();
        String packagePath = className.indexOf(".") >0 ? (className.substring(0,className.lastIndexOf(".")) + ";\n"):"";
        stringBuilder.append("package ").append(packagePath);
        String name = className.indexOf(".") > 0 ? className.substring(className.lastIndexOf(".") + 1) : className;
        //类
        stringBuilder.append("public interface ").append(name).append(" {\n");
        //方法声明
        stringBuilder.append("public Object ").append(methodName).append(" ( ");
        int i = 0;
        for (String argTyp : argTyps) {
            stringBuilder.append(argTyp).append(" args" + i++ ).append(",");
        }
        if (i > 0) {
            stringBuilder.delete(stringBuilder.length()-1,stringBuilder.length());
        }
        stringBuilder.append(");\n");

        stringBuilder.append("}");

        System.out.println("源码为：\n" + stringBuilder.toString());
        return stringBuilder.toString();
    }

}
