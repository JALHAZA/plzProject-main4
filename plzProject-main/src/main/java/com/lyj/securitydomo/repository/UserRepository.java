package com.lyj.securitydomo.repository;

import com.lyj.securitydomo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    //select * from tbl_user where username="abcd";
}