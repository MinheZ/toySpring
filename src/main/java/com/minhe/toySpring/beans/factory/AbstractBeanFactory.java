package com.minhe.toySpring.beans.factory;

import com.minhe.toySpring.beans.BeanDefinition;
import com.minhe.toySpring.beans.BeanPostProcessor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: toySpring
 * @description: BeanFactory 的一种抽象类实现，规范了 IoC 容器的基本结构。
 * IoC 容器的结构：AbstractBeanFactory 维护一个 beanDefinitionMap 哈希表用于保存类的定义信息（BeanDefinition）。
 * @author: MinheZ
 * @create: 2019-04-11 14:54
 **/

public class AbstractBeanFactory implements BeanFactory {

    // bean定义的信息和bean的name保存在线程安全的HashMap中
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

    // 保存完成注册的 bean 的 name
    private final List<String> beanDefinitionNames = new ArrayList<String>();

    /**
     * 增加bean处理程序：
     * 例如通过AspectJAwareAdvisorAutoProxyCreator#postProcessAfterInitialization()实现AOP的织入
     */
    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();

    // 根据 bean 的名称获取 bean 实例
    public Object getBean(String name) throws Exception {
        // 获取该 bean 的定义
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        // 如果没有这个 bean 的定义就抛异常
        if (beanDefinition == null)
            throw new IllegalArgumentException("No bean named " + name + " is defined");
        Object bean = beanDefinition.getBean();
        // 如果 bean 还没有装配
        if (bean == null) {
            // 装配 bean（实例化，并注入属性）
            bean = doCreatBean(beanDefinition);
            // 初始化 bean，例如生成相关代理类，用于 AOP 织入
            bean = initializeBean(bean, name);
            beanDefinition.setBean(bean);
        }
        return bean;
    }

    /**
     * @Description: 初始化 bean，可以再次进行 AOP 相关的操作：例如生成相关代理类，并返回
     * @Param: [bean, name]
     * @return: java.lang.Object
     * @Author: MinheZ
     * @Date: 2019/4/12
     **/
    protected Object initializeBean(Object bean, String name) throws Exception {
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessorBeforeInitialization(bean, name);
        }
        // 可以看看AutowireCapableBeanFactory的postProcessAfterInitialization()方法实现
        // 返回的可能是代理对象
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessorAfterInitialization(bean, name);
        }
        return bean;
    }

    /**
     * @Description: 装载 bean
     * @Param: [beanDefinition]
     * @return: java.lang.Object
     * @Author: MinheZ
     * @Date: 2019/4/12
     **/
    protected Object doCreatBean(BeanDefinition beanDefinition) throws Exception {
        Object bean = createBeanInstance(beanDefinition);
        beanDefinition.setBean(bean);
        // 注入属性的hook方法(参考模板方法设计模式中的hook方法)交给子类去实现
        // 例如：AutowireCapableBeanFactory.java实现了自动装配
        applyPropertyValues(bean, beanDefinition);
        return bean;
    }

    protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) {

    }

    protected Object createBeanInstance(BeanDefinition beanDefinition) throws Exception {
        return beanDefinition.getBeanClass().newInstance();
    }

    /**
     * @Description: 预处理bean的定义，将bean的名字提前存好,实现Ioc容器中存储单例bean
     * @Param: []
     * @return: void
     * @Author: MinheZ
     * @Date: 2019/4/12
     **/
    public void preInstantiateSingletons() throws Exception {
        Iterator it = this.beanDefinitionNames.iterator();
        while (it.hasNext()) {
            String beanName = (String) it.next();
            getBean(beanName);
        }
    }

    /**
     * @Description: 根据类型获取所有的 bean
     * @Param: [type]
     * @return: java.util.List
     * @Author: MinheZ
     * @Date: 2019/4/12
    **/
    public List getBeansForType(Class type) throws Exception {
        List beans = new ArrayList<Object>();
        for (String beanDefinitionName : beanDefinitionNames) {
            /**
             * boolean isAssignableFrom(Class<?> cls)
             * 判定此 Class 对象所表示的类或接口与指定的 Class 参数所表示的类或接口是否相同，或是否是其超类或超接口。
             */
            if (type.isAssignableFrom(beanDefinitionMap.get(beanDefinitionName).getClass())) {
                beans.add(getBean(beanDefinitionName));
            }
        }
        return beans;
    }

    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception {
        beanDefinitionMap.put(name, beanDefinition);
        beanDefinitionNames.add(name);
    }

    // 增加bean处理程序，例如AspectJAwareAdvisorAutoProxyCreator#postProcessAfterInitialization()
    public void addBeanProcessor(BeanPostProcessor beanPostProcessor) throws Exception {
        this.beanPostProcessors.add(beanPostProcessor);
    }
}
