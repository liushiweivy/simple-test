package com.example.estest.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.alibaba.fastjson.JSONObject;
import com.example.estest.config.LogAnnoDTO;
import com.example.estest.config.anno.SystemControllerLog;
import com.example.estest.config.anno.SystemServiceLog;
import com.example.estest.dto.ApiLogDto;
import com.example.estest.service.LogService;

/**
 * Title: SystemControllerLog
 * @version V1.0
 * Description: 切点类
 * @date 2018年8月31日
 */
@Aspect
@Component
@SuppressWarnings("all")
public class SystemLogAspect {
	//注入Service用于把日志保存数据库，实际项目入库采用队列做异步
	//本地异常日志记录对象
	private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);

	@Autowired
	private LogService logService;

	//Service层切点
	@Pointcut("@annotation(com.example.estest.config.anno.SystemServiceLog)")
	public void serviceAspect() {
	}

	//Controller层切点
	@Pointcut("@annotation(com.example.estest.config.anno.SystemControllerLog)")
	public void controllerAspect() {
	}

	/**
	 * @Description 前置通知  用于拦截Controller层记录用户的操作
	 * @date 2018年9月3日 10:38
	 */
	@Around("controllerAspect()")
	public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("11111");
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		StringBuffer requestURL = request.getRequestURL();
		String queryString = request.getQueryString();
		ApiLogDto apiLogDto = new ApiLogDto();
		apiLogDto.setRequestJson(queryString);
		apiLogDto.setUrl(requestURL.toString());
		Object proceed = joinPoint.proceed();
		apiLogDto.setResponseJson(JSONObject.toJSONString(proceed));
		logService.anaLog(apiLogDto);
		return proceed;
	}


	/**
	 * @Description 获取注解中对方法的描述信息 用于service层注解
	 * @date 2018年9月3日 下午5:05
	 */
	public static String getServiceMethodDescription(JoinPoint joinPoint) throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					description = method.getAnnotation(SystemServiceLog.class).description();
					break;
				}
			}
		}
		return description;
	}


	/**
	 * @Description 获取注解中对方法的描述信息 用于Controller层注解
	 * @date 2018年9月3日 上午12:01
	 */
	public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();//目标方法名
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					description = method.getAnnotation(SystemControllerLog.class).description();
					break;
				}
			}
		}
		return description;
	}

	public static SystemControllerLog getLogAnnoDTO(JoinPoint joinPoint) throws Exception {
		LogAnnoDTO logAnnoDTO = new LogAnnoDTO();
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();//目标方法名
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					SystemControllerLog annotation = method.getAnnotation(SystemControllerLog.class);
					return annotation;
				}
			}
		}
		return null;
	}
}