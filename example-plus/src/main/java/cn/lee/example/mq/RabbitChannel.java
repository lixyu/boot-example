package cn.lee.example.mq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

/**
 * @author：lix492 @date: 2021/7/7
 */
@Component
public interface RabbitChannel {

    String BOOK_ORDER_INPUT="bookOrderInput";
    String BOOK_ORDER_OUTPUT="bookOrderOutput";

    @Input(BOOK_ORDER_INPUT)
    SubscribableChannel bookOrderInput();

    @Output(BOOK_ORDER_OUTPUT)
    MessageChannel bookOrderOutput();

}
