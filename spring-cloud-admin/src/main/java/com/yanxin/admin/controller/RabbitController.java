package com.yanxin.admin.controller;

import com.yanxin.admin.rabbit.work.RabbitProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitController {

    private RabbitProducer rabbitProducer;

    public RabbitController(RabbitProducer rabbitProducer) {
        this.rabbitProducer = rabbitProducer;
    }

    @GetMapping(value = "/work")
    public String work() {
        rabbitProducer.sendWorkMessage();
        return "ok";
    }
}
