package com.example.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpHeaders;
import java.util.Base64;
import java.util.Enumeration;
import java.util.Iterator;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@WebFilter(urlPatterns = "/v1/employes/*")
public class DeleteFilter implements Filter {
	private static final Logger log =org.slf4j.LoggerFactory.getLogger(DeleteFilter.class);
	private static final String AUTH_API_KEY=Base64.getEncoder().encodeToString(new String("adminuser:adminpassword").getBytes());
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
			HttpServletRequest req=(HttpServletRequest) request;
			String method = req.getMethod();
			if(method.equals("DELETE")) {
				log.info("the request is deleted");
				String authValue = req.getHeader(org.springframework.http.HttpHeaders.AUTHORIZATION);
				authValue=authValue.substring(authValue.indexOf(" ")+1,authValue.length());
				log.info("auth value"+authValue+" "+AUTH_API_KEY+" equals "+authValue.equals(AUTH_API_KEY));
				if(authValue.equals(AUTH_API_KEY)) {
					chain.doFilter(request, response);
				}else {
					HttpServletResponse res=(HttpServletResponse)response;
					PrintWriter writer = res.getWriter();
					res.sendError(HttpServletResponse.SC_BAD_REQUEST," you are not autenticated");
					writer.flush();
				}
			}else {
				log.info("method "+method+" is called");
				chain.doFilter(request, response);

			}
	}
}
