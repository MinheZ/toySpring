package com.minhe.beans;

/**
 * @program: toySpring
 * @description: Bean在 IoC 容器中的定义， IoC 容器可以根据这个定义来生成实例 的问题
 *               以 BeanDefinition 类为核心发散出的几个类，都是用于解决 Bean 的具体
 *               它的类型是什么，它的属性赋予了哪些值或者引用定义问题，包括 Bean 的名
 *               字是什么、它的类型是什么，它的属性赋予了哪些值或者引用
 * @author: MinheZ
 * @create: 2019-04-10 16:55
 **/

public class BeanDefinition {

    private Object bean;

    /**
     * bean的类型
     * 根据其 类型 可以生成一个类实例，然后可以把 属性 注入进去。
     */
    private Class beanClass;

    // bean 的名字
    private String beanClassName;

    /*
    * bean 的所有属性
    * 每个属性都是键值对 String-Object
    * */
    private PropertyValues propertyValues = new PropertyValues();

    public BeanDefinition() {
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
        try {
            this.beanClass = Class.forName(beanClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
