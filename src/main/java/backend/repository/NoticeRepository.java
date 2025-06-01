package backend.repository;

import backend.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    List<Notice> findByClubName(String clubName);
    List<Notice> findByClubNameIgnoreCase(String clubName);
}
