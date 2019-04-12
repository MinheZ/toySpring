package com.minhe.toySpring.aop;

import com.minhe.toySpring.beans.factory.BeanFactory;

/**
 * @program: toySpring
 * @description: 实现该接口则有操作beanFactory的能力
 * @author: MinheZ
 * @create: 2019-04-12 14:39
 **/

public interface BeanFactoryAware {

    void setBeanFactory(BeanFactory beanFactory) throws Exception;

}
