package com.nmm.dubbo.complier;

import org.apache.dubbo.common.compiler.support.JdkCompiler;
import org.springframework.stereotype.Component;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.security.SecureClassLoader;

/**
 * 实现编辑
 */
public class DynamicJavaComplierManager extends ForwardingJavaFileManager {

    private JavaClassObject object;

    public DynamicJavaComplierManager(JavaFileManager fileManager) {
        super(fileManager);
    }

    /**
     * 获取字节码输出对象
     * @param location
     * @param className
     * @param kind
     * @param sibling
     * @return
     * @throws IOException
     */
    @Override
    public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind, FileObject sibling) throws IOException {
        this.object = new JavaClassObject(className, kind);
        return this.object;
    }


    @Override
    public ClassLoader getClassLoader(Location location) {
        return new SecureClassLoader(){
            @Override
            protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
                byte[] code = object.getClassBytes();
                return super.defineClass(name, code,0,code.length);
            }
        };
    }

    @Override
    public String inferBinaryName(Location loc, JavaFileObject file) {
        if (file instanceof JavaSourceObject || file instanceof JavaClassObject) {
            return file.getName();
        }
        return super.inferBinaryName(loc, file);
    }
}
