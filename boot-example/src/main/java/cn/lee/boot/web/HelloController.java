package cn.lee.boot.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：lix492
 * 2021/4/27
 */
@Slf4j
@RestController
public class HelloController {

    @GetMapping("/hello/{world}")
    public ResponseEntity<String> hello(@PathVariable String world) {
        String str = "Hello," + world;
        return ResponseEntity.ok(str);

    }
}
