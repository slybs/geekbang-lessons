package com.lege.beans;

import java.beans.*;
import java.lang.reflect.Method;
import java.util.stream.Stream;

/**
 * @Author 了个
 * @date 2020/4/29 9:56
 */
public class BeanInfoDemo {
    public static void main(String[] args) throws IntrospectionException{
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class,Object.class);
        //Bean的基本描述信息
        BeanDescriptor beanDescriptor = beanInfo.getBeanDescriptor();
        /**
         * Name:Person
         * DisplayName:Person
         * BeanClass:class com.lege.beans.Person
         * CustomizerClass:null
         * ShortDescription:Person
         * Value1:null
         * Value2:null
         * Class:class java.beans.BeanDescriptor
         */
        System.out.println("Name:"+beanDescriptor.getName());
        System.out.println("DisplayName:"+beanDescriptor.getDisplayName());
        System.out.println("BeanClass:"+beanDescriptor.getBeanClass());
        System.out.println("CustomizerClass:"+beanDescriptor.getCustomizerClass());
        System.out.println("ShortDescription:"+beanDescriptor.getShortDescription());
        System.out.println("Value1:"+beanDescriptor.getValue("name"));
        System.out.println("Value2:"+beanDescriptor.getValue("age"));
        System.out.println("Class:"+beanDescriptor.getClass());


        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        //Stream.of(propertyDescriptors).forEach(propertyDescriptor -> print(propertyDescriptor))
        Stream.of(propertyDescriptors).forEach(BeanInfoDemo::print);

    }

    /**
     * java.beans.PropertyDescriptor:
     *      [name=age; propertyType=class java.lang.Integer; readMethod=public java.lang.Integer com.lege.beans.Person.getAge(); writeMethod=public void com.lege.beans.Person.setAge(java.lang.Integer)]
     * java.beans.PropertyDescriptor:
     *      [name=name; propertyType=class java.lang.String; readMethod=public java.lang.String com.lege.beans.Person.getName(); writeMethod=public void com.lege.beans.Person.setName(java.lang.String)]
     * @param propertyDescriptor
     */
    private static void print(PropertyDescriptor propertyDescriptor) {
        System.out.println(propertyDescriptor.toString());
        Class<?> propertyEditorClass = propertyDescriptor.getPropertyEditorClass();
        String propertyName = propertyEditorClass.getName();
        if("age".equals(propertyName)){
            propertyDescriptor.setPropertyEditorClass(String2IntegerPropertyEditor.class);
            //propertyDescriptor.createPropertyEditor();
        }
        //Method readMethod = propertyDescriptor.getReadMethod();
        //System.out.println("readMethod.getName()="+readMethod.getName());
        //Method writeMethod = propertyDescriptor.getWriteMethod();
        //System.out.println("writeMethod.getName()="+writeMethod.getName());
        //Class<?> propertyType = propertyDescriptor.getPropertyType();
        //System.out.println("propertyType.getName()="+propertyType.getName());
        //Class<?> propertyEditorClass = propertyDescriptor.getPropertyEditorClass();
        //System.out.println(propertyEditorClass);

    }
    static class String2IntegerPropertyEditor extends PropertyEditorSupport{
        @Override
        public void setAsText(String text){
            Integer value = Integer.valueOf(text);
            setValue(value);
        }
    }
}
