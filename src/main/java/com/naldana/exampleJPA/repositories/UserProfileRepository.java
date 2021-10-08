package com.naldana.exampleJPA.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.naldana.exampleJPA.models.UserProfile;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long>{

}
