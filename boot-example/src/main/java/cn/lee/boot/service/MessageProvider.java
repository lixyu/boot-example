package cn.lee.boot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author ：lix492
 * 2021/5/24
 */
@Service
@RequiredArgsConstructor
public class MessageProvider {

    private final StreamBridge streamBridge;

    public void sendHello() {
        String message = UUID.randomUUID().toString();

        streamBridge.send("sendHello-in-0", message);
        System.out.println("*************send message:" + message);
    }
}
