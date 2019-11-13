package com.gupao.homework.proxy;

import java.io.*;

/**
 * @ClassName MyClassLoader
 * @Author LIUHANPENG
 * @Date 2019/11/13 0013 13:08
 **/
public class MyClassLoader extends ClassLoader {
    private File classPathFile;

    public MyClassLoader() {
        String classPath = MyClassLoader.class.getResource("").getPath();
        this.classPathFile = new File(classPath);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String className = MyClassLoader.class.getPackage().getName()+"."+name;
        if(classPathFile!= null){
            File classFile = new File(classPathFile, name.replaceAll("\\.", "/") + ".class");
            if(classFile.exists()){
               // FileInputStream fileInputStream =null;
                //ByteArrayOutputStream byteArrayOutputStream = null;

                try ( FileInputStream fileInputStream =  new FileInputStream(classFile);
                      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();){
                    byte[] buff = new byte[1024];
                    int len;
                    while ((len = fileInputStream.read(buff))!=-1){
                        byteArrayOutputStream.write(buff,0,len);
                    }
                    return defineClass(className,byteArrayOutputStream.toByteArray(),0,byteArrayOutputStream.size());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
