package com.hekx.designMode02.singleton;

/**
 * 懒汉式：
 * 在需要资源的时候，才进行初始化，并返回
 * 适用于资源消耗较大，不用随项目启动的情况下，存在线程安全问题
 */
public class LazySimpleSingleton {

    private static LazySimpleSingleton singleton = null;

    private LazySimpleSingleton(){}

    public static LazySimpleSingleton getSingleton(){
        if(singleton == null){
            singleton = new LazySimpleSingleton();
        }
        return singleton;
    }

}
