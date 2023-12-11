package com.example.enotes_pro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.enotes_pro.entity.Notes;
import com.example.enotes_pro.entity.User;

@Repository
public interface NotesRepository extends JpaRepository<Notes,Integer>
 {
        public List<Notes> findByUser(User user);
    
} 