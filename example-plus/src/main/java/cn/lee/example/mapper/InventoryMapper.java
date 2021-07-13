package cn.lee.example.mapper;

import cn.lee.example.entity.Inventory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author：lix492 @date: 2021/6/29
 */
public interface InventoryMapper extends BaseMapper<Inventory> {

    Inventory findByGoodId(@Param("goodId") Long goodId);
}
