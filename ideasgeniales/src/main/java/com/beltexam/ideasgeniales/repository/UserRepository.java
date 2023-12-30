package com.beltexam.ideasgeniales.repository;

import org.springframework.data.repository.CrudRepository;

import com.beltexam.ideasgeniales.models.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);
}
