package vip.openpark.cloud.consumer.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vip.openpark.facade.IHelloFacade;

/**
 * @author anthony
 * @version 2024/10/8
 * @since 2024/10/8 20:45
 */
@Slf4j
@RestController
public class ConsumerHelloController {
	@Resource
	private IHelloFacade helloFacade;

	@GetMapping(value = "hello")
	public String hello(@RequestParam(name = "name", required = false) String name) {
		return helloFacade.hello(name);
	}

	@GetMapping(value = "hello2")
	public String hello2(@RequestParam(name = "name", required = false) String name) {
		return helloFacade.hello(name) + "  2";
	}
}