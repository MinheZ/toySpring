package com.minhe.toySpring.beans.xml;


import com.minhe.toySpring.BeanReference;
import com.minhe.toySpring.beans.AbstractBeanDefinitionReader;
import com.minhe.toySpring.beans.BeanDefinition;
import com.minhe.toySpring.beans.PropertyValue;
import com.minhe.toySpring.beans.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

/**
 * @program: toySpring
 * @description: 从 xml 文件中读取 bean 的定义。注意: 在loadBeanDefinitions中并没有实例化bean，
 * 而只是注册了bean的定义
 * @author: MinheZ
 * @create: 2019-04-10 16:19
 **/

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(ResourceLoader resourceLoader) {
        super(resourceLoader);
    }

    // 加载并通过 bean 定义注册 bean
    public void loadBeanDefinitions(String location) throws Exception {
        // 获取资源输入流
        InputStream inputStream = getResourceLoader().getResource(location).getInputStream();

        /**
         * 从xml文件中读取所有bean的定义，并注册到registry中
         * 注意: 此时bean定义里并没有实例化该bean
         */
        doLoadBeanDefinitions(inputStream);
    }

    protected void doLoadBeanDefinitions(InputStream inputStream) throws Exception {
        // 读取工厂
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // 获取生成器
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        // 解析为 Document
        Document document = documentBuilder.parse(inputStream);
        // 解析并注册其中的 bean
        registerBeanDefinitions(document);
        // 关闭流
        inputStream.close();
    }

    private void registerBeanDefinitions(Document document) {
        // 获取根标签 <beans>
        Element root = document.getDocumentElement();
        parseBeanDefinitions(root);
    }

    private void parseBeanDefinitions(Element root) {
        // 获取<beans> 下的所有 <bean>
        NodeList nodeList = root.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            // 如果该字标签是 Element
            if (node instanceof Element) {
                Element ele = (Element) node;
                // 解析 bean 标签
                processBeanDefinition(ele);
            }
        }
    }

    /**
     * @Description: 处理（解析） bean 标签
     * @Param: [ele]
     * @return: void
     * @Author: MinheZ
     * @Date: 2019/4/10
     **/
    private void processBeanDefinition(Element ele) {
        // 获取 bean 标签的 id 属性作为 bean 的 name
        String name = ele.getAttribute("id");
        // 获取 bean 标签的 class 属性作为 bean 的 className
        String className = ele.getAttribute("class");

        BeanDefinition beanDefinition = new BeanDefinition();
        // 解析 bean 标签下的 property 子标签
        processProperty(ele, beanDefinition);
        // 设置className的同时，也在内部设置了Class
        beanDefinition.setBeanClassName(className);
        // 注册类定义
        getRegistry().put(name, beanDefinition);
    }

    /**
     * @Description: 解析 bean 标签下的 property 子标签
     * @Param: [ele, beanDefinition]
     * @return: void
     * @Author: MinheZ
     * @Date: 2019/4/10
     **/
    private void processProperty(Element ele, BeanDefinition beanDefinition) {
        // 获取所有 property 标签
        NodeList propertyNodes = ele.getElementsByTagName("property");
        for (int i = 0; i < propertyNodes.getLength(); i++) {
            Node node = propertyNodes.item(i);
            if (node instanceof Element) {
                Element propertyEle = (Element) node;
                String name = propertyEle.getAttribute("name");
                String value = propertyEle.getAttribute("value");
                // 这里需要判断 value 是不是引用类型
                if (value != null && value.length() > 0) {
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, value));
                } else {
                    // 否则为 ref 类型
                    String ref = propertyEle.getAttribute("ref");
                    if (ref == null || ref.length() == 0) {
                        throw new IllegalArgumentException("Configuration problem: <property> element for property '"
                                + name + "' must specify a ref or value");
                    }
                    BeanReference beanReference = new BeanReference(name);
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, beanReference));
                }
            }
        }
    }
}
