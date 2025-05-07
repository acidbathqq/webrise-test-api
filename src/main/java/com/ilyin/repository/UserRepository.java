package com.ilyin.repository;

import com.ilyin.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByName(String name);

    boolean existsByName(String name);
}