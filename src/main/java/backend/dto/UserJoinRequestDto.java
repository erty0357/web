package backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserJoinRequestDto {

    @NotBlank
    private String name;

    @Pattern(regexp = "^[0-9]+$", message = "아이디는 숫자만 입력하세요.")
    private String userId;

    @NotBlank
    private String password;

    @Email
    private String email;
}

