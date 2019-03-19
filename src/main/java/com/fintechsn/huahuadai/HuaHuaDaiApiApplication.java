package com.fintechsn.huahuadai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.fintechsn.huahuadai.mapper")
@EnableConfigurationProperties
@EnableTransactionManagement
@EnableCaching
public class HuaHuaDaiApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(HuaHuaDaiApiApplication.class, args);
    }
}
