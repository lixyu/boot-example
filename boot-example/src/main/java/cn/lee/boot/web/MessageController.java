package cn.lee.boot.web;

import cn.lee.boot.service.MessageProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：lix492
 * 2021/5/24
 */
@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageProvider messageProvider;

    @GetMapping("/send/hello")
    public ResponseEntity<String> sendHello() {

        messageProvider.sendHello();
        return ResponseEntity.ok("LEE");
    }
}
