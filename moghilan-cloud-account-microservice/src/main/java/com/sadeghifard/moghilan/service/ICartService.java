package com.sadeghifard.moghilan.service;


import com.sadeghifard.moghilan.exception.ResourceAlreadyReportedException;
import com.sadeghifard.moghilan.exception.ResourceNotAcceptableException;
import com.sadeghifard.moghilan.exception.ResourceNotFoundException;
import com.sadeghifard.moghilan.model.Cart;


public interface ICartService {
	Iterable<Cart> getAllCarts() throws ResourceNotFoundException;
	Cart getCartById(Long id) throws ResourceNotFoundException;
	Cart getCartByCartNumber(Long cartNumber) throws ResourceNotFoundException;
	Cart saveCart(Cart cart) throws ResourceAlreadyReportedException;
	Cart updateCart(Cart cart) throws ResourceNotAcceptableException;
	Cart updateCartById(Cart cart, Long id) throws ResourceNotFoundException;
	Cart updateCartByCartNumber(Cart cart, Long cartNumber) throws ResourceNotFoundException;
	String deleteCart(Cart cart) throws ResourceNotFoundException;
	String deleteByCartNumber(Long cartNumber) throws ResourceNotFoundException;
	String deleteById(Long id) throws ResourceNotFoundException;
}
