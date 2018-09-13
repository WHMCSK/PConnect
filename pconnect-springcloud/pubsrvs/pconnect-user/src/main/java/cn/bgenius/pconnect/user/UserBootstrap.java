package cn.bgenius.pconnect.user;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.session.data.redis.RedisFlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * ${DESCRIPTION}
 *
 * @author 金飞
 * @create 2017-05-25 12:44
 */
@SpringBootApplication
@ServletComponentScan("cn.bgenius.pconnect.user.config.druid")
@EnableEurekaClient
@EnableDiscoveryClient  //激活eureka中的DiscoveryClient实现
@EnableAsync
@EnableRedisHttpSession(redisFlushMode = RedisFlushMode.IMMEDIATE)
@EnableCaching
public class UserBootstrap {
    public static void main(String[] args) {
        new SpringApplicationBuilder(UserBootstrap.class).web(true).run(args);    }
}
 