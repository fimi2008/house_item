package top.lionxxw.house;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * spring boot 启动类
 * @author  Shaun Wang
 */
@SpringBootApplication
@MapperScan("classpath:top/lionxxw/house/dao/impl/*.xml")
public class HouseItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HouseItemApplication.class, args);
	}
}
