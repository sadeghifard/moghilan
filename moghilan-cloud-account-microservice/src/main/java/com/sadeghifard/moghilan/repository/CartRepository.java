package com.sadeghifard.moghilan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sadeghifard.moghilan.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{

}
