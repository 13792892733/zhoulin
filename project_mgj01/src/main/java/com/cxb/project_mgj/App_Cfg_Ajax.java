package com.cxb.project_mgj;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class App_Cfg_Ajax {


	/**
	 * 允许 其他应用 通过ajax 实现跨域 请求获取数据, 并 支持 会话 唯一性（session 稳定）
	 * @return
	 */
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() { 
        	
            @Override
            public void addCorsMappings(CorsRegistry registry) {
            	
                registry.addMapping("/**")
                          .allowedOrigins("*")  
                          .allowCredentials(true)
                          .allowedMethods("GET", "POST", "DELETE", "PUT","PATCH")
                          .maxAge(3600);  
            }
        };
    }
	
	
}
