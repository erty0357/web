package backend.repository;

import backend.entity.ClubMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClubMemberRepository extends JpaRepository<ClubMember, Long> {
    List<ClubMember> findByClubName(String clubName);
    boolean existsByClubNameAndUserId(String clubName, String userId);
    void deleteByClubNameAndUserId(String clubName, String userId);
}