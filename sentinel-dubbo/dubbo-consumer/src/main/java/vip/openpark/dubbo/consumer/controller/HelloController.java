package vip.openpark.dubbo.consumer.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vip.openpark.dubbo.IHelloServiceFacade;

/**
 * @author anthony
 * @version 2024/10/9
 * @since 2024/10/9 23:04
 */
@Slf4j
@RestController
public class HelloController {
	@DubboReference
	private IHelloServiceFacade helloServiceFacade;

	@GetMapping("hello")
	public String sayHello(@RequestParam(name = "name", required = false) String name) {
		return helloServiceFacade.sayHello(name);
	}
}