package vip.openpark.facade;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author anthony
 * @version 2024/10/8
 * @since 2024/10/8 20:10
 */
@FeignClient("spring-cloud-provider")
public interface IHelloFacade {

	@GetMapping("hello")
	String hello(@RequestParam(name = "name", required = false) String name);
}