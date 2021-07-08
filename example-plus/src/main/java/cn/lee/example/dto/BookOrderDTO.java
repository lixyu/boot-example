package cn.lee.example.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author：lix492 @date: 2021/7/7
 */
@Data
@NoArgsConstructor
public class BookOrderDTO {
    private Long goodId;
    private Integer count;

    public BookOrderDTO(Long goodId, Integer count) {
        this.goodId = goodId;
        this.count = count;
    }
}
