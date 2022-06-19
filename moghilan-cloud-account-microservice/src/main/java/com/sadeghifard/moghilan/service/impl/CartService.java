package com.sadeghifard.moghilan.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.sadeghifard.moghilan.exception.ResourceException;
import com.sadeghifard.moghilan.model.Cart;
import com.sadeghifard.moghilan.repository.CartRepository;
import com.sadeghifard.moghilan.service.ICartService;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class CartService implements ICartService{
	
	private final CartRepository cartRepository;

	@Override
	public Iterable<Cart> getAllCarts() {
		try {
			return cartRepository.findAll();
		} catch (Exception e) {
			throw new ResourceException("Cart", "Get All", null);
		}
	}

	@Override
	public Cart getCartById(Long id) {
		return cartRepository.findById(id)
				.orElseThrow(()-> new ResourceException("Cart","Cart ID", id));
	}

	@Override
	public Cart getCartByCartNumber(Long cartNumber) {
		return cartRepository.findByCartNumber(cartNumber)
				.orElseThrow(()-> new ResourceException("Cart","Cart Number", cartNumber));
	}
	
	@Override
	public Cart saveCart(Cart cart) {
		try {
			cart.setCreateDate(LocalDateTime.now());
			return cartRepository.save(cart);
		}catch (Exception e) {
			throw new ResourceException("Cart", "Save Cart", cart);
		}
	}

	@Override
	public Cart updateCart(Cart cart) {
		try {
			cart.setModifyDate(LocalDateTime.now());
			return cartRepository.save(cart);
		} catch (Exception e) {
			throw new ResourceException("Cart", "Update Cart", cart);
		}
	}

	@Override
	public Cart updateCartById(Cart cart, Long id) {
			Cart existCart = getCartById(id);
			if(existCart != null) {
				cart.setModifyDate(LocalDateTime.now());
				existCart = cartRepository.save(cart);
			}
			return existCart;
	}

	@Override
	public Cart updateCartByCartNumber(Cart cart, Long cartNumber) {
		Cart existCart = getCartByCartNumber(cartNumber);
		if(existCart != null) {
			cart.setModifyDate(LocalDateTime.now());
			existCart = cartRepository.save(cart);
		}
		return existCart;
	}
	
	@Override
	public String deleteCart(Cart cart) {
		cartRepository.delete(cart);
		return "Cart deleted successfully";
	}

	@Override
	public String deleteByCartNumber(Long cartNumber) {
		Cart existCart = getCartByCartNumber(cartNumber);
		if(existCart != null) {
			cartRepository.deleteByCartNumber(cartNumber);
		}
		return "Cart deleted successfully by Cart number = " + cartNumber;
	}

	@Override
	public String deleteById(Long id) {
		Cart existCart = getCartById(id);
		if(existCart != null) {
			cartRepository.deleteById(id);
		}
		return "Cart deleted successfully by Cart ID = " + id;
	}
}
