package com.nmm.study.xml;

import javax.tools.SimpleJavaFileObject;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * 字符串源码文件类，用于加载源代码
 */
public class JavaSourceObject extends SimpleJavaFileObject {

    private String content;

    /**
     * Construct a SimpleJavaFileObject of the given kind and with the
     * given URI.
     *
     * @param className  the URI for this file object
     */
    public JavaSourceObject(String className,String content) throws URISyntaxException {
        super(new URI(className), Kind.SOURCE);
        this.content = content;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
        return content;
    }
}
