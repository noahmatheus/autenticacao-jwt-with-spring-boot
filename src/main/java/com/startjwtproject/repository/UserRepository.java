package com.startjwtproject.repository;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.startjwtproject.model.User;

@Repository
public class UserRepository extends GenericRepository<User, UUID> {

}
