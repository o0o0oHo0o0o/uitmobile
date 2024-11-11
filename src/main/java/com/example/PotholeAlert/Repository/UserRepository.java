package com.example.PotholeAlert.Repository;

import com.example.PotholeAlert.Entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<MyUser, Integer> {
    Optional<MyUser> findByEmail(String email); // Để tìm người dùng theo email
}
