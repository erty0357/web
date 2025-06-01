package backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 모든 엔드포인트
                .allowedOrigins(
                        "http://localhost:5173",              // 로컬 개발 환경
                        "https://web-vx1u.onrender.com", // 백엔드 배포 서버
                        "https://yuclub-git-main-kim-sang-hyuns-projects.vercel.app"
                        "https://yuclub-fc6vf11ge-kim-sang-hyuns-projects.vercel.app" // ✅ 프론트 Vercel 주소 명시적으로 추가
                )
                .allowedMethods("*") // GET, POST, PUT, DELETE 등 모두 허용
                .allowedHeaders("*"); // 모든 헤더 허용
    }
}

