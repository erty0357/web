package backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "club_members")
public class ClubMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clubName;
    private String name;
    private String grade;
    private String major;
    private String phone;
    private LocalDateTime joinedAt = LocalDateTime.now();
    // ✅ 여기에 추가!
    private String userId;

    public ClubMember() {}

    public ClubMember(String clubName, String name, String grade, String major, String phone, String userId) {
        this.clubName = clubName;
        this.name = name;
        this.grade = grade;
        this.major = major;
        this.phone = phone;
        this.userId = userId;
        this.joinedAt = LocalDateTime.now();
    }
}
