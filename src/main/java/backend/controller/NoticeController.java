package backend.controller;

import backend.entity.Notice;
import backend.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/notices")
@CrossOrigin(origins = "http://localhost:5173") // 프론트엔드 주소와 맞추기
public class NoticeController {

    @Autowired
    private NoticeRepository noticeRepository;

    // 특정 동아리의 모든 공지사항 조회
    @GetMapping("/{clubName}")
    public List<Notice> getNotices(@PathVariable String clubName) {
        System.out.println("✅ Controller에서 받은 clubName = [" + clubName + "] / length = " + clubName.length());
        return noticeRepository.findByClubNameIgnoreCase(clubName.trim());

    }

    // 특정 공지사항 상세 조회
    @GetMapping("/{clubName}/{id}")
    public Notice getNoticeDetail(@PathVariable String clubName, @PathVariable Long id) {
        return noticeRepository.findById(id)
                .filter(notice -> notice.getClubName().equals(clubName))
                .orElseThrow(() -> new RuntimeException("Not Found"));
    }

    // 공지사항 등록
    @PostMapping
    public Notice createNotice(@RequestBody Notice notice) {
        System.out.println("📥 공지 등록 요청 도착: " + notice);  // ← 로그 추가

        return noticeRepository.save(notice);
    }

    // 공지사항 수정 (필요 시 추가)
    @PutMapping("/{id}")
    public Notice updateNotice(@PathVariable Long id, @RequestBody Notice updatedNotice) {
        Notice notice = noticeRepository.findById(id).orElseThrow();
        notice.setTitle(updatedNotice.getTitle());
        notice.setContent(updatedNotice.getContent());
        return noticeRepository.save(notice);
    }

    // 공지사항 삭제 (필요 시 추가)
    @DeleteMapping("/{id}")
    public void deleteNotice(@PathVariable Long id) {
        noticeRepository.deleteById(id);
    }
}
