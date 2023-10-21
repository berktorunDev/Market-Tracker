package com.boilerplate.project.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boilerplate.project.model.User;

public interface UserRepository extends JpaRepository<User, UUID> {

}
