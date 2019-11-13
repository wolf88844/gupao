package com.gupao.homework.proxy;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MyProxy
 * @Author LIUHANPENG
 * @Date 2019/11/13 0013 13:07
 **/
public class MyProxy {
    public static final String ln = "\r\n";

    public static Object newProxyInstance(MyClassLoader classLoader,Class<?>[] interfaces,MyInvocationHandler h){
        //动态生成源代码.java文件
        String src = generateSrc(interfaces);
        //java文件输出到磁盘
        String path = MyProxy.class.getResource("").getPath();
        File file = new File(path + "$MyProxy0.java");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(src);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null!= fileWriter) {
                try {
                    fileWriter.flush();
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //把生成的java文件编译为class文件
        JavaCompiler systemJavaCompiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager standardFileManager = systemJavaCompiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> iterable = standardFileManager.getJavaFileObjects(file);
        JavaCompiler.CompilationTask task = systemJavaCompiler.getTask(null, standardFileManager, null, null, null, iterable);
        task.call();
        try {
            standardFileManager.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //编译生成的class文件加载到jvm
        Constructor<?> constructor = null;
        try {
            Class<?> proxy = classLoader.findClass("$MyProxy0");
            constructor = proxy.getConstructor(MyInvocationHandler.class);
            file.delete();
            //返回字节码充足以后的代理对象
            return constructor.newInstance(h);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        return null;
    }

    private static String generateSrc(Class<?>[] interfaces) {
        StringBuilder sb = new StringBuilder();
        sb.append("package com.gupao.homework.proxy;"+ln);
        sb.append("import com.gupao.homework.proxy.Person;"+ln);
        sb.append("import java.lang.reflect.*;"+ln);
        sb.append("public class $MyProxy0 implements "+interfaces[0].getName()+"{"+ln);
            sb.append("MyInvocationHandler h;"+ln);
            sb.append("public $MyProxy0(MyInvocationHandler h){"+ln);
                sb.append("this.h=h;"+ln);
            sb.append("}"+ln);

            for(Method m:interfaces[0].getMethods()){
                Class<?>[] params = m.getParameterTypes();
                StringBuilder paramNames = new StringBuilder();
                StringBuilder paramValues = new StringBuilder();
                StringBuilder paramClasses = new StringBuilder();
                for(int i=0;i<params.length;i++){
                    Class clazz = params[i];
                    String type = clazz.getName();
                    String paramName = toLowerFirstCase(clazz.getSimpleName());
                    paramNames.append(type+" "+paramName);
                    paramValues.append(paramName);
                    paramClasses.append(clazz.getName()+".class");
                    if(i>0 && i<params.length-1){
                        paramNames.append(",");
                        paramValues.append(",");
                        paramClasses.append(",");
                    }
                }
                sb.append("public "+m.getReturnType().getName()+" "+m.getName()+ "("+paramNames.toString()+"){"+ln);
                    sb.append("try{"+ln);
                        sb.append("Method m = "+interfaces[0].getName()+".class.getMethod(\""+m.getName()+"\",new Class[]{"+paramClasses.toString()+"});"+ln);
                        sb.append((hasReturnValue(m.getReturnType())?"return ":"")+getCaseCode("this.h.invoke(this,m,new Object[]{"+paramValues+"})",m.getReturnType())+";"+ln);
                    sb.append("}catch(Throwable e){"+ln);
                    sb.append("throw new UndeclaredThrowableException(e);"+ln);
                    sb.append("}"+ln);
                    sb.append(getReturnEmptyCode(m.getReturnType())+ln);
                sb.append("}"+ln);

            }
        sb.append("}"+ln);
        return sb.toString();
    }

    private static Map<Class,Class> mapping = new HashMap<>();
    static {
        mapping.put(int.class,Integer.class);
    }


    private static String getReturnEmptyCode(Class<?> returnClass){
        if(mapping.containsKey(returnClass)){
            return "return 0;";
        }else if(returnClass == void.class){
            return "";
        }else{
            return "return null;";
        }
    }

    private static String getCaseCode(String code,Class<?> returnClass){
        if(mapping.containsKey(returnClass)){
            return "(("+mapping.get(returnClass).getName()+")"+code+")."+returnClass.getSimpleName()+"Value()";
        }
        return code;
    }

    private static boolean hasReturnValue(Class<?> clazz){
        return clazz!=void.class;
    }

    private static String toLowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        chars[0]+=32;
        return new String(chars);
    }
}
