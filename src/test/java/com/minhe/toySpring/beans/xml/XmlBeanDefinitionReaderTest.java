package com.minhe.toySpring.beans.xml;

import com.minhe.toySpring.beans.BeanDefinition;
import com.minhe.toySpring.beans.io.ResourceLoader;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;


public class XmlBeanDefinitionReaderTest {

    @Test
    public void loadBeanDefinitions() throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions("toySpring.xml");
        Map<String, BeanDefinition> registry = xmlBeanDefinitionReader.getRegistry();
        // 如果 condition 为 false，会报错
        Assert.assertTrue(registry.size() > 0);
    }


}