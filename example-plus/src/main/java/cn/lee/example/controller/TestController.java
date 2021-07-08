package cn.lee.example.controller;

import cn.lee.example.mq.RabbitChannel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author：lix492 @date: 2021/7/7
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

    private final RabbitChannel rabbitChannel;

    @GetMapping("/send")
    public void send(){
        String string="Hello,disney";
        log.info("send message-->{}",string);
        rabbitChannel.bookOrderOutput().send(MessageBuilder.withPayload(string).build());
    }

}
