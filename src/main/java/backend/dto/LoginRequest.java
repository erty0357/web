package backend.dto;

public class LoginRequest {
    private String username;
    private String password;

    // 기본 생성자 필수
    public LoginRequest() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
