package vip.openpark.quick.start.component;


import com.alibaba.csp.sentinel.adapter.spring.webmvc_v6x.callback.RequestOriginParser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

/**
 * @author anthony
 * @version 2024/10/11
 * @since 2024/10/11 21:09
 */
@Component
public class AppRequestOriginParser implements RequestOriginParser {
	@Override
	public String parseOrigin(HttpServletRequest request) {
		// 根据请求来源的 app 进行限流
		return request.getParameter("app");
	}
}