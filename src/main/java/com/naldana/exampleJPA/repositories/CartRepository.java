package com.naldana.exampleJPA.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.naldana.exampleJPA.models.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long>{
	
	
}
