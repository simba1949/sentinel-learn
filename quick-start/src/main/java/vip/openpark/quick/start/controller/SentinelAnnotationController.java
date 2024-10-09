package vip.openpark.quick.start.controller;

import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * sentinel annotation 实践
 *
 * @author anthony
 * @version 2024/10/9
 * @since 2024/10/9 21:21
 */
@Slf4j
@RestController
@RequestMapping(value = "annotation")
public class SentinelAnnotationController {
	/**
	 * 定义资源
	 *
	 * @param name 名称
	 * @return String
	 */
	@SentinelResource(
		// 资源名称
		value = "vip.openpark.quick.start.controller.SentinelAnnotationController.hello",
		// entry 类型，可选项（默认为 EntryType.OUT）
		entryType = EntryType.OUT,
		//  blockHandler 对应处理 BlockException 的函数名称，必须是 public，
		//  且返回类型需要与原方法相匹配，
		//  参数类型需要和原方法相匹配并且最后加一个额外的参数，类型为 BlockException
		blockHandler = "blockHandler",
		// fallback 用于在抛出异常的时候提供 fallback 处理逻辑，
		// 返回值类型必须与原函数返回值类型一致；
		// 方法参数列表需要和原函数一致，或者可以额外多一个 Throwable 类型的参数用于接收对应的异常。
		fallback = "fallback"
	)
	@GetMapping("hello")
	public String hello(@RequestParam(name = "name", required = false) String name) {
		log.info("获取的客户端请求的数据是：{}", name);
		return "【" + name + "】吟诵：不经一番寒彻骨，怎得梅花扑鼻香。";
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