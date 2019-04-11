package com.minhe.toySpring.service.impl;

import com.minhe.toySpring.service.HelloWorldService;
import com.minhe.toySpring.service.OutputService;

/**
 * @program: toySpring
 * @description:
 * @author: MinheZ
 * @create: 2019-04-11 10:55
 **/

public class HelloWorldServiceImpl implements HelloWorldService {

    private String text;

    private OutputService outputService;

    public void helloWorld() {
        outputService.output(text);
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setOutputService(OutputService outputService) {
        this.outputService = outputService;
    }
}
