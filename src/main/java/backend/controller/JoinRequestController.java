package backend.controller;

import backend.dto.NotificationMessage;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import backend.service.ClubMemberService;
import backend.dto.JoinRequestDto;
import backend.entity.ClubMember;
import backend.join.JoinRequest;
import backend.repository.JoinRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/join-requests")
public class JoinRequestController {

    private final JoinRequestRepository joinRequestRepository;
    private final SimpMessagingTemplate messagingTemplate; // ✅ 알림 전송 도구 주입
    private final ClubMemberService clubMemberService; // ✅ 회원 등록을 위한 서비스 주입


    @PostMapping
    public ResponseEntity<Void> createJoinRequest(@RequestBody JoinRequestDto dto) {
        System.out.println("📥 DTO 도착 확인:");
        System.out.println("- club: " + dto.getClubName());
        System.out.println("- name: " + dto.getName());
        System.out.println("- grade: " + dto.getGrade());
        System.out.println("- major: " + dto.getMajor());
        System.out.println("- phone: " + dto.getPhone());
        System.out.println("- motivation: " + dto.getMotivation());

        JoinRequest entity = new JoinRequest();
        entity.setClubName(dto.getClubName());
        entity.setName(dto.getName());
        entity.setGrade(dto.getGrade());
        entity.setMajor(dto.getMajor());
        entity.setPhone(dto.getPhone());
        entity.setMotivation(dto.getMotivation());
        entity.setUserId(dto.getUserId());


        joinRequestRepository.save(entity);

        // ✅ WebSocket 알림 전송
        String club = dto.getClubName();
        String normalizedClub = club.toLowerCase().replace(" ", "-");
        String message = dto.getName() + "님이 가입 신청서를 보냈습니다!";
        messagingTemplate.convertAndSend("/topic/notifications/" + normalizedClub, new NotificationMessage(club, message));

        return ResponseEntity.ok().build();
    }

    // ✅ 조회
    @GetMapping("/{clubName}")
    public ResponseEntity<List<JoinRequestDto>> getJoinRequestsByClub(@PathVariable String clubName) {
        List<JoinRequestDto> results = joinRequestRepository.findByClubName(clubName).stream()
                .map(entity -> {
                    System.out.println("📥 받은 clubName = " + clubName);
                    JoinRequestDto dto = new JoinRequestDto();
                    dto.setClubName(entity.getClubName());
                    dto.setName(entity.getName());
                    dto.setGrade(entity.getGrade());
                    dto.setMajor(entity.getMajor());
                    dto.setPhone(entity.getPhone());
                    dto.setMotivation(entity.getMotivation());
                    dto.setUserId(entity.getUserId()); // ✅ userId도 함께 전달
                    return dto;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(results);
    }
    // ✅ 가입 승인
    @PostMapping("/{clubName}/approve")
    public ResponseEntity<Void> approveJoinRequest(@PathVariable String clubName, @RequestBody JoinRequestDto dto) {
        ClubMember member = new ClubMember();
        member.setClubName(clubName);
        member.setName(dto.getName());
        member.setGrade(dto.getGrade());
        member.setMajor(dto.getMajor());
        member.setPhone(dto.getPhone());
        // ✅ 여기에 userId도 넣어줘야 함!
        member.setUserId(dto.getUserId()); // ✅ dto에서 바로 꺼냄

        clubMemberService.addMember(member); // ✅ 회원 DB 저장
        // ✅ 신청서 삭제용 joinRequest 조회 추가
        JoinRequest joinRequest = joinRequestRepository
                .findByClubNameAndName(clubName, dto.getName())
                .orElseThrow(() -> new RuntimeException("신청서를 찾을 수 없습니다."));

        joinRequestRepository.delete(joinRequest); // ✅ 이제 오류 안 남
        return ResponseEntity.ok().build();
    }
}

