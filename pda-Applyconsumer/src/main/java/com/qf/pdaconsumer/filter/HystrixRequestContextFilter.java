package com.qf.pdaconsumer.filter;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author ：lh
 * @date ：Created in 2022/3/10 13:31
 */
//缓存过滤器：创建请求缓存
    @WebFilter( "/*")
public class HystrixRequestContextFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        Filter.super.init(filterConfig);

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //        初始化Hystrix请求上下文，请求缓存才生效

        HystrixRequestContext.initializeContext();
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
