package cn.bgenius.pconnect.todo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.session.data.redis.RedisFlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


@SpringBootApplication
@ServletComponentScan("cn.bgenius.pconnect.todo.config.druid")
@EnableEurekaClient
@EnableDiscoveryClient  //激活eureka中的DiscoveryClient实现
@EnableAsync
@EnableRedisHttpSession(redisFlushMode = RedisFlushMode.IMMEDIATE)
@EnableCaching
public class TodoBootstrap {
    public static void main(String[] args) {
        new SpringApplicationBuilder(TodoBootstrap.class).web(true).run(args);    }
}
