package vip.openpark.dubbo.provider.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import vip.openpark.dubbo.IHelloServiceFacade;

import java.time.LocalDateTime;

/**
 * @author anthony
 * @version 2024/10/9
 * @since 2024/10/9 22:57
 */
@Slf4j
@DubboService
public class HelloServiceImpl implements IHelloServiceFacade {
	@Override
	@SentinelResource(
		value = "vip.openpark.dubbo.IHelloServiceFacade.sayHello",
		blockHandler = "blockHandler",
		fallback = "fallback"
	)
	public String sayHello(String name) {
		return LocalDateTime.now() + " dubbo provider : hello " + name;
	}

	/**
	 * 限流逻辑
	 *
	 * @param name           名称
	 * @param blockException 限流异常
	 * @return 限流后的返回值
	 */
	public String blockHandler(String name, BlockException blockException) {
		log.info("blockHandler，入参：{}，异常信息：{}", name, blockException.getMessage(), blockException);
		return "blockHandler";
	}

	/**
	 * 异常逻辑
	 *
	 * @param name      名称
	 * @param throwable 异常
	 * @return 异常后的返回值
	 */
	public String fallback(String name, Throwable throwable) {
		log.info("fallback，入参：{}，异常信息：{}", name, throwable.getMessage(), throwable);
		return "fallback";
	}
}