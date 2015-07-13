package com.controller;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.entities.Album;
import com.entities.Cart;
import com.entities.Genre;
import com.entities.OrderDetail;
import com.entities.Orders;
import com.entities.Users;
import com.service.AlbumService;
import com.service.GenreService;
import com.service.OrderDetailService;
import com.service.OrdersService;
import com.service.ShoppingCartService;
import com.service.UsersService;

/**
 * Handles and retrieves the login or denied page depending on the URI template 
 *
 */
@Controller
@RequestMapping("/Account")
public class AccountController {
	
	protected static Logger logger = Logger.getLogger("Controller");
	
	@Autowired
	private AlbumService albumService;
	
	@Autowired
	private GenreService genreService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private OrdersService ordersService;
	
	@Autowired
	private OrderDetailService orderdetailService;
	
	/**
	 * Handles and retrieves the login JSP page
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(@RequestParam(value = "error", required = false) String error, ModelMap model) {
		logger.debug("Recevied request to show login page");
		
		Users users = new Users();
		
		model.addAttribute("login", users);
		return "login";
	}
	
	/**
	 * Login page - POST
	 * @param username
	 * @param password
	 * @param loginUser
	 * @param model
	 */
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String postLoginPage(@RequestParam(value = "error", required = false) boolean error, @ModelAttribute("login") Users loginUser, ModelMap model) {
		
		logger.debug("User want to login");
		
		// Use this one for storing return page
		String destination = "";
		
		// 
		boolean checkLoginUser = false;
		
		checkLoginUser = usersService.verifyLoginUser(loginUser.getUsersName(), loginUser.getPassWord());
		
		if (checkLoginUser == false) {
			// Assign an error message
			model.put("error", "You have entered an invalid username or password!");
			destination = "login";
		} else {
			
			int totalAlbum = shoppingCartService.getAlbumsInCart().size();
			
			Users user = usersService.findUserByUsername(loginUser.getUsersName());
			Orders order = ordersService.findOrdersByUserId(user.getUsersId());
			
			Date now = new Date();
			OrderDetail orderDetail = new OrderDetail();
			Album album = null;
			
			Cart cart = new Cart();
			List<Album> albumList = shoppingCartService.getAlbumsInCart();
			for (int i = 0; i < totalAlbum; i++) 
			{
				album = albumService.getAlbumById(albumList.get(i).getAlbumId());
				// Create order detail
				orderDetail.setOrderId(order);
				orderDetail.setAlbumId(album);
				orderDetail.setQuantity(1);
				orderDetail.setUnitPrice(album.getPrice());
				orderdetailService.create(orderDetail);
				
				// Create Cart
				cart.setCartId(shoppingCartService.getCurrentCartNumber().toString());
				cart.setAlbumId(albumList.get(i));
				cart.setCount(1);
				cart.setDateCreated(now);
				shoppingCartService.create(cart);
			}
			
			// Put information for CheckOut page
			model.put("error", "");
			model.put("myCartId", shoppingCartService.getCurrentCartNumber());
			model.put("itemNumbers", shoppingCartService.getAlbumsInCart().size());
			
			List<Genre> genreList = genreService.getAll();
			model.put("genreList", genreList);
			
			
			destination = "CheckOut";
		}
		
		return destination;
	}
	
}
