package com.hekx.designMode02.singleton;

/**
 * 懒汉式：优化
 * 解决普通懒汉式的线程安全问题
 * 使用synchronized关键字可以在多线程运行状态下使得被保护代码块被依次访问，但会造成阻塞，所以使用时需要尽量缩小被保护的代码块的范围
 */
public class LazyDoubleCheckSingleton {

    private static LazyDoubleCheckSingleton singleton = null;

    private LazyDoubleCheckSingleton(){}

    public static LazyDoubleCheckSingleton getSingleton(){
        if(singleton == null){
            synchronized (LazyDoubleCheckSingleton.class){
                if (singleton == null){
                    singleton = new LazyDoubleCheckSingleton();
                }
            }
        }
        return singleton;
    }

}
