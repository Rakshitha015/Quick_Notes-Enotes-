package com.example.enotes_pro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.enotes_pro.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
    

    public boolean existsByEmail(String email);

    public User findByEmail(String email);
}
