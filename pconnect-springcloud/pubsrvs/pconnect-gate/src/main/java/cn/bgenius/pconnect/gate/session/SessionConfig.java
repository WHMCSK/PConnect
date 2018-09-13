package cn.bgenius.pconnect.gate.session;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.ConfigureRedisAction;

/**
 * Created by 金飞 on 2017/10/16.
 */
@Configuration
public class SessionConfig {
     @Bean
    public   ConfigureRedisAction configureRedisAction() {
        return ConfigureRedisAction.NO_OP;
    }
}
