package se.javakurs.gruppupg4.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import se.javakurs.gruppupg4.dao.MovieDAO;
import se.javakurs.gruppupg4.dao.ShowDAO;
import se.javakurs.gruppupg4.dao.TheatreDAO;
import se.javakurs.gruppupg4.entities.Movie;
import se.javakurs.gruppupg4.entities.Show;
import se.javakurs.gruppupg4.entities.Theatre;

@Controller
@RequestMapping("")
public class HomePageController {
	
	@Autowired
	private TheatreDAO theatreDAO;
	@Autowired
	private MovieDAO movieDAO;
	@Autowired
	private ShowDAO showDAO;
	
	@GetMapping("/")
	public String getHomePage(Model model) {
//		Show show = new Show(0, 0, 0, LocalDateTime.now(), LocalDateTime.now());
//		List<Show> shows = new ArrayList<>();
//		shows.add(show);
//		model.addAttribute("shows", shows);

//		Theatre theatre = new Theatre();
//		theatre.setName("Blå hallen");
//		theatre.setId(0);
//		List<Theatre> theatres = new ArrayList<>();
//		theatres.add(theatre);


//		Movie movie = new Movie();
//		movie.setDescription("A seemingly indestructible android is sent from 2029 to"
//				+ " 1984 to assassinate a waitress, whose unborn son will lead humanity "
//				+ "in a war against the machines, while a soldier from that war is sent to protect her at all costs.");
//		movie.setName("terminator");
//		List<Movie> movies = new ArrayList<>();
//		movies.add(movie);
//		model.addAttribute("movies", movies);
		List<Theatre> theatres = theatreDAO.findAllTheatres();
		model.addAttribute("theatres", theatres);
		
		List<Show> shows = showDAO.findAllShows();
		model.addAttribute("shows", shows);
		
		List<Movie> movies = movieDAO.findAllMovies();
		Map<Integer, Movie> movieMap = new HashMap<>();
		movies.forEach(movie -> movieMap.put(movie.getId(), movie));
		model.addAttribute("movies", movieMap);
		
		Integer availableSeats = 30;
		List<Integer> availableSeatsForShow = new ArrayList<>();
		availableSeatsForShow.add(availableSeats);
		model.addAttribute("availableSeats", availableSeatsForShow);
		return "index";
	}
	
	@PostMapping("/")
	public String postShow (@RequestParam("movie") Integer movieId, @RequestParam("theatre") Integer theatreId,
			@RequestParam("starttime") String starttime, @RequestParam("endtime") String endtime) {
	
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
		
		Show show = new Show();
		
		
		
		show.setMovieId(movieId);
		show.setTheatreId(theatreId);
		show.setStart(LocalDateTime.parse(LocalDateTime.parse(starttime).format(formatter), formatter));
		show.setEnd(LocalDateTime.parse(LocalDateTime.parse(endtime).format(formatter), formatter));
		
		showDAO.create(show);
	
		return "redirect:/";
		
		
	}
	
	
}


