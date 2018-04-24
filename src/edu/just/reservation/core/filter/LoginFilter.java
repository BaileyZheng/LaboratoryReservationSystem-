package edu.just.reservation.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import edu.just.reservation.core.constant.Constant;
import edu.just.reservation.core.permission.PermissionCheck;
import edu.just.reservation.manage.user.entity.User;

public class LoginFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String uri = request.getRequestURI();
		if (!uri.contains("sys/login_")) {
			User user = (User) request.getSession().getAttribute(Constant.USER);
			if (user != null) {
				if (uri.contains("/manage/")) {
					WebApplicationContext applicationContext = WebApplicationContextUtils
							.getWebApplicationContext(request.getSession()
									.getServletContext());
					PermissionCheck pc = (PermissionCheck) applicationContext
							.getBean("permissionCheck");
					if (pc.isAccessible(user, "sjgl")) {
						chain.doFilter(request, response);
					} else {
						response.sendRedirect(request.getContextPath()
								+ "/sys/login_toNoPermissionUI.action");
					}
				} else if (uri.contains("/yyxx/")) {
					WebApplicationContext applicationContext = WebApplicationContextUtils
							.getWebApplicationContext(request.getSession()
									.getServletContext());
					PermissionCheck pc = (PermissionCheck) applicationContext
							.getBean("permissionCheck");
					if (pc.isAccessible(user, "yyxx")) {
						chain.doFilter(request, response);
					} else {
						response.sendRedirect(request.getContextPath()
								+ "/sys/login_toNoPermissionUI.action");
					}
				} else if (uri.contains("/grxx/")) {
					WebApplicationContext applicationContext = WebApplicationContextUtils
							.getWebApplicationContext(request.getSession()
									.getServletContext());
					PermissionCheck pc = (PermissionCheck) applicationContext
							.getBean("permissionCheck");
					if (pc.isAccessible(user, "grxx")) {
						chain.doFilter(request, response);
					} else {
						response.sendRedirect(request.getContextPath()
								+ "/sys/login_toNoPermissionUI.action");
					}
				} else if(uri.contains("/skgl/")){
					WebApplicationContext applicationContext = WebApplicationContextUtils
							.getWebApplicationContext(request.getSession()
									.getServletContext());
					PermissionCheck pc = (PermissionCheck) applicationContext
							.getBean("permissionCheck");
					if (pc.isAccessible(user, "skgl")) {
						chain.doFilter(request, response);
					} else {
						response.sendRedirect(request.getContextPath()
								+ "/sys/login_toNoPermissionUI.action");
					}
				}else {
					chain.doFilter(request, response);
				}
			} else {
				if (uri.contains("sys") || uri.contains("xtsy")||uri.contains("tjbb")
						|| uri.contains("cgzs")||uri.contains("xwgg")) {
					chain.doFilter(request, response);
				} else {
					response.sendRedirect(request.getContextPath()
							+ "/sys/login_toLoginUI.action");
				}
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
