package vip.openpark.cloud.provider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vip.openpark.facade.IHelloFacade;

import java.time.LocalDateTime;

/**
 * @author anthony
 * @version 2024/10/8
 * @since 2024/10/8 20:28
 */
@Slf4j
@RestController
public class HelloController implements IHelloFacade {
	@Override
	@GetMapping("hello")
	public String hello(@RequestParam(name = "name", required = false) String name) {
		log.info("provider hello 入参：{}", name);
		return LocalDateTime.now() + " PROVIDER: Hello " + name;
	}
}