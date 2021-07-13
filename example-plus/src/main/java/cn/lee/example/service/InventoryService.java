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
import net.dreamlu.mica.core.utils.NumberUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final DistributedLock distributedLock;
    private final RabbitChannel rabbitChannel;

    public Long save(Inventory inventory){
        inventoryDao.save(inventory);
        log.info("service log ASSIGN_ID:{}",inventory.getId());
        return inventory.getId();
    }

    @Transactional
    public void updateInventory(Long goodId,Integer count){
        Wrapper<Inventory> wrapper=new QueryWrapper(new Inventory(goodId));
        Inventory inventory=inventoryDao.findByGoodId(goodId);
        log.info("真正开始减库存-->{}",inventory.getNum()-count);
        inventory.setNum(inventory.getNum()-count);
        log.info("减库存结果-->{}",inventory.getNum());
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


    public synchronized Integer preLoadInventory(Long goodId){
        Wrapper<Inventory> queryWrapper= new QueryWrapper(new Inventory(goodId));

        Inventory inventory=inventoryDao.getOne(queryWrapper);

        String key=Constants.GOOD_INVENTORY_CACHE_PRE+goodId;
        stringRedisTemplate.opsForValue().set(key,String.valueOf(inventory.getNum()),1L, TimeUnit.DAYS);
        return inventory.getNum();
    }

    public  Long preReduceInventory(Long goodId,Integer count){
        String lockKey=null;
        Long num=0L;
        String key=Constants.GOOD_INVENTORY_CACHE_PRE+goodId;
        try {

            lockKey=distributedLock.tryLock(key);
            if(StringUtils.isBlank(lockKey)){
                //log.error("加锁失败");
                return num;
            }
            if(!stringRedisTemplate.hasKey(key)){
                this.preLoadInventory(goodId);
            }
            String current=stringRedisTemplate.opsForValue().get(key);
            log.info("商品:{},当前库存-->{}",goodId,current);
            num=NumberUtil.toLong(current);
            if(num<=0){
                //log.error("该商品已经售光了,当前库存:{}",num);
                return num;
            }

            Long after=stringRedisTemplate.opsForValue().decrement(key,count);
            if(after<0){
                //log.error("该商品已经售光了,减库存后:{}",after);
                return after;
            }
            //log.info("商品:{},预减库存-->{}",goodId,after);
            rabbitChannel.bookOrderOutput().send(MessageBuilder.withPayload(new BookOrderDTO(goodId,count)).build());

        }catch (Exception e){
            log.error("报错了",e);
        }finally {
            distributedLock.unLock(lockKey);
        }
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
