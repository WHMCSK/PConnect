package cn.bgenius.pconnect.dbcreate;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * ${DESCRIPTION}
 *
 * @author 金飞
 * @create 2017-05-25 12:44
 */
@SpringBootApplication
public class DBCreateBootstrap {
    public static void main(String[] args) {
        new SpringApplicationBuilder(DBCreateBootstrap.class).web(true).run(args);    }
}
