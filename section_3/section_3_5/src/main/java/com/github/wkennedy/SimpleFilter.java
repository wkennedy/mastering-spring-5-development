//package com.github.wkennedy;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import java.io.IOException;
//
//@WebFilter("/*")
//public class SimpleFilter implements Filter {
//
//    private static Logger log = LoggerFactory.getLogger(SimpleFilter.class);
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        log.info("In SimpleFilter " + request.getServerName());
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
