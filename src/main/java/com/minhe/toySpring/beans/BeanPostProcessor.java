package com.minhe.toySpring.beans;

/**
 * @program: toySpring
 * @description: 用于在bean定义初始化时嵌入相关操作, 例如：在 postProcessorAfterInitialization
 *               方法中，使用动态代理的方式，返回一个对象的代理对象。解决了在 IoC 容器的何处植入 AOP 的问题。
 * @author: MinheZ
 * @create: 2019-04-11 14:50
 **/

public interface BeanPostProcessor {

    Object postProcessorBeforeInitialization(Object bean, String beanName) throws Exception;

    Object postProcessorAfterInitialization(Object bean, String beanName) throws Exception;

}
