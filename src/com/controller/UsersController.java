package com.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.entities.Album;
import com.entities.Genre;
import com.entities.Orders;
import com.entities.Users;
import com.service.AlbumService;
import com.service.GenreService;
import com.service.OrdersService;
import com.service.ShoppingCartService;
import com.service.UsersRoleService;
import com.service.UsersService;

@Controller
public class UsersController {

	protected static Logger logger = Logger.getLogger("Controller");
	
	@Autowired
	private AlbumService albumService = null;
	
	@Autowired
	private GenreService genreService = null;
	
	@Autowired
	private ShoppingCartService shoppingCartService = null;
	
	@Autowired
	private UsersService usersService = null;
	
	@Autowired
	private UsersRoleService usersroleService = null;
	
	@Autowired
	private OrdersService ordersService = null;
	
	/**
	 * Map create user page - GET method 
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public ModelAndView addUserPage(HttpServletRequest request) {
		
		return new ModelAndView("addUser", "usersModel", usersService);
	}
	
	/**
	 * Map create user page - POST method
	 * 
	 * @
	 */
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public ModelAndView addUserPage(@RequestParam(value = "error", required = false) boolean error, ModelMap model, HttpServletRequest request) {
		
		Users user = new Users();
		String Usersname, Password, Firstname, Lastname;
		int UserRole = 2;
		
		// Create new User account
		
		// Get information of an user object from the view page
		Usersname = request.getParameter("txtUsername");
		Password = request.getParameter("txtPassword");
		Firstname = request.getParameter("txtFirstname");
		Lastname = request.getParameter("txtLastname");
		
		if (checkUsername(Usersname)) {
			
			// put information into the new user object
			user.setFirstName(Firstname);
			user.setLastName(Lastname);
			user.setPassWord(Password);
			user.setUsersName(Usersname);
			user.setUsersRole(usersroleService.findUsersroleByUserroleId(UserRole));
			
			usersService.create(user);
			model.put("error", "");
			
			// Create new shipping information - It is Orders object
			
			// Get information of an order object from the view page
			Orders order = new Orders();
			
			String address = request.getParameter("txtAddress");
			String city = request.getParameter("txtCity");
			String state = request.getParameter("txtState");
			String postal = request.getParameter("txtPostalCode");
			String country = request.getParameter("txtCountry");
			String phone = request.getParameter("txtPhone");
			String email = request.getParameter("txtEmail");
			Date today = new Date();
			double total = 0.00;
			
			// Put information into the new order object
			order.setAddress(address);
			order.setCity(city);
			order.setState(state);
			order.setPostalCode(postal);
			order.setCountry(country);
			order.setPhone(phone);
			order.setEmail(email);
			order.setOrderDate(today);
			order.setTotal(total);
			order.setUsersId(user);
			
			ordersService.create(order);
			
			return new ModelAndView("/Store/Index", "usersModel", usersService);
		}
		else 
		{
			model.put("error", "Please choose another username. This username has been in used.");
		}
		
		return new ModelAndView("addUser", "usersModel", usersService);
	}
	
	/**
	 * Check if entered username is exist or not
	 *
	 */
	public boolean checkUsername(String passUsername) {
		boolean result = true;
		
		for (Users user : usersService.findAllUsers()) {
			if(user.getUsersName().equals(passUsername))
				result = false;
		}
		
		return result;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getIndexPage(ModelMap model) {
		List<Album> albums = albumService.getTop6();
		List<Genre> genreList = genreService.getAll();
		
		model.put("albumList", albums);
		model.put("genreList", genreList);
		
		model.put("itemNumbers", shoppingCartService.getAlbumsInCart().size());
		return "Home/Index";
	}
	
}
