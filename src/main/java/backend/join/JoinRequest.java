package backend.join;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "join_requests")
public class JoinRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔽 여기에 추가
    @Column(name = "user_id")
    private String userId;

    private String clubName;
    private String name;
    private String grade;
    private String major;
    private String phone;

    @Column(length = 50)
    private String motivation;

    // ✅ 추가할 getter/setter
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    private LocalDateTime createdAt = LocalDateTime.now();
}

