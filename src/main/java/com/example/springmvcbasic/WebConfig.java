package com.example.springmvcbasic;

import com.example.springmvcbasic.web.argumentresolver.LoginMemberArgumentResolver;
import com.example.springmvcbasic.web.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginMemberArgumentResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1) // 첫번째 체인을 뜻한다.
                .addPathPatterns("/**") // 모든 경로에 대해서 로그인 체크를 한다.
                .excludePathPatterns("/", "/members/add", "/login",
                        "/logout", "/css/**", "/*.ico", "/error"); // 하지만, 여기 경로들은 로그인 체크를 하지 않는 것이다. 즉 프리패스.

        // excludePathPatterns() 포함된 것들은 new LoginCheckInterceptor() 호출조차 하지 않는다.
    }

}
