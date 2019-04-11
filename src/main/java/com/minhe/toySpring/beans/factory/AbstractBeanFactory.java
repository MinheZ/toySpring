package com.minhe.toySpring.beans.factory;

import com.minhe.toySpring.beans.BeanDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: toySpring
 * @description: BeanFactory 的一种抽象类实现，规范了 IoC 容器的基本结构。
 *               IoC 容器的结构：AbstractBeanFactory 维护一个 beanDefinitionMap 哈希表用于保存类的定义信息（BeanDefinition）。
 * @author: MinheZ
 * @create: 2019-04-11 14:54
 **/

public class AbstractBeanFactory implements BeanFactory{

    // bean定义的信息和bean的name保存在线程安全的HashMap中
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

    // 保存完成注册的 bean 的 name
    private final List<String> beanDefinitionNames = new ArrayList<String>();

    public Object getBean(String name) throws Exception {
        return null;
    }
}
