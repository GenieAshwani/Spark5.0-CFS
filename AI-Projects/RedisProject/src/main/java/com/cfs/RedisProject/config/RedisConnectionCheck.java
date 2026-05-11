package com.cfs.RedisProject.config;

import com.cfs.RedisProject.service.StudentService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Component;

@Component
public class RedisConnectionCheck {

    private static final Logger log= LoggerFactory.getLogger(RedisConnectionCheck.class);


    private final RedisConnectionFactory connectionFactory;

    public RedisConnectionCheck(RedisConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @PostConstruct
    public void checkConnection()
    {
        log.info("================================================");
        log.info("Checking Redis connection...");
        try {
            String pong=connectionFactory.getConnection().ping();
            if("PONG".equals(pong))
            {
                log.info("✅ Redis Connection Successfully!");
            }
        }
        catch (Exception e)
        {
            log.error("❌ Redis Connection Failed");
        }
    }


}
