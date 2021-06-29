package cn.lee.boot.service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * @author ：lix492
 * 2021/5/24
 */
@Component
public class MessageConsumer {

    @Bean
    Consumer<String> sendHello() {
        return str -> {
            System.out.println("消费者收到消息:" + str);
        };
    }
}
