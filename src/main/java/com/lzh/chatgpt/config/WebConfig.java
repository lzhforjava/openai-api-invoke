package com.lzh.chatgpt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author：LZH
 * @Package：com.lzh.chatgpt.config
 * @Project：chat-gpt
 * @name：WebMvcConfigurer
 * @Date：2023/3/20 19:20
 * @Filename：WebMvcConfigurer
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        spring.mvc.view.prefix + /index + spring.mvc.view.suffix 构成完整的请求地址
//        访问项目根目录即是访问 /views/index.html页面
        registry.addViewController("/").setViewName("/index");
    }
}


