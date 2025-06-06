package backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "http://localhost:5173",              
                        "https://web-vx1u.onrender.com", 
                        "https://yuclub.vercel.app",
                        "https://yuclub-git-main-kim-sang-hyuns-projects.vercel.app",
                        "https://yuclub-e4lohltyo-kim-sang-hyuns-projects.vercel.app"
                )
                .allowedMethods("*")
                .allowedHeaders("*");
    }
}


