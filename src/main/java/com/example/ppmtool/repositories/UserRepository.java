package com.example.ppmtool.repositories;

import com.example.ppmtool.domain.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    
}
