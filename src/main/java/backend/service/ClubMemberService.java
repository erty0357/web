package backend.service;

import backend.entity.ClubMember;
import backend.repository.ClubMemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClubMemberService {

    private final ClubMemberRepository clubMemberRepository;

    public ClubMemberService(ClubMemberRepository clubMemberRepository) {
        this.clubMemberRepository = clubMemberRepository;
    }

    public void addMember(ClubMember member) {
        clubMemberRepository.save(member);
    }

    @Transactional // ✅ 이거 추가!!
    public void removeMember(String clubName, String userId) {
        clubMemberRepository.deleteByClubNameAndUserId(clubName, userId);
    }

    public List<ClubMember> getMembers(String clubName) {
        return clubMemberRepository.findByClubName(clubName);
    }
}
