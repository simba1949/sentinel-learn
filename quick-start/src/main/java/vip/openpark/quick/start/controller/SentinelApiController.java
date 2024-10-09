package vip.openpark.quick.start.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * sentinel api 实践
 * 1. 初始化控制流量规则（可选，可以在 sentinel 控制台中配置）；
 * 2. 调用 SphU.entry() 方法，进行流控
 *
 * @author anthony
 * @version 2024/10/9
 * @since 2024/10/9 21:21
 */
@Slf4j
@RestController
@RequestMapping(value = "api")
public class SentinelApiController {
	// 第一步：初始化控制流量规则（可选，可以在 sentinel 控制台中配置）；
	static {
		List<FlowRule> rules = new ArrayList<>();

		FlowRule rule = new FlowRule();
		rule.setResource("vip.openpark.quick.start.controller.SentinelApiController.hello");
		// 流量控制类型，0: thread count, 1: QPS
		rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
		// Set limit QPS to 2.
		rule.setCount(2);

		rules.add(rule);
		FlowRuleManager.loadRules(rules);
	}

	@GetMapping("hello")
	public String hello(@RequestParam(name = "name", required = false) String name) {
		// 1.5.0 版本开始可以直接利用 try-with-resources 特性
		// 第二步：调用 SphU.entry() 方法，进行流控
		try (Entry entry = SphU.entry("vip.openpark.quick.start.controller.SentinelApiController.hello")) {
			// 被保护的逻辑
			log.info("获取的客户端请求的数据是：{}", name);
			return "【" + name + "】吟诵：不经一番寒彻骨，怎得梅花扑鼻香。";
		} catch (BlockException ex) {
			// 处理被流控的逻辑
			log.info("blockHandler，入参：{}，异常信息：{}", name, ex.getMessage(), ex);
			return "blockHandler";
		} catch (Throwable ex) {
			log.info("fallback，入参：{}，异常信息：{}", name, ex.getMessage(), ex);
			return "fallback";
		}
	}
}