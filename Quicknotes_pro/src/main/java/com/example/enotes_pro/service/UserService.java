package com.example.enotes_pro.service;

import com.example.enotes_pro.entity.User;


public interface UserService {

    public User saveUser(User user);

    public boolean existEmailCheck(String email);





}


