package com.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entities.Album;
import com.entities.Cart;
import com.service.ShoppingCartService;

@Service("shoppingCartService")
public class ShoppingCartServiceImpl implements ShoppingCartService {

	protected static Logger logger = Logger.getLogger("Service");
	
	private Integer currentCartNumber = 0;
	public List<Album> albumsInCart = new ArrayList<Album>();
	
	// Injected database connection
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * create an cart object
	 * @param cart
	 */
	@Transactional
	public void create(Cart cart) {
		em.merge(cart);
	}
	
	@Transactional
	public void delete(Cart cart) {
		em.remove(cart);
	}
	
	@SuppressWarnings("unchecked")
	public List<Cart> getCartById(Integer cartId) {
		Query q = em.createNamedQuery("Cart.findByCartId");
		q.setParameter("cartId", cartId);
		List<Cart> cartList = q.getResultList();
		return cartList;
	}
	
	/**
	 * Generate CartId
	 * @return
	 */
	public void generateCartId() {
		if (currentCartNumber == 0) {
			Random rand = new Random();
			currentCartNumber = rand.nextInt(999999999);
		}
		
	}

	/**
	 * Get total price
	 * @return
	 */
	public double getTotalPrice() {
		double price = 0;
		List<Album> albumList = getAlbumsInCart();
		for (Album album : albumList) {
			price += album.getPrice();
		}
		return price;
	}
	
	public void deleteAlbumFromAlbumList(Album album) {
		logger.info("removeAlbum : " + album.getTitle());
		
		getAlbumsInCart().remove(album);
		
		logger.info("After remove : " + getAlbumsInCart());
	}
	
	public Integer getCurrentCartNumber() {
		return currentCartNumber;
	}

	public void setCurrentCartNumber(Integer currentCartNumber) {
		this.currentCartNumber = currentCartNumber;
	}

	/**
	 * get all of albums in cart
	 * @return
	 */
	public List<Album> getAlbumsInCart() {
		return albumsInCart;
	}

	/**
	 * set all of album in cart
	 */
	public void setAlbumsInCart(List<Album> albumsInCart) {
		this.albumsInCart = albumsInCart;
	}
	
	
	
}
