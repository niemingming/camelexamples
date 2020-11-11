package com.nmm.dubbo.complier;

import javax.tools.SimpleJavaFileObject;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class JavaSourceObject extends SimpleJavaFileObject {

    private String content;

    /**
     *
     * @param filename  文件名
     * @param content 源文件内容
     */
    public JavaSourceObject(String filename, String content) throws URISyntaxException {
        super(new URI(filename), Kind.SOURCE);
        this.content = content;
    }

    /**
     * 覆盖原编译项，获取编译源码
     * @param ignoreEncodingErrors
     * @return
     * @throws IOException
     */
    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
        return this.content;
    }
}
