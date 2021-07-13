package cn.lee.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dreamlu.mica.core.utils.$;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * redis分布式锁
 * @author：lix492 @date: 2021/7/12
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DistributedLock {

    private final StringRedisTemplate stringRedisTemplate;

    /**
     * 锁的超时时间 10s
     */
    int expireTime = 10 * 1000;

    /**
     * 锁等待，防止线程饥饿
     */
    int acquireTimeout  = 1 * 1000;

    private static String LOCK_PRE="lock_";


    public String tryLock(String key){
        try {
            // 获取锁的超时时间，超过这个时间则放弃获取锁

            long end = System.currentTimeMillis() + acquireTimeout;
            // 随机生成一个value
            String lockKey=LOCK_PRE+key;
            while (System.currentTimeMillis() < end) {
                Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey,lockKey,expireTime, TimeUnit.MILLISECONDS);
                if (result) {
                    return lockKey;
                }
            }
        } catch (Exception e) {
            log.error("try lock due to error", e);
        }

        return null;
    }

    public void unLock(String lockKey){
        if(StringUtils.isNotBlank(lockKey)){

            stringRedisTemplate.delete(lockKey);
        }
    }
}
