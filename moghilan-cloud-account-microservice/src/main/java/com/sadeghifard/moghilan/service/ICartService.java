package com.sadeghifard.moghilan.service;


import com.sadeghifard.moghilan.model.Cart;


public interface ICartService {
	Iterable<Cart> getAllCarts();
	Cart getCartById(Long id);
	Cart getCartByCartNumber(Long cartNumber);
	Cart saveCart(Cart cart);
	Cart updateCart(Cart cart);
	Cart updateCartById(Cart cart, Long id);
	Cart updateCartByCartNumber(Cart cart, Long cartNumber);
	String deleteCart(Cart cart);
	String deleteByCartNumber(Long cartNumber);
	String deleteById(Long id);
}
