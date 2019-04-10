package com.hekx.designMode02.singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;

/**
 * 枚举类型单例：  <br/>
 * 普通类无法直接继承Enum，使用枚举的类经过编译后的文件见EnumSingleton.jad   <br/>
 *  <br/>
 * 关键代码==>  <br/>
 * <br/>
 * ################################################<br/>
 * extends Enum <br/>
 * <br/>
 *     private EnumSingleton(String s, int i){  <br/>
 *         //(String name, int ordinal) <br/>
 *         super(s, i); <br/>
 *     }    <br/>
 *  <br/>
 *     public static final EnumSingleton INSTANCE;  <br/>
 *     private Object data;<br/>
 *     private static final EnumSingleton $VALUES[];    <br/>
 *  <br/>
 *     static{  <br/>
 *         INSTANCE = new EnumSingleton("INSTANCE", 0); <br/>
 *         $VALUES = (new EnumSingleton[] { <br/>
 *             INSTANCE <br/>
 *         });  <br/>
 *     }    <br/>
 *  ################################################<br/>
 *  <br/>
 * 编译后会默认通过静态字段/静态块进行初始化，多个枚举的值将以数组的形式存储，综上所见枚举类型默认使用单例【饿汉式】<br/>
 * <br/>
 * <br/>
 * ###### 枚举对反序列化的保护：采取了枚举的正常查找方式 ######<br/>
 * try {<br/>
 *      Enum<?> en = Enum.valueOf((Class)cl, name);<br/>
 *      result = en;<br/>
 * } catch (IllegalArgumentException ex) {<br/>
 *      throw (IOException) new InvalidObjectException("enum constant " + name + " does not exist in " + cl).initCause(ex);<br/>
 * }<br/>
 * <br/>
 * <br/>
 *
 * ###### 枚举对反射的保护： ######<br/>
 * ###### ①枚举的构造只能通过子类调用 ######<br/>
 * protected Enum(String name, int ordinal) {<br/>
 *     this.name = name;<br/>
 *     this.ordinal = ordinal;<br/>
 * }<br/>
 * <br/>
 * ###### ②反射使用强制访问也无法对枚举类型进行创建 ######<br/>
 *  if ((clazz.getModifiers() & Modifier.ENUM) != 0)<br/>
 *  throw new IllegalArgumentException("Cannot reflectively create enum objects");<br/>
 *
 */
public enum EnumSingleton {

    INSTANCE;

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static EnumSingleton getInstance() { return INSTANCE; }
}

class EnumSingletonTest {
    public static void main(String[] args) {
        try {
            EnumSingleton instance1 = null;
            EnumSingleton instance2 = EnumSingleton.getInstance();
            instance2.setData(new Object());
            FileOutputStream fos = new FileOutputStream("EnumSingleton.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(instance2);
            oos.flush();
            oos.close();
            FileInputStream fis = new FileInputStream("EnumSingleton.obj");
            ObjectInputStream ois = new ObjectInputStream(fis);
            instance1 = (EnumSingleton) ois.readObject();
            ois.close();
            System.out.println(instance1.getData());
            System.out.println(instance2.getData());
            System.out.println(instance1.getData() == instance2.getData());

            try {
                Class clazz = EnumSingleton.class;
                Constructor c = clazz.getDeclaredConstructor(String.class,int.class);
                c.setAccessible(true);
                EnumSingleton enumSingleton = (EnumSingleton)c.newInstance("hekx",1);

            }catch (Exception e){
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}