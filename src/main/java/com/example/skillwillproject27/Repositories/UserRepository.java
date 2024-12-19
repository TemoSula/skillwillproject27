package com.example.skillwillproject27.Repositories;

import com.example.skillwillproject27.Models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel,UUID> {


    @Query("select um from UserModel um where username = :username")
    public UserModel findByUsername(@Param("username") String username);

}
