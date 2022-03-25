package springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springcloud.interceptor.LogInterceptor;

@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    @Bean
    public HandlerInterceptor getMyInterceptor(){
        return new LogInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(getMyInterceptor())
                .addPathPatterns("/**").excludePathPatterns("/","/favicon.ico","/error","/css/**","/fonts/**","/images/**",
                "/js/**","/aa/**"); ;
    }
}
