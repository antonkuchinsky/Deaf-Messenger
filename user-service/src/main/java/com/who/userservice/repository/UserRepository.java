package com.who.userservice.repository;

import com.who.userservice.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {

    @Query("select u from User u where  u.id = :id")
    Optional<User> getUserByID(@Param("id") UUID id);


}
