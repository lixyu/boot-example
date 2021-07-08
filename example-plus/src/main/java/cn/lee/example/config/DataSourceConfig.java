package cn.lee.example.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author：lix492 @date: 2021/6/29
 */
@Configuration
@EnableTransactionManagement
@MapperScan("cn.lee.example.mapper")
public class DataSourceConfig {
}
