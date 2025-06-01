package backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/clubs")
public class ClubController {

    @GetMapping("/{clubName}")
    public ResponseEntity<?> getClubExistCheck(@PathVariable String clubName) {
        // 단순히 존재 여부만 확인하고 "OK" 메시지 반환 (예: 프론트 존재 확인용 용도)
        return ResponseEntity.ok(Map.of("message", clubName + " 페이지 존재 확인"));
    }
}

