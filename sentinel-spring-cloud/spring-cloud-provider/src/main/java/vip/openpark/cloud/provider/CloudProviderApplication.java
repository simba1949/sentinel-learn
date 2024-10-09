package vip.openpark.cloud.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @author anthony
 * @version 2024/10/8
 * @since 2024/10/8 20:23
 */
@RefreshScope
@EnableDiscoveryClient
@SpringBootApplication
public class CloudProviderApplication {
	public static void main(String[] args) {
		SpringApplication.run(CloudProviderApplication.class, args);
	}
}