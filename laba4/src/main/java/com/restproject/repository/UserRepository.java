package com.restproject.repository;

import com.restproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;


@RestResource(exported = false)
public interface UserRepository extends JpaRepository<User, String> {

}
