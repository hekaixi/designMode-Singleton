package com.hekx.designMode02.singleton;

import java.lang.reflect.Constructor;

/**
 * 使用内部类机制实现单例
 * 类似懒加载机制，本实例中，当执行`LazyHolder.LAZY;`时才会加载内部类【初次涉及到内部类】，内部类中静态的属性决定了只加载一次，final关键字决定了指向不可变
 *
 * 【*】 对于构造的保护：虽然通过私有化构造函数可以确保无法通过关键字new创建对象，但无法避免其他手段的入侵【反射机制强制访问/反序列化等技术】
 */
public class LazyInnerClassSingleton {

    private LazyInnerClassSingleton() {
        if(LazyHolder.LAZY!=null){
            throw new RuntimeException("不允许创建多实例");
        }
        System.out.println("Constructor");
    }

    private static void test(){
        System.out.println("before ......");
        try {
            Thread.sleep(3000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static final LazyInnerClassSingleton getInstance() {
        test();
        return LazyHolder.LAZY;
    }

    private static class LazyHolder {
        static {
            System.out.println("LazyHolder init start ....");
        }
        private static final LazyInnerClassSingleton LAZY = new LazyInnerClassSingleton();
        static {
            System.out.println("LazyHolder init end ....");
        }
    }
}



class LazyInnerClassSingletonTest{
    public static void main(String[] ags) throws Exception {
        LazyInnerClassSingleton o1 = LazyInnerClassSingleton.getInstance();
//
//        Class clazz = LazyInnerClassSingleton.class;
//        Constructor c = clazz.getDeclaredConstructor(null); //强制访问
//        c.setAccessible(true);
//        LazyInnerClassSingleton o2 = (LazyInnerClassSingleton) c.newInstance();
//        LazyInnerClassSingleton o3 = (LazyInnerClassSingleton) c.newInstance();
//
//        System.out.println((o1==o2)+"||"+(o2==o3));
    }
}
