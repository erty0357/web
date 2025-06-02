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
                        "https://yuclub-git-main-kim-sang-hyuns-projects.vercel.app",
                        "https://yuclub-a3mnq8f0n-kim-sang-hyuns-projects.vercel.app"
                )
                .allowedMethods("*")
                .allowedHeaders("*");
    }
}


