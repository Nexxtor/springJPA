package com.naldana.exampleJPA.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.naldana.exampleJPA.models.Items;

@Repository
public interface ItemsRepository extends JpaRepository<Items,Long>{

}
