package com.pago.pgo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;
import feign.RequestInterceptor;

@Configuration
public class FeignClientConfig {

	@Bean
	public Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}
	
	@Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            String token = SecurityContext.getToken();
            if (token != null) {
                requestTemplate.header("Authorization", token);
            }
        };
    }

}
