package com.minhe.beans;

import java.util.Map;

/**
 * @program: toySpring
 * @description: 实现 BeanDefinitionReader 接口的抽象类（未具体实现 loadBeanDefinitions，
 *                   而是规范了BeanDefinitionReader 的基本结构）
 * @author: MinheZ
 * @create: 2019-04-10 16:51
 **/

public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{
    // String, BeanDefinition 保存 IoC 容器里的类定义
    private Map<String, BeanDefinition> registery;

    //private

}
