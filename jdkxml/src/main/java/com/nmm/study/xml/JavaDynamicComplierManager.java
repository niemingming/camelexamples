package com.nmm.study.xml;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.security.SecureClassLoader;

/**
 * 自定义manager，用于接收编译后文件
 */
public class JavaDynamicComplierManager extends ForwardingJavaFileManager {

    private JavaClassObject javaClassObject;

    /**
     * Creates a new instance of ForwardingJavaFileManager.
     *
     * @param fileManager delegate to this file manager
     */
    protected JavaDynamicComplierManager(JavaFileManager fileManager) {
        super(fileManager);
    }

    @Override
    public ClassLoader getClassLoader(Location location) {
        return new SecureClassLoader(){
            @Override
            protected Class<?> findClass(String name) throws ClassNotFoundException {
                byte[] bytes = javaClassObject.getClassBytes();
                return super.defineClass(name,bytes,0,bytes.length);
            }
        };
    }

    @Override
    public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind, FileObject sibling) throws IOException {
        this.javaClassObject = new JavaClassObject(className,kind);
        return this.javaClassObject;
    }

}
