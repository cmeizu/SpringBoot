package com.fintechsn.huahuadai.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Filename        RedisLockUtil.java
 * <p>
 * Description    分布式redis锁
 * Copyright     Copyright (c) 2016-2022 All Rights Reserved.
 * Company       fintechsn.com Inc Inc.
 *
 * @author XXX
 * @version 1.0
 * @date 2018/11/17 10:44
 */
@Service
public class RedisLockUtil {
    private final Logger log = LoggerFactory.getLogger(RedisLockUtil.class);

    /**
     * 分布式锁 key
     */
    public static final String DISTRIBUTED_LOCK_PREFIX = "distributed_redis_lock_";

    /**
     * 放款锁key
     */
    public static final String WITHDRAW_LOCK_PREFIX = "redis_withdraw_lock";

    /**
     * 还款锁key
     */
    public static final String WITHHOLD_LOCK_PREFIX = "redis_withhold_lock";


    public static final int WITHDRAW_LOCK_EXPIRE = 100;

    public static final int WITHHOLD_LOCK_EXPIRE = 100;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 分布式锁
     *
     * @param key
     * @param expire
     * @return
     */
    public boolean distributedLock(String key, int expire) {
        String lockKey = DISTRIBUTED_LOCK_PREFIX + key;
        if (!stringRedisTemplate.opsForValue().setIfAbsent(lockKey, key)) {
            log.info("<-- distributedLock setNX 失败 key：{}-->", key);
            return false;
        }
        if (!stringRedisTemplate.expire(lockKey, expire, TimeUnit.SECONDS)) {
            log.info("<-- distributedLock expire  key：{}-->", key);
            return false;
        }
        return true;
    }


    /**
     * 放款锁
     *
     * @param key
     * @param expire
     * @return
     */
    public boolean withdrawLock(String key, int expire) {
        String lockKey = WITHDRAW_LOCK_PREFIX + key;
        if (!stringRedisTemplate.opsForValue().setIfAbsent(lockKey, key)) {
            log.info("<-- withdrawLock setNX 失败-->");
            return false;
        }
        if (!stringRedisTemplate.expire(lockKey, expire, TimeUnit.SECONDS)) {
            log.info("<-- withdrawLock expire 失败-->");
            return false;
        }
        return true;
    }


    /**
     * 还款锁
     *
     * @param key
     * @param expire
     * @return
     */
    public boolean withholdLock(String key, int expire) {
        String lockKey = WITHHOLD_LOCK_PREFIX + key;
        if (!stringRedisTemplate.opsForValue().setIfAbsent(lockKey, key)) {
            log.info("<-- withholdLock setNX 失败-->");
            return false;
        }
        if (!stringRedisTemplate.expire(lockKey, expire, TimeUnit.SECONDS)) {
            log.info("<-- withholdLock expire 失败-->");
            return false;
        }
        return true;
    }

}
