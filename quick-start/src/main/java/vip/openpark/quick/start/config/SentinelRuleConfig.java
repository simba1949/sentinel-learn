package vip.openpark.quick.start.config;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 当 Spring 容器初始化完成后,
 * Spring 会遍历所有实现 CommandLineRunner 接口的类, 并运行其 run() 方法.
 *
 * @author anthony
 * @version 2024/10/9
 * @since 2024/10/9 22:26
 */
@Component
public class SentinelRuleConfig implements CommandLineRunner {
	@Override
	public void run(String... args) throws Exception {
		List<FlowRule> rules = new ArrayList<>();

		FlowRule rule = new FlowRule();
		rule.setResource("vip.openpark.quick.start.controller.SentinelAnnotationController.hello");
		// 流量控制类型，0: thread count, 1: QPS
		rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
		// Set limit QPS to 2.
		rule.setCount(2);

		rules.add(rule);
		FlowRuleManager.loadRules(rules);
	}
}