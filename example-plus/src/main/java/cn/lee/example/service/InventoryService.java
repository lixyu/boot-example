package cn.lee.example.service;

import cn.lee.example.config.Constants;
import cn.lee.example.dao.InventoryDao;
import cn.lee.example.dto.BookOrderDTO;
import cn.lee.example.entity.Inventory;
import cn.lee.example.exception.BusinessException;
import cn.lee.example.mq.RabbitChannel;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.integration.core.Pausable;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author：lix492 @date: 2021/7/2
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryDao inventoryDao;
    private final StringRedisTemplate stringRedisTemplate;
    private final RabbitChannel rabbitChannel;

    public Long save(Inventory inventory){
        inventoryDao.save(inventory);
        log.info("service log ASSIGN_ID:{}",inventory.getId());
        return inventory.getId();
    }

    public void updateInventory(Long goodId,Integer count){
        Wrapper<Inventory> wrapper=new QueryWrapper(new Inventory(goodId));
        Inventory inventory=inventoryDao.getOne(wrapper);
        inventory.setNum(inventory.getNum()-count);
        inventoryDao.updateById(inventory);
    }

    //@Cacheable是spring自带缓存,不支持过期设置,需要调用@CacheEvient来针对性的,清除
    //值在内容中,不支持分布式(多节点),这是个问题
    @Cacheable("select-all")
    public List<Inventory> selectAll(){
        log.info("查询库存所有数据");
        return inventoryDao.list();
    }
    //
    @CacheEvict("select-all")
    public void removeCache(){

    }

    @Cacheable(value = "inventory#5m",key = "#id")
    public Inventory findById(Long id){
        log.info("find inventory by id={}",id);
        Inventory inventory=inventoryDao.getById(id);
        return inventory;
    }


    public Integer preLoadInventory(Long goodId){
        Wrapper<Inventory> queryWrapper= new QueryWrapper(new Inventory(goodId));

        Inventory inventory=inventoryDao.getOne(queryWrapper);

        String key=Constants.GOOD_INVENTORY_CACHE_PRE+goodId;
        stringRedisTemplate.opsForValue().set(key,String.valueOf(inventory.getNum()),1L, TimeUnit.DAYS);
        return inventory.getNum();
    }

    public Long preReduceInventory(Long goodId,Integer count){
        String key=Constants.GOOD_INVENTORY_CACHE_PRE+goodId;
        String current=stringRedisTemplate.opsForValue().get(key);
        log.info("商品:{},当前库存-->{}",goodId,current);
        Long num=stringRedisTemplate.opsForValue().decrement(key,count);
        if(num<=0){
            throw new BusinessException("该商品已经售光了");
        }
        stringRedisTemplate.opsForValue().set(key,String.valueOf(num));
        log.info("商品:{},预减库存-->{}",goodId,num);
        rabbitChannel.bookOrderOutput().send(MessageBuilder.withPayload(new BookOrderDTO(goodId,count)).build());
        return num;
    }

    public Long addInventory(Long goodId,Long count){
        String key=Constants.GOOD_INVENTORY_CACHE_PRE+goodId;
        if(stringRedisTemplate.hasKey(key)){
            stringRedisTemplate.opsForValue().increment(key,count);
        }
        log.info("商品:{},归还库存-->{}",goodId,count);
        return count;
    }


}
