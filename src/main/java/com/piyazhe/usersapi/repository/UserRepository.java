package com.piyazhe.usersapi.repository;

import com.piyazhe.usersapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
