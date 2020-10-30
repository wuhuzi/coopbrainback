package cn.cocoding;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.cocoding.mapper")
public class CoopbrainbackApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoopbrainbackApplication.class, args);
    }

}
