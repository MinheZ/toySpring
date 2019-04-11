package com.minhe.toySpring.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: toySpring
 * @description: 封装一个对象所有的 PropertyValue。  为什么不直接使用List？因为可以封装一些操作在里面
 * @author: MinheZ
 * @create: 2019-04-10 15:40
 **/

public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<PropertyValue>();

    public PropertyValues() {
    }

    public void addPropertyValue(PropertyValue propertyValue) {
        // 在这里可以对 propertyValue 做一些处理，直接使用 List 就不行
        this.propertyValueList.add(propertyValue);
    }

    public List<PropertyValue> getPropertyValueList() {
        return this.propertyValueList;
    }
}
