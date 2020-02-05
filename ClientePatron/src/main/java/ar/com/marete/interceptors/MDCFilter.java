package ar.com.marete.interceptors;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.MDC;

/**
 * Servlet Filter implementation class MDCFilter
 */
public class MDCFilter implements Filter {

    /**
     * Default constructor. 
     */
    public MDCFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		//MDC.put("sessionId",httpServletRequest.getSession().getId());
        Cookie[] cookies = httpServletRequest.getCookies();
        if(cookies!=null) {
        	MDC.put("sessionId",this.getCookieValue(cookies, "JSESSIONID"));
        	MDC.put("nombre", "mario");
        }else {
        	MDC.put("sessionId","-1");
        	MDC.put("nombre", "no user");
        }
        chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	
	private String getCookieValue(Cookie[] cookies, String nameCookie) {
		String jsessionidValue = null;
		for(Cookie cookie:cookies) {
			if(nameCookie.equals(cookie.getName())) {
				jsessionidValue =  cookie.getValue();
			}
		}
		return jsessionidValue;
	}

}
