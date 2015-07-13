package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.ModelMap;

import com.entities.Album;
import com.entities.Genre;
import com.service.AlbumService;
import com.service.GenreService;
import com.service.ShoppingCartService;

@Controller
@RequestMapping("/Store")
public class StoreController {
	protected static Logger logger = Logger.getLogger("Controller");
	
	@Autowired
	private AlbumService albumService;
	
	@Autowired
	private GenreService genreService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;

	/**
	 * Map Root of Store page
	 * 
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getStoreIndexPage(ModelMap model) throws Exception {

		List<Genre> genreList = genreService.getAll();
		model.put("genreList", genreList);
		model.put("genreCount", genreList.size());

		model.put("itemNumbers", shoppingCartService.getAlbumsInCart().size());
		
		return "/Store/Index";
	}

	@RequestMapping(value = "/Browse", method = RequestMethod.GET)
	public String getStoreBrowsePage(@RequestParam(value = "genre", required = false) String genreName, ModelMap model) throws Exception {
		List<Genre> genreList = genreService.getAll();
		model.put("genreList", genreList);
		
		List<Album> foundAlbum = new ArrayList<Album>();
		
		if (genreName == null) {
			model.put("genre", "Empty");
		} else {
			List<Album> myAlbum = albumService.getAll();
			for (Album a : myAlbum) 
			{
				if(a.getGenreId().getName().equals(genreName)) 
				{
					foundAlbum.add(a);
				}
			}
			model.put("genre", genreName);
			model.put("foundAlbum", foundAlbum);
		}
		
		model.put("itemNumbers", shoppingCartService.getAlbumsInCart().size());

		return "/Store/Browse";
	}

	@RequestMapping(value = "/Details", method = RequestMethod.GET)
	public String getStoreDetailsPage(@RequestParam(value = "albumId", required = false) Integer albumId, ModelMap model) throws Exception {
		Album album = albumService.getAlbumById(albumId);
		
		model.put("albumId", albumId);
		model.put("detailedAlbum", album);
		
		List<Genre> genreList = genreService.getAll();
		model.put("genreList", genreList);

		model.addAttribute("addedAlbum", album);
		model.put("itemNumbers", shoppingCartService.getAlbumsInCart().size());
		return "/Store/Details";
	}
	
	@RequestMapping(value = "/Details", method = RequestMethod.POST)
	public String getStoreDetailsPage(@RequestParam(value = "albumId", required = false) Integer albumId, @ModelAttribute("addedAlbum") Album addedAlbum, HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
		List<Genre> genreList = genreService.getAll();
		model.put("genreList", genreList);
		
		model.addAttribute("addedAlbum", addedAlbum);
		request.getSession().setAttribute("addedAlbumId", albumId);
		logger.info("albumID : " + albumId);
		model.put("itemNumbers", shoppingCartService.getAlbumsInCart().size());
		return "redirect:/ShoppingCart/";
	}
}