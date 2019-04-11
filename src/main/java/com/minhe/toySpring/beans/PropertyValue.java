package com.minhe.toySpring.beans;

/**
 * @program: toySpring
 * @description: 用于记录 bean 配置中的标签属性值
 * @author: MinheZ
 * @create: 2019-04-10 15:34
 **/

public class PropertyValue {

    private final String name;
    /*
    * Spring XML 文件中，键是 name，值是 value（直接注入） 或者 ref（解析转化为实际的 Object才行）
    * */
    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
