package com.simple.oracletest.config;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import com.simple.oracletest.entity.Person;

/**
 * @author simple
 * @date 2021/5/27 22:03
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI();
		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();
		String contextPath = context.getContextPath();
		 uri = StringUtils.remove(uri, contextPath + "/");
		String[] authUri =new String[] {"index"};
		if (isAuthUri(uri,authUri)) {
			Person person = (Person) session.getAttribute("user");
			if (person == null) {
				response.sendRedirect("login");
				return false;
			}
		}
		return true;
	}

	private boolean isAuthUri(String uri,String[] a) {
		for (String string : a) {
			if (StringUtils.startsWith(uri, string)) {
				return true;
			}
		}
		return false;
	}

}
