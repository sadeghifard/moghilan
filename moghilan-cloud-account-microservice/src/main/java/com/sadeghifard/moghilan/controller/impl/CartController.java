package com.sadeghifard.moghilan.controller.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sadeghifard.moghilan.controller.ICartController;
import com.sadeghifard.moghilan.model.Cart;
import com.sadeghifard.moghilan.service.impl.CartService;

import lombok.AllArgsConstructor;

@CrossOrigin
@AllArgsConstructor
@RestController
public class CartController implements ICartController {

	private final CartService cartService;

	@Override
	@GetMapping("/cart")
	public ResponseEntity<Iterable<Cart>> getAllCarts (){
		List<Cart> carts = (List<Cart>) cartService.getAllCarts();
		carts = carts.stream().filter(c -> c != null).toList();
		if(carts != null && !carts.isEmpty()) {
			return  new ResponseEntity<Iterable<Cart>>(carts, HttpStatus.OK);
		} else {
			return new ResponseEntity<Iterable<Cart>>(carts, HttpStatus.NO_CONTENT);
		}
	}
	
	@Override
	@GetMapping("/cart/{id}")
	public ResponseEntity<Cart> getCartById (@PathVariable Long id){
		Cart existCart = cartService.getCartById(id);
		if(existCart != null) {
			return new ResponseEntity<Cart>(existCart, HttpStatus.OK);
		} else {
			return new ResponseEntity<Cart>(existCart, HttpStatus.NO_CONTENT);
		}
	}
	
	@Override
	@GetMapping("/cart/{cartNumber}")
	public ResponseEntity<Cart> getCartByCartNumber(@PathVariable Long cartNumber) {
		Cart existCart = cartService.getCartByCartNumber(cartNumber);
		if(existCart != null) {
			return new ResponseEntity<Cart>(existCart, HttpStatus.OK);
		} else {
			return new ResponseEntity<Cart>(existCart, HttpStatus.NO_CONTENT);
		}
	}
	
	@Override
	@PostMapping("/cart")
	public ResponseEntity<Cart> saveCart (@RequestBody Cart cart){
			Cart existCart = cartService.saveCart(cart);
			if(existCart != null) {
				return new ResponseEntity<Cart>(existCart, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<Cart>(existCart, HttpStatus.NO_CONTENT);
			}
	}
	
	@Override
	@PutMapping("/cart")
	public ResponseEntity<Cart> updateCart(@RequestBody Cart cart) {
			Cart existCart = cartService.updateCart(cart);
			if(existCart != null) {
				return new ResponseEntity<Cart>(existCart, HttpStatus.OK);
			} else {
				return new ResponseEntity<Cart>(existCart, HttpStatus.NO_CONTENT);
			}
	}

	@Override
	@PutMapping("/cart/{id}")
	public ResponseEntity<Cart> updateCartById(Cart cart, Long id) {
			Cart existCart = cartService.updateCartById(cart, id);
			if(existCart != null) {
				return new ResponseEntity<Cart>(existCart, HttpStatus.OK);
			} else {
				return new ResponseEntity<Cart>(existCart, HttpStatus.NO_CONTENT);
			}
	}

	@Override
	@PutMapping("/cart/{cartNumber}")
	public ResponseEntity<Cart> updateCartByCartNumber(Cart cart, Long cartNumber) {
			Cart existCart = cartService.updateCartById(cart, cartNumber);
			if(existCart != null) {
				return new ResponseEntity<Cart>(existCart, HttpStatus.OK);
			} else {
				return new ResponseEntity<Cart>(existCart, HttpStatus.NO_CONTENT);
			}
	}
	
	@Override
	@DeleteMapping("/cart")
	public ResponseEntity<String> deleteCart (@RequestBody Cart cart){
		String massage = cartService.deleteCart(cart);
		return new ResponseEntity<String>(massage, HttpStatus.OK);
	}
	
	@Override
	@DeleteMapping("/cart/{cartNumber}")
	public ResponseEntity<String> deleteByCartNumber(@PathVariable Long cartNumber){
		String massage = cartService.deleteByCartNumber(cartNumber);
		return new ResponseEntity<String>(massage, HttpStatus.OK);
	}
	
	@Override
	@DeleteMapping("/cart/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id){
		String massage = cartService.deleteById(id);
		return new ResponseEntity<String>(massage, HttpStatus.OK);
	}
}
