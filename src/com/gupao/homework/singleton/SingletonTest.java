package com.gupao.homework.singleton;

/**
 * @ClassName SingletonTest
 * @Author LIUHANPENG
 * @Date 2019/11/11 0011 10:34
 **/
public class SingletonTest {
    public static void main(String[] args){
        HungrySingleton hungrySingleton1 = HungrySingleton.getInstance();
        HungrySingleton hungrySingleton2 = HungrySingleton.getInstance();
        //System.out.println(hungrySingleton1==hungrySingleton2);

        LazySingleton lazySingleton1 = LazySingleton.getInstance();
        LazySingleton lazySingleton2 = LazySingleton.getInstance();
        System.out.println(lazySingleton1==lazySingleton2);

        InnerClassSingleton innerClassSingleton1 = InnerClassSingleton.getInstance();
        InnerClassSingleton innerClassSingleton2 = InnerClassSingleton.getInstance();
        System.out.println(innerClassSingleton1==innerClassSingleton2);

        EnumSingleton enumSingleton1= EnumSingleton.getInstance();
        EnumSingleton enumSingleton2= EnumSingleton.getInstance();
        System.out.println(enumSingleton1==enumSingleton2);

        ThreadLoaclSingleton threadLoaclSingleton1 = ThreadLoaclSingleton.getInstance();
        ThreadLoaclSingleton threadLoaclSingleton2 = ThreadLoaclSingleton.getInstance();
        System.out.println(threadLoaclSingleton1==threadLoaclSingleton2);
    }
}
