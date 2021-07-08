package cn.lee.example.config;

import cn.lee.example.mq.RabbitChannel;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Configuration;

/**
 * @author：lix492 @date: 2021/7/7
 */
@Configuration
@EnableBinding({Processor.class,RabbitChannel.class})
public class RabbitConfig {
}
