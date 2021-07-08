package cn.lee.example.dao;

import cn.lee.example.mapper.InventoryMapper;
import cn.lee.example.entity.Inventory;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author：lix492 @date: 2021/6/29
 */
@Service
public class InventoryDao extends ServiceImpl<InventoryMapper, Inventory> {

}
