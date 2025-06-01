package backend.controller;

import backend.join.JoinRequest;
import backend.repository.JoinRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationController {

    private final JoinRequestRepository joinRequestRepository;

    @GetMapping("/unread")
    public List<String> getJoinRequestsAsNotifications(@RequestParam String club) {
        return joinRequestRepository.findByClubName(club).stream()
                .map(req -> req.getName() + "님이 가입 신청서를 보냈습니다!")
                .toList();
    }
}

