package com.example.PotholeAlert.Service;

import com.example.PotholeAlert.Entity.MyUser;
import com.example.PotholeAlert.Repository.UserRepository;
import com.example.PotholeAlert.JWT.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import javax.naming.AuthenticationException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public MyUser registerUser(MyUser user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email đã tồn tại");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public String login(String email, String password) throws AuthenticationException {
        Optional<MyUser> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent() && passwordEncoder.matches(password, userOptional.get().getPassword())) {
            return jwtUtil.generateToken(email);
        }
        throw new AuthenticationException("Thông tin đăng nhập không hợp lệ");
    }
}
