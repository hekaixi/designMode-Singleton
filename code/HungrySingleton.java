package com.hekx.designMode02.singleton;

/**
 * 饿汉式：
 * 启动时自动初始化，后续没有安全问题
 * 适用于资源消耗较小，可以随项目启动的情况下
 */
public class HungrySingleton {

    private static final HungrySingleton singleton = new HungrySingleton();

    private HungrySingleton(){}

    public static HungrySingleton getInstance(){
        return singleton;
    }

}
