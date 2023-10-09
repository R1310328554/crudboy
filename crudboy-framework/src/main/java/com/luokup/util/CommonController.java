//package com.luokup.util;// 这里之所以是 /lq , 而不是 /* ; 是因为 AntPathMatcher.combine 方法中进行合并时的处理, 导致 前一个 /* 丢失
//
//import cn.hutool.core.util.ReflectUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * <p> 直接以前端传递来的Serivce名+方法名去调用Service层的同名方法; Controller层不再需要写任何代码
// * <p> 例子
// * <pre>
// * 		前端: /lq/thirdService/queryTaskList.do
// * 		Service层相应的方法签名:  Object queryTaskList(Map<String, Object> parameterMap)
// * 		相应的Service注册到Spring容器中的id : thirdServiceService
// * </pre>
// * @author LQ
// *
// */
//@RestController
//@RequestMapping("/lq")
//public class CommonController {
//
//	private static final Logger LOG = LoggerFactory.getLogger(CommonController.class);
//
//	@PostMapping(value = "/{serviceName}/{serviceMethodName}")
//	public void common(@PathVariable String serviceName, @PathVariable final String serviceMethodName, HttpServletRequest request, HttpServletResponse response) {
//		// 收集前台传递来的参数, 并作预处理
//		final Map<String, String> parameterMap = HtmlUtils.getParameterMap(request);
//		final Map<String, Object> paramsCopy = preDealOutParam(parameterMap);
//
//		// 获取本次的调度服务名和相应的方法名
//		//final List<String> serviceAndMethod = parseServiceAndMethod(request);
//		//final String serviceName = serviceAndMethod.get(0) + "Service";
//		//final String serivceMethodName = serviceAndMethod.get(1);
//
//		// 直接使用Spring3.x新加入的@PathVariable注解; 代替上面的自定义操作
//		serviceName = serviceName + "Service";
//		final String fullServiceMethodName = StringUtil.format("{}.{}", serviceName, serivceMethodName);
//		// 输出日志, 方便回溯
//		LOG.debug("### current request method is [ {} ] ,  parameters is [ {} ]", fullServiceMethodName, parameterMap);
//
//		// 获取Spring中注册的Service Bean
//		final Object serviceBean = SpringBeanFactory.getBean(serviceName);
//		Object rv;
//		try {
//			// 调用Service层的方法
//			rv = ReflectUtil.invoke(serviceBean, serviceMethodName, paramsCopy);
//			// 若用户返回一个主动构建的FriendlyException
//			if (rv instanceof FriendlyException) {
//				rv = handlerException(fullServiceMethodName, (FriendlyException) rv);
//			} else {
//				rv = returnVal(rv);
//			}
//		} catch (Exception e) {
//			rv = handlerException(fullServiceMethodName, e);
//		}
//
//		LOG.debug("### current request method [ {} ] has dealed,  rv is [ {} ]", fullServiceMethodName, rv);
//		HtmlUtils.writerJson(response, rv);
//	}
//
//	/**
//	 * 解析出Service和相应的方法名
//	 * @param request
//	 * @return
//	 */
//	private List<String> parseServiceAndMethod(HttpServletRequest request) {
//		// /lq/thirdService/queryTaskList.do 解析出 [ thirdService, queryTaskList ]
//		final String serviceAndMethod = StringUtil.subBefore(request.getServletPath(), ".", false);
//		List<String> split = StringUtil.split(serviceAndMethod, '/', true, true);
//		return split.subList(1, split.size());
//	}
//
//	// 将传递来的JSON字符串转换为相应的Map, List等
//	private Map<String, Object> preDealOutParam(final Map<String, String> parameterMap) {
//		final Map<String, Object> outParams = new HashMap<String, Object>(parameterMap.size());
//		for (Map.Entry<String, String> entry : parameterMap.entrySet()) {
//			outParams.put(entry.getKey(), entry.getValue());
//		}
//
//		for (Map.Entry<String, Object> entry : outParams.entrySet()) {
//			final String value = (String) entry.getValue();
//			if (StringUtil.isEmpty(value)) {
//				entry.setValue("");
//				continue;
//			}
//
//			Object parsedObj = JSONUtil.tryParse(value);
//
//			// 不是JSON字符串格式
//			if (null == parsedObj) {
//				continue;
//			}
//
//			entry.setValue(parsedObj);
//		}
//
//		return outParams;
//	}
//
//	// 构建成功执行后的返回值
//	private Object returnVal(Object data) {
//		return MapUtil.newMapBuilder().put("data", data).put("status", 200).put("msg", "success").build();
//	}
//
//	// 构建执行失败后的返回值
//	private Object handlerException(String distributeMethod, Throwable e) {
//		final String logInfo = StringUtil.format("[ {} ] fail", distributeMethod);
//		LOG.error(logInfo, ExceptionUtil.getRootCause(e));
//
//		return MapUtil.newMapBuilder().put("data", "").put("status", 500)
//				.put("msg", ExceptionUtil.getRootCause(e).getMessage()).build();
//	}
//}
//
//
///**
// *
// *
// 到此为止，Controller层的代码就算是完成了。之后的开发工作中，在绝大多数情况下，我们将不再需要编写任何Controller层的代码。只要遵循如下的约定，前端将会直接调取到Service层的相应方法，并获取到约定格式的响应值。
//
// 前端请求路径 ： {{rootPath}}/lq/serviceName/serviceMethodName.do
//
// {{rootPath}} ： 访问地址的根路径
// lq ：自定义的固定名称，用于满足SpringMVC的映射规则。
// serviceName ： 用于获取Spring容器中的Service Bean。这里的规则是 该名称后附加上Service字符来作为Bean Id来从Spring容器中获取相应 Service Bean。
// serviceMethodName ： 第三步中找到的Service Bean中的名为serviceMethodName的方法。签名为Object serviceMethodName(Map<String,Object> param)。
// 5. 特殊需求
// 对于有额外需要的特殊Controller，可以完全按照之前的Controller层写法。没有任何额外需要注意的地方。
// 6. 完善
// 上面的Service层的方法签名中，其参数使用的是固定的Map<String,Object> param。对Map和Bean的争论由来已久，经久不衰，这里不搅和这趟浑水。
// ————————————————
// 版权声明：本文为CSDN博主「夫礼者」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
// 原文链接：https://blog.csdn.net/lqzkcx3/article/details/80454826
//
// *
// */