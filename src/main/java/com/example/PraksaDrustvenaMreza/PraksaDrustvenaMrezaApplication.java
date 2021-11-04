package com.example.PraksaDrustvenaMreza;

import io.swagger.models.HttpMethod;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SpringBootApplication
@EnableSwagger2
public class PraksaDrustvenaMrezaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PraksaDrustvenaMrezaApplication.class, args);
	}

	@Component
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public class MyCorsFilterConfig implements Filter {

		@Override
		public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
			final HttpServletResponse response = (HttpServletResponse) res;
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
			response.setHeader("Access-Control-Allow-Headers", "X-Auth-Token, Content-Type, enctype, Authorization");
			response.setHeader("Access-Control-Max-Age", "3600");
			if (HttpMethod.OPTIONS.name().equalsIgnoreCase(((HttpServletRequest) req).getMethod())) {
				response.setStatus(HttpServletResponse.SC_OK);
			} else {
				chain.doFilter(req, res);
			}
		}

		@Override
		public void destroy() {
		}

		@Override
		public void init(FilterConfig config) throws ServletException {
		}
	}
}
