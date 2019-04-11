package com.minhe.toySpring;

/**
 * @program: toySpring
 * @description: 用于代表 property 标签的 ref 属性里面的对象
 * @author: MinheZ
 * @create: 2019-04-11 10:07
 **/

public class BeanReference {

    public String name;
    public Object bean;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public BeanReference(String name) {
        this.name = name;
    }
}
