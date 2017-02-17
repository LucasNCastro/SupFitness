package com.supfitness.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Singh
 */
@WebFilter(filterName = "Auth", urlPatterns = {"/faces/raceDetail.xhtml", "/faces/edit.xhtml", "/faces/raceList.xhtml"})
public class Auth implements Filter {
    
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        Cookie[] cookies = httpRequest.getCookies();
        if(cookies != null && cookies.length > 0){
            
            for(Cookie cookie : cookies){
                
                if(cookie.getName().equals("username")) {
                    
                    chain.doFilter(request, response);
                    break;
                }
            }
        }else {
            
            httpResponse.sendRedirect("/faces/login.xhtml");
        }
        
    }

    
    /**
     * Destroy method for this filter
     */
    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {        
        
    }
 
}
