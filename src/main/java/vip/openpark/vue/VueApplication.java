package vip.openpark.vue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author anthony
 * @version 2024/2/21 17:29
 */
@MapperScan("vip.openpark.vue.mapper")
@SpringBootApplication
public class VueApplication {
	public static void main(String[] args) {
		SpringApplication.run(VueApplication.class, args);
	}
}