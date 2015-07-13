package com.service;

import java.util.List;

import com.entities.Album;
import com.entities.Cart;


public interface ShoppingCartService {

	void create(Cart cart);
	
	void delete(Cart cart);
	
	List<Cart> getCartById(Integer cartId);
	
	void generateCartId();

	double getTotalPrice();
	
	void deleteAlbumFromAlbumList(Album album);
	
	List<Album> getAlbumsInCart();
	
	Integer getCurrentCartNumber();
}
