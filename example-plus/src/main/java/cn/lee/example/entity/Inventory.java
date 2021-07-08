package cn.lee.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author：lix492 @date: 2021/6/29
 */
@Data
@NoArgsConstructor
@TableName("tb_inventory")
@Accessors(chain = true)
public class Inventory {
    @TableId
    private Long id;
    private Long goodId;
    private Integer num;

    public Inventory(Long goodId, Integer num) {
        this.goodId = goodId;
        this.num = num;
    }

    public Inventory(Long goodId) {
        this.goodId = goodId;
    }
}
