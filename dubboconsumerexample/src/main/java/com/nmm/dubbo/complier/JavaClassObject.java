package com.nmm.dubbo.complier;

import javax.tools.SimpleJavaFileObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

public class JavaClassObject extends SimpleJavaFileObject {

    private ByteArrayOutputStream bos;

    /**
     *
     * @param className  the URI for this file object
     * @param kind the kind of this file object
     */
    protected JavaClassObject(String className, Kind kind) {
        super(URI.create("string:///" + className.replace("\\.","/") + kind.extension), kind);
        bos = new ByteArrayOutputStream();
    }

    @Override
    public OutputStream openOutputStream() throws IOException {
        return bos;
    }

    /**
     * 获取编译后字节码
     * @return
     */
    public byte[] getClassBytes(){
        return this.bos.toByteArray();
    }
}
