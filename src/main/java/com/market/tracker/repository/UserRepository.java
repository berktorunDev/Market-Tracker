package com.market.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.market.tracker.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
