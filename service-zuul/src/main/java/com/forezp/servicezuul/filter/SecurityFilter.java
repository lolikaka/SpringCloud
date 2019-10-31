package com.forezp.servicezuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Title: SecurityFilter
 * @Description: zuul过滤器
 * @Author: ccg
 * @Date: 2019/6/20 18:00
 */
@Component
public class SecurityFilter extends ZuulFilter {
    @Override
    public String filterType() {
        //filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型
        //pre路由之前
        //routing：路由之时
        // post： 路由之后
        // error：发送错误调用
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //true表示拦截
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //获取Request与Response接口对象
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String securityToken = request.getParameter("token");
        if (securityToken == null) {
            ctx.setSendZuulResponse(false);
            //状态码
            ctx.setResponseStatusCode(401);
            try {
                //返回响应
                ctx.getResponse().getWriter().write("request failure , you do not have security token ");
            } catch (Exception e) {
            }
        }
        //return null 表示直接越过此Filter
        return null;
    }
}
