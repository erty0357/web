package backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JoinRequestDto {
    private String clubName;
    private String name;
    private String grade;
    private String major;
    private String phone;
    private String motivation;
    private String userId; // ✅ 추가

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
}



