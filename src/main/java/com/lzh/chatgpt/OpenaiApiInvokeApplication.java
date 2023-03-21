package com.lzh.chatgpt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@SpringBootApplication
public class OpenaiApiInvokeApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenaiApiInvokeApplication.class, args);
    }

    //将ServerEndpointExporter 注册为一个spring的bean
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
