package backend.repository;
import java.util.Optional;

import backend.join.JoinRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;


import java.util.List;


public interface JoinRequestRepository extends JpaRepository<JoinRequest, Long> {
    List<JoinRequest> findByClubName(String clubName);

    // 새로 추가할 메서드 (승인용)
    Optional<JoinRequest> findByClubNameAndName(String clubName, String name);

    @Modifying
    @Transactional
    void deleteByClubNameAndName(String clubName, String name);
}
