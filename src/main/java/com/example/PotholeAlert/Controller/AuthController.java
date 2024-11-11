package com.example.PotholeAlert.Controller;

import com.example.PotholeAlert.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    // Endpoint đăng nhập
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> loginRequest) throws AuthenticationException {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");
        String token = userService.login(email, password);
        return ResponseEntity.ok("Bearer " + token);
    }
}
