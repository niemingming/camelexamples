package com.nmm.study.xml;

import java.io.*;

public class Yaowuzhengli {

    public static void main(String[] args) throws IOException {
        File directory = new File("F:/个人/yaowu");
        File info = new File(directory,"info.csv");
        File struct = new File(directory,"strctmol2");
        File other = new File(directory,"newstrctmol");

        LineNumberReader reader = new LineNumberReader(new FileReader(info));

        String line = null;
        while ((line = reader.readLine()) != null) {
            if (reader.getLineNumber() > 1) {
                String id = line.split(",")[0].trim();
                String name = line.split(",")[1].trim() + ".mol2";
                File detail = new File(struct,name);
                if (!detail.exists()) {
                    System.out.println("行号：" + reader.getLineNumber() + "," + line + ";未找到文件");
                    continue;
                }
                File target = new File(other,id);
                File targetfile = new File(target,name);
                if (!target.exists()) {
                    target.mkdirs();
                }
                boolean res = copyFile(detail,targetfile);
                if (!res)
                 System.out.println("行号：" + reader.getLineNumber() + "," + line + ";出现异常！");
            }
        }


    }

    private static boolean copyFile(File detail, File target) {
        try {
            FileInputStream fis = new FileInputStream(detail);
            FileOutputStream fos = new FileOutputStream(target);

            byte[] buffer = new byte[8196];
            int len = 0;
            while((len = fis.read(buffer)) > 0) {
                fos.write(buffer,0,len);
            }
            fis.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
