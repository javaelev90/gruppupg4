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

import se.javakurs.gruppupg4.dao.BookingDAO;
import se.javakurs.gruppupg4.dao.MovieDAO;
import se.javakurs.gruppupg4.dao.ShowDAO;
import se.javakurs.gruppupg4.dao.TheatreDAO;
import se.javakurs.gruppupg4.dao.TicketDAO;
import se.javakurs.gruppupg4.entities.Booking;
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
	@Autowired
	private BookingDAO bookingDAO;
	@Autowired
	private TicketDAO ticketDAO;
	
	@GetMapping("/")
	public String getHomePage(Model model) {

		List<Theatre> theatres = theatreDAO.findAllTheatres();
		model.addAttribute("theatres", theatres);
		
		List<Show> shows = showDAO.findAllShows();
		model.addAttribute("shows", shows);
		
		List<Movie> movies = movieDAO.findAllMovies();
		Map<Integer, Movie> movieMap = new HashMap<>();
		movies.forEach(movie -> movieMap.put(movie.getId(), movie));
		model.addAttribute("movies", movieMap);
		
		List<Integer> totalSeatsTaken = new ArrayList<>();
		for(Show show : shows) {
			List<Booking> bookings = new ArrayList<>();
			bookings.addAll(bookingDAO.findAllBookings(show.getId()));
			int seatsTaken = 0;
			for(Booking booking : bookings) {
				seatsTaken += ticketDAO.findAllTickets(booking.getId()).size();
			}
			totalSeatsTaken.add(seatsTaken);
		}
		

		model.addAttribute("seatsTaken", totalSeatsTaken);
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
		
		if(!isShowOverlapping(show)) {
			showDAO.create(show);
		}
	
		return "redirect:/";
		
		
	}
	
	private boolean isShowOverlapping(Show checkShow) {
		
		List<Show> shows = showDAO.findAllShows();
		for(Show show : shows) {
			if((checkShow.getStart().isBefore(show.getEnd()) && show.getStart().isBefore(checkShow.getEnd()))) {
				return true;
			}
		}
		return false;
		
	}
	
	
}


