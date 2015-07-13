package com.controller;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.entities.Album;
import com.entities.Cart;
import com.entities.Genre;
import com.entities.Users;
import com.service.AlbumService;
import com.service.GenreService;
import com.service.ShoppingCartService;

@Controller
public class ShoppingCartController {
	static Logger logger = Logger.getLogger("Controller");
	
	@Autowired
	private AlbumService albumService;
	
	@Autowired
	private GenreService genreService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	/**
	 * Map the Shopping Cart page - GET
	 * @param deletedAlbumID
	 * @param request
	 * @param session
	 * @param model
	 */
	@RequestMapping(value="/ShoppingCart", method = RequestMethod.GET)
	public String ShoppingCartPage(@RequestParam(value = "deletedAlbumID", required = false) Integer deletedAlbumID, HttpServletRequest request, HttpSession session, ModelMap model) {
		
		List<Genre> genreList = genreService.getAll();
		model.put("genreList", genreList);
		
		// Format the Decimal number
		NumberFormat formatter = new DecimalFormat("#0.00");
		logger.debug("Just got Shopping Cart page GET");
		
		// Prepare needed variable and objects
		Album album = null;
		Cart cart = new Cart();
		
		String totalPrice = "";
		
		if (deletedAlbumID != null) 
		{
			// Prepare for removing album from cart
			Album removedAlbum = albumService.getAlbumById(deletedAlbumID);
			deleteThatAlbum(removedAlbum);
			model.addAttribute("removedAlbumInfo", removedAlbum.getTitle() + " has been removed from your shopping cart.");
		} 
		else 
		{
			// Prepare for creating new Cart.

			// Generate shopping cart ID
			shoppingCartService.generateCartId();
			
			// Find the album that user want to add into shopping cart
			int addedAlbumId = (Integer) request.getSession().getAttribute("addedAlbumId");
			album = albumService.getAlbumById(addedAlbumId);
			
			shoppingCartService.getAlbumsInCart().add(album);
		}
		
		// Format the price of the whole shopping cart
		totalPrice = formatter.format(shoppingCartService.getTotalPrice());
		
		// Add information into view
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("albumList", shoppingCartService.getAlbumsInCart());
		model.put("itemNumbers", shoppingCartService.getAlbumsInCart().size());
		model.addAttribute("myCart", cart);
		
		return "/ShoppingCart/ShoppingCart";
	}
	
	/**
	 * Map the Shopping Cart page - POST
	 * @param deletedAlbumID
	 * @param myCart
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/ShoppingCart", method = RequestMethod.POST)
	public String ShoppingCartPage(@RequestParam(value = "deletedAlbumID", required = false) Integer deletedAlbumID, @ModelAttribute("myCart") Cart myCart, ModelMap model) {
		logger.debug("Just got Shopping Cart page POST");
		
		model.addAttribute("myCartId", shoppingCartService.getCurrentCartNumber());
		
		// Prepare information for login page
		Users users = new Users();
		model.addAttribute("login", users);
		return "login";
	}
	
	
	/**
	 * Remove an album from the shopping cart
	 * @return
	 */
	public void deleteThatAlbum(Album album) {
		shoppingCartService.getAlbumsInCart().remove(album);
	}
	
}
