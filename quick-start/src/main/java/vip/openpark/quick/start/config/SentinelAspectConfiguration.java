package vip.openpark.quick.start.config;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author anthony
 * @version 2024/10/9
 * @since 2024/10/9 22:05
 */
@Configuration
public class SentinelAspectConfiguration {
	@Bean
	public SentinelResourceAspect sentinelResourceAspect() {
		return new SentinelResourceAspect();
	}
}