package com.sadeghifard.moghilan.controller;

import org.springframework.http.ResponseEntity;

import com.sadeghifard.moghilan.model.Cart;

public interface ICartController {
	ResponseEntity<Iterable<Cart>> getAllCarts();
	ResponseEntity<Cart> getCartById(Long id);
	ResponseEntity<Cart> getCartByCartNumber(Long cartNumber);
	ResponseEntity<Cart> saveCart(Cart cart);
	ResponseEntity<Cart> updateCart(Cart cart);
	ResponseEntity<Cart> updateCartById(Cart cart, Long id);
	ResponseEntity<Cart> updateCartByCartNumber(Cart cart, Long cartNumber);
	ResponseEntity<String> deleteCart(Cart cart);
	ResponseEntity<String> deleteByCartNumber(Long cartNumber);
	ResponseEntity<String> deleteById(Long id);
}
