package com.yogafire.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * CORS(Cross-origin resource sharing) 관련 컨트롤러측 설정 역할을 하는 클래스입니다.
 */
@Configuration
public class CorsMvcConfig implements WebMvcConfigurer {
    
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        
        corsRegistry.addMapping("/**")
        .allowedOriginPatterns("http://localhost:5173") // '*' 대신 패턴 사용
//      .allowedMethods("GET", "POST", "PUT", "DELETE")   
        .allowCredentials(true);
    }
}
