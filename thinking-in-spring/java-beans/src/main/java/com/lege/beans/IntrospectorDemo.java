package com.lege.beans;

import com.lege.beans.BeanUtilsDemo.UserInfo;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * @Author 了个
 * @date 2020/4/29 10:06
 * JDK内省类库------PropertyDescriptor类：(属性描述器)
 * 　　PropertyDescriptor类表示JavaBean类通过存储器导出一个属性。主要方法：
 * 　　1. getPropertyType()，获得属性的Class对象；
 * 　　2. getReadMethod()，获得用于读取属性值的方法；
 * 　　　3. getWriteMethod()，获得用于写入属性值的方法；
 * 　　4. hashCode()，获取对象的哈希值；
 * 　　5. setReadMethod(Method readMethod)，设置用于读取属性值的方法；
 * 　　6. setWriteMethod(Method writeMethod)，设置用于写入属性值的方法。
 */
public class IntrospectorDemo {
    public static void main(String[] args) throws Exception{
        UserInfo userInfo = new UserInfo();
        setProperty(userInfo,"userName");
        getProperty(userInfo,"userName");
    }
    // 设置bean的某个属性值
    public static void setProperty(UserInfo userInfo, String propertyName) throws Exception {
        // 获取bean的某个属性的描述符
        PropertyDescriptor propDesc = new PropertyDescriptor(propertyName, UserInfo.class);
        // 获得用于写入属性值的方法
        Method methodSetUserName = propDesc.getWriteMethod();
        // 写入属性值
        methodSetUserName.invoke(userInfo, "wong");
        System.out.println("set userName:" + userInfo.getUserName());
    }

    // 获取bean的某个属性值
    public static void getProperty(UserInfo userInfo, String propertyName) throws Exception {
        // 获取Bean的某个属性的描述符
        PropertyDescriptor proDescriptor = new PropertyDescriptor(propertyName, UserInfo.class);
        // 获得用于读取属性值的方法
        Method methodGetUserName = proDescriptor.getReadMethod();
        // 读取属性值
        Object objUserName = methodGetUserName.invoke(userInfo);
        System.out.println("get userName:" + objUserName.toString());
    }
}
