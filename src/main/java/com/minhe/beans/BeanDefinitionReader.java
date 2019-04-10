package com.minhe.beans;

/**
 * @program: toySpring
 * @description: 解析 BeanDefinition 的接口，暴露加载 bean 定义的方法
 * @author: MinheZ
 * @create: 2019-04-10 16:16
 **/

public interface BeanDefinitionReader {

    /**
     * @Description: 根据路径加载 bean 的定义
     * @Param: [location]
     * @return: void
     * @Author: MinheZ
     * @Date: 2019/4/10
    **/
    void loadBeanDefinitions(String location) throws Exception;

}
