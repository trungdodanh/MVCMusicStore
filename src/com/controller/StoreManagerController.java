package com.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.entities.Album;
import com.entities.Artist;
import com.entities.Genre;
import com.service.AlbumService;
import com.service.ArtistService;
import com.service.GenreService;

/**
 * @author Trung
 *
 */
@Controller
@RequestMapping("/StoreManager")
public class StoreManagerController {
	
	protected static Logger logger = Logger.getLogger("Controller");
	
	@Autowired
	private AlbumService albumService;
	
	@Autowired
	private GenreService genreService;
	
	@Autowired
	private ArtistService artistService;
	
	
	/**
	* Map the StoreManager page
	* @param request
	* @param session
	* @param model
	* @return
	 * @throws Exception 
	*/
	@RolesAllowed(value = "Administrator")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView getStoreManagerPage(HttpServletRequest request, HttpSession session, ModelMap model) throws Exception {
		logger.debug("Just got Store Manager page");
		
		List<Album> albumList = albumService.getAll();
		
		model.put("albumList", albumList);
		return new ModelAndView("/StoreManager/Index", "albumModel", albumService);
	}
	
	
	/**
	 * Map the Create Album page - Get
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/CreateAlbum", method = RequestMethod.GET)
	public String getCreateAlbumPage(ModelMap model) {
		logger.debug("Just got Create GET Album page");
		
		Album album = new Album();
		
		List<Genre> genreList = genreService.getAll();
		List<Artist> artistList = artistService.getAll();
		
		model.put("genres", genreList);
		model.put("artists", artistList);
		model.addAttribute("createAlbum", album);
		
		return "/StoreManager/CreateAlbum";
	}
	
	/**
	 * Map the Create Album page - POST
	 * @param genreSelected
	 * @param artistSelected
	 * @param createAlbum
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/CreateAlbum", method = RequestMethod.POST)
	public String getCreateAlbumPage(@RequestParam("genreSelected") Integer genreSelected, @RequestParam("artistSelected") Integer artistSelected, @ModelAttribute("createAlbum") Album createAlbum) throws Exception {
		logger.debug("Just got Create Post Album page");
		
		Genre genre = genreService.getByGenreId(genreSelected);
		Artist artist = artistService.getByArtistId(artistSelected);
		
		createAlbum.setGenreId(genre);
		createAlbum.setArtistId(artist);
		
		albumService.create(createAlbum);
		
		return "redirect:";
	}
	
	
	/**
	 * Mapping the Album Detail for Store manager page
	 * @param detailsAlbumId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/DetailsAlbum", method = RequestMethod.GET)
	public String getDetailsAlbumPage(@RequestParam("detailsAlbumId") Integer detailsAlbumId, ModelMap model) {
		logger.debug("Just got Details Album page -GET");
		
		Album album = albumService.getAlbumById(detailsAlbumId);
		
		model.put("detailsAlbum", album);
		model.put("detailsAlbumId", detailsAlbumId);
		
		return "/StoreManager/DetailsAlbum";
		
	}
	
	@RequestMapping(value = "/EditAlbum", method = RequestMethod.GET)
	public String getEditAlbumPage(@RequestParam("editAlbumId") Integer editAlbumId, ModelMap model) {
		logger.debug("Just got Edit Album page -GET");
		
		Album album = albumService.getAlbumById(editAlbumId);
		
		List<Genre> genreList = genreService.getAll();
		List<Artist> artistList = artistService.getAll();
		
		model.put("genres", genreList);
		model.put("selectedGenre", album.getGenreId().getName());
		
		model.put("artists", artistList);
		model.put("selectedArtist", album.getArtistId().getName());
		model.put("editAlbum", album);
		
		return "/StoreManager/EditAlbum";
	}
	
	@RequestMapping(value = "/EditAlbum", method = RequestMethod.POST)
	public String getEditAlbumPage(@RequestParam("albumArtUrl") String albumArtUrl, @RequestParam("price") String price, @RequestParam("genreSelected") Integer genreSelected, @RequestParam("artistSelected") Integer artistSelected, @RequestParam("title") String txtTitle, @RequestParam("editAlbumId") Integer editAlbumId, @ModelAttribute("editAlbum") Album editAlbum, ModelMap model) {
		logger.debug("Just got Edit Post Album page");
		
		Album album = albumService.getAlbumById(editAlbumId);
		
		Genre genre = genreService.getByGenreId(genreSelected);
		Artist artist = artistService.getByArtistId(artistSelected);
		
		album.setGenreId(genre);
		album.setArtistId(artist);
		album.setTitle(txtTitle);
		album.setPrice(Double.parseDouble(price));
		album.setAlbumArtUrl(albumArtUrl);
		
		albumService.edit(album);
		
		return "redirect:";
	}
	
	
	@RequestMapping(value = "/DeleteAlbum", method = RequestMethod.GET)
	public String getDeleteAlbumPage(@RequestParam("deleteAlbumId") Integer deleteAlbumId, ModelMap model) {
		logger.debug("Just got Delete Album page -GET");
		
		Album album = albumService.getAlbumById(deleteAlbumId);
		
		model.addAttribute("deleteAlbum", album);
		model.put("deleteAlbumId", album);
		
		return "/StoreManager/DeleteAlbum";
		
	}
	
	@RequestMapping(value = "/DeleteAlbum", method = RequestMethod.POST)
	public String getDeleteAlbumPage(@RequestParam("deleteAlbumId") Integer deleteAlbumId, @ModelAttribute("deleteAlbum") Album deleteAlbum, ModelMap model) {
		logger.debug("Just got Delete Album page -POST");
		
		
		Album album = albumService.getAlbumById(deleteAlbumId);
		
		model.put("deleteAlbumId", album);
		
		albumService.delete(album);
		
		return "redirect:";
		
	}
}
