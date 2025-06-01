package backend.service;

import backend.dto.UserJoinRequestDto;
import backend.repository.UserRepository;
import backend.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void join(UserJoinRequestDto dto) {
        if (userRepository.existsByUserId(dto.getUserId())) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        User user = new User();
        user.setName(dto.getName());
        user.setUserId(dto.getUserId());
        user.setPassword(dto.getPassword()); // ⚠️ 나중에 암호화 필요
        user.setEmail(dto.getEmail());

        userRepository.save(user);
    }
}

