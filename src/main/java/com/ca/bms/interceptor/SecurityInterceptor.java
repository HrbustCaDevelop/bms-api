package com.ca.bms.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ca.bms.annotation.AuthPass;
import com.ca.bms.enumtype.UserStatusEnum;
import com.ca.bms.utils.RedisUtils;

/**
 * 登陆权限鉴定
 * 
 * @author：刘志龙
 * @since：2014年12月4日 下午2:31:14
 * @version:1.0
 */
public class SecurityInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		boolean flag = false;
		if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			AuthPass authPass = ((HandlerMethod) handler)
					.getMethodAnnotation(AuthPass.class);
			if (authPass == null || authPass.validate() == false)
				return true;
			else {
				Object username = request.getParameter("username");
				Object usertoken = request.getParameter("usertoken");
				if (username != null && usertoken != null) {
					String jusertoken = RedisUtils.get(username.toString());
					if (usertoken.equals(jusertoken)) {
						flag = true;
					}
				}
				if (flag) {
					return true;
				} else {
					response.setCharacterEncoding("UTF-8");
					response.setContentType("text/html");
					StringBuilder regMsg = new StringBuilder(
							"{\"returnmsg\":\"");
					regMsg.append(UserStatusEnum.UINI.getValue());
					regMsg.append("\"}");
					response.getWriter().write(regMsg.toString());
					return false;
				}
			}
		} else {
			return true;
		}
	}
}
