package backend.controller;

import backend.entity.ClubMember;
import backend.service.ClubMemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import backend.repository.ClubMemberRepository;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


@RestController
@RequestMapping("/api/clubs")
public class ClubMemberController {

    private final ClubMemberService clubMemberService;
    private final ClubMemberRepository clubMemberRepository; // ✅ 추가

    // ✅ 생성자 주입
    public ClubMemberController(ClubMemberService clubMemberService,
                                ClubMemberRepository clubMemberRepository) {
        this.clubMemberService = clubMemberService;
        this.clubMemberRepository = clubMemberRepository;
    }

    @GetMapping("/{clubName}/members")
    public ResponseEntity<List<ClubMember>> getMembersByClub(@PathVariable String clubName) {
        List<ClubMember> members = clubMemberService.getMembers(clubName);
        return ResponseEntity.ok(members);
    }

    @GetMapping
    public List<ClubMember> getMembers(@PathVariable String clubName) {
        return clubMemberService.getMembers(clubName);
    }

    @GetMapping("/{clubName}/members/status")
    public ResponseEntity<?> checkMembershipStatus(
            @PathVariable String clubName,
            @RequestParam String userId // userId = "user"
    ) {
        boolean isMember = clubMemberRepository.existsByClubNameAndUserId(clubName, userId);
        return ResponseEntity.ok(Map.of("isMember", isMember));
    }

    @DeleteMapping("/clubmembers")
    public ResponseEntity<Map<String, String>> deleteMember(
            @RequestParam String clubName,
            @RequestParam String userId
    ) {
        try {
            clubMemberService.removeMember(clubName, userId); // ✅ 서비스 통해 삭제 (트랜잭션 포함)
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.err.println("❌ 삭제 중 에러 발생: " + e.getMessage());
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("error", "서버 내부 오류");
            return ResponseEntity.status(500).body(errorMap);
        }
    }
}
