package com.minhe.toySpring.service.impl;

import com.minhe.toySpring.service.OutputService;

/**
 * @program: toySpring
 * @description:
 * @author: MinheZ
 * @create: 2019-04-11 10:56
 **/

public class OutputServiceImpl implements OutputService {

    public void output(String text) {
        System.out.println(text);
    }
}
