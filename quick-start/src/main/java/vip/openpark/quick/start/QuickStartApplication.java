package vip.openpark.quick.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 添加 JVM 参数，用于连接 sentinel 控制台
 * -Dcsp.sentinel.dashboard.server=192.168.0.100:8080
 *
 * @author anthony
 * @version 2024/10/9
 * @since 2024/10/9 21:17
 */
@SpringBootApplication
public class QuickStartApplication {
	public static void main(String[] args) {
		SpringApplication.run(QuickStartApplication.class, args);
	}
}