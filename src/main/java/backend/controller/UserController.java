package backend.controller;

import backend.dto.UserJoinRequestDto;
import backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody @Valid UserJoinRequestDto dto) {
        userService.join(dto);
        return ResponseEntity.ok("회원가입 완료");
    }
}
