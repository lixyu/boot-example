package cn.lee.example.exception;

/**
 * @author：lix492 @date: 2021/7/7
 */

public class BusinessException extends RuntimeException{

    public BusinessException(String message) {
        super(message);
    }
}
