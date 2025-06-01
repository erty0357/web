package backend.controller;

import backend.dto.LoginRequest;
import backend.dto.LoginResponse;
import backend.entity.User;
import backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = {
    "http://localhost:5173",
    "https://web-vx1u.onrender.com",
    "https://yuclub-mxyqm30vm-kim-sang-hyuns-projects.vercel.app"
})
public class AuthController {

    private final UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        String userId = request.getUsername();
        String password = request.getPassword();

        // ✅ 관리자 계정: 하드코딩
        if ("admin".equals(userId) && "1234".equals(password)) {
            return ResponseEntity.ok(new LoginResponse("ADMIN"));
        }

        // ✅ 일반 사용자 로그인
        Optional<User> optionalUser = userRepository.findByUserId(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getPassword().equals(password)) {
                return ResponseEntity.ok(new LoginResponse(user.getRole())); // 대부분 MEMBER
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 로그인 실패
    }
}


