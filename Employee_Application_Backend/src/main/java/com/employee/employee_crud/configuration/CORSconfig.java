package com.employee.employee_crud.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CORSconfig implements WebMvcConfigurer {


    @Override
    public void addCorsMappings(CorsRegistry registry) {
                        registry.addMapping("/**")
                        .allowedOrigins("http://localhost:5173/iiitb/Employee")
//                        .allowCredentials(true)
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*");
            }

    }

