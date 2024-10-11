package vip.openpark.quick.start.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 热点参数限流
 *
 * @author anthony
 * @version 2024/10/11
 * @since 2024/10/11 20:37
 */
@Slf4j
@RestController
public class SentinelHotParamController {

	/**
	 * 热点参数限流
	 *
	 * @param p1 参数1，在 sentinel 参数索引中是 0
	 * @param p2 参数2，在 sentinel 参数索引中是 1
	 * @return String
	 */
	@GetMapping("hotParam")
	@SentinelResource(value = "hotParam", blockHandler = "blockHandler")
	public String hotParam(@RequestParam(name = "p1", required = false) String p1,
	                       @RequestParam(name = "p2", required = false) String p2) {
		log.info("获取的客户端请求的数据是：p1:{}, p2:{}", p1, p2);
		return "不经一番寒彻骨，怎得梅花扑鼻香。";
	}

	public String blockHandler(String p1, String p2, BlockException blockException) {
		log.info("热点参数限流，被限流了");
		return "热点参数限流，被限流了";
	}
}