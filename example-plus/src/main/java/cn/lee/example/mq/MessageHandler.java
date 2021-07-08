package cn.lee.example.mq;

import cn.lee.example.dto.BookOrderDTO;
import cn.lee.example.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.mica.core.utils.$;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @author：lix492 @date: 2021/7/7
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MessageHandler {

    private final InventoryService inventoryService;

    @StreamListener(RabbitChannel.BOOK_ORDER_INPUT)
    public void listenBookMessage(Message<BookOrderDTO> message){
        BookOrderDTO payload=message.getPayload();
        log.info("MQ接收到下单消息体:{},下一步进行异步下单操作",$.toJson(payload));
        log.info("下单成功,减库存");
        inventoryService.updateInventory(payload.getGoodId(),payload.getCount());

    }

//    @StreamListener(RabbitChannel.BOOK_ORDER_OUTPUT)
//    public void test(String message){
//        log.info("MQ接收到下单消息体:{}",message);
//        log.info("test下单成功,减库存");
//
//    }
}
