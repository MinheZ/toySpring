package com.minhe.toySpring.beans.factory;

import com.minhe.toySpring.aop.BeanFactoryAware;
import com.minhe.toySpring.beans.BeanDefinition;
import com.minhe.toySpring.beans.PropertyValue;

/**
 * @program: toySpring
 * @description: 可以实现自动装配的 BeanFactory。在这个工厂中，实现了 doCreateBean 方法
 * @author: MinheZ
 * @create: 2019-04-12 14:07
 **/

public class AutowireCapableBeanFactory extends AbstractBeanFactory{

    /**
     * @Description: 通过反射自动装配 bean 的所有信息
     * @Param: [bean, beanDefinition]
     * @return: void
     * @Author: MinheZ
     * @Date: 2019/4/12
    **/
    protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception {
        if (bean instanceof BeanFactoryAware) {
            ((BeanFactoryAware) bean).setBeanFactory(this);
        }
        for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValueList()) {

        }
    }
}
