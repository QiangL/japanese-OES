package org.ssdut.japanese.oes.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


import org.ssdut.japanese.oes.cons.*;
import org.apache.commons.lang.StringUtils;
import org.ssdut.japanese.oes.entity.User;


public class ForumFilter implements Filter {
	private static final String FILTERED_REQUEST = "@@session_context_filtered_request";
	// ① 不需要登录即可访问的URI资源
	private static final String[] INHERENT_ESCAPE_URIS = { "index.html","about.html","login.html",
		"register.html"
			 };
	// ② 执行过滤
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		 if (request.getCharacterEncoding() == null) {
	            request.setCharacterEncoding("UTF-8");
	        }
		// ②-1 保证该过滤器在一次请求中只被调用一次
		if (request != null && request.getAttribute(FILTERED_REQUEST) != null) {
			chain.doFilter(request, response);
		} else {

			// ②-2 设置过滤标识，防止一次请求多次过滤
			request.setAttribute(FILTERED_REQUEST, Boolean.TRUE);
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			User userContext = getSessionUser(httpRequest);
		
			// ②-3 用户未登录, 且当前URI资源需要登录才能访问
			if (userContext == null
					&& !isURILogin(httpRequest.getRequestURI(), httpRequest)) {
				//System.out.println(userContext);
				String toUrl = httpRequest.getRequestURL().toString();
				if (!StringUtils.isEmpty(httpRequest.getQueryString())) {
					toUrl += "?" + httpRequest.getQueryString();
				}
				// ②-4将用户的请求URL保存在session中，用于登录成功之后，跳到目标URL
				httpRequest.getSession().setAttribute(CommonConstant.LOGIN_TO_URL, toUrl);

				// ②-5转发到登录页面
				request.getRequestDispatcher("/login.html").forward(request,
						response);
				return;
			}
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
   /**
    * 当前URI资源是否需要登录才能访问
    * @param requestURI
    * @param request
    * @return
    */
	private boolean isURILogin(String requestURI, HttpServletRequest request) {
		if (request.getContextPath().equalsIgnoreCase(requestURI)
				|| (request.getContextPath() + "/").equalsIgnoreCase(requestURI))
			return true;
		for (String uri : INHERENT_ESCAPE_URIS) {
			if (requestURI != null && requestURI.indexOf(uri) >= 0) {
				return true;
			}
		}
		return false;
	}

	protected User getSessionUser(HttpServletRequest request) {
		return (User) request.getSession().getAttribute(CommonConstant.USER_CONTEXT);
	}

	@Override
	public void destroy() {

	}
}
