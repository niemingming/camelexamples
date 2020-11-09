package com.nmm.study.xml;

import javax.tools.SimpleJavaFileObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

/**
 * 变异后字节码接收类
 */
public class JavaClassObject extends SimpleJavaFileObject {

    private ByteArrayOutputStream byteArrayOutputStream;

    /**
     * Construct a SimpleJavaFileObject of the given kind and with the
     * given URI.
     *
     * @param uri  the URI for this file object
     * @param kind the kind of this file object
     */
    protected JavaClassObject(String uri, Kind kind) {
        super(URI.create("string:///" + uri.replace("\\.","/") + kind.extension), kind);
        this.byteArrayOutputStream = new ByteArrayOutputStream();
    }

    @Override
    public OutputStream openOutputStream() throws IOException {
        return byteArrayOutputStream;
    }

    public byte[] getClassBytes(){
        return byteArrayOutputStream.toByteArray();
    }
}
