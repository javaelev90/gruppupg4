package se.javakurs.gruppupg4.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import se.javakurs.gruppupg4.dao.BookingDAO;
import se.javakurs.gruppupg4.dao.MovieDAO;
import se.javakurs.gruppupg4.dao.ShowDAO;
import se.javakurs.gruppupg4.dao.TheatreDAO;
import se.javakurs.gruppupg4.dao.TicketDAO;
import se.javakurs.gruppupg4.entities.Booking;
import se.javakurs.gruppupg4.entities.Movie;
import se.javakurs.gruppupg4.entities.Show;
import se.javakurs.gruppupg4.entities.Theatre;
import se.javakurs.gruppupg4.entities.wrappers.TheatrepageWrapper;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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
	@ResponseBody
	public ResponseEntity<TheatrepageWrapper> getHomePage() {

		TheatrepageWrapper theatrepageWrapper = new TheatrepageWrapper();
		List<Theatre> theatres = theatreDAO.findAllTheatres();
//		model.addAttribute("theatres", theatres);
		theatrepageWrapper.setTheatres(theatres);
		
		List<Show> shows = showDAO.findAllShows();
//		model.addAttribute("shows", shows);
		theatrepageWrapper.setShows(shows);
		
		List<Movie> movies = movieDAO.findAllMovies();
//		Map<Integer, Movie> movieMap = new HashMap<>();
//		movies.forEach(movie -> movieMap.put(movie.getId(), movie));
//		model.addAttribute("movies", movieMap);
		theatrepageWrapper.setMovies(movies);
		
//		List<Integer> totalSeatsTaken = new ArrayList<>();
		Map<Integer, Integer> totalSeatsTaken = new HashMap<>();
		for(Show show : shows) {
			List<Booking> bookings = new ArrayList<>();
			bookings.addAll(bookingDAO.findAllBookings(show.getId()));
			int seatsTaken = 0;
			for(Booking booking : bookings) {
				seatsTaken += ticketDAO.findAllTickets(booking.getId()).size();
			}
			totalSeatsTaken.put(show.getId(), new Integer(seatsTaken));
		}
		

//		model.addAttribute("seatsTaken", totalSeatsTaken);
		theatrepageWrapper.setTotalSeatsTaken(totalSeatsTaken);
		System.out.println("Retrieved theatrepagewrapper");
		return new ResponseEntity<>(theatrepageWrapper, HttpStatus.OK);
	}
	
	@PostMapping("/")
	@ResponseBody
	public ResponseEntity<?> postShow (@RequestBody Show show) {
		if(show.getStart() == null || show.getEnd() == null || show.getMovieId() < 0|| show.getTheatreId() < 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if(!isShowOverlapping(show, show.getTheatreId())) {
			showDAO.create(show);
			return new ResponseEntity<Show>(show,HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}	
	}
	
	private boolean isShowOverlapping(Show checkShow, Integer theatreId) {
		
		List<Show> shows = showDAO.findAllShowsForTheatre(theatreId);
		for(Show show : shows) {

			if(checkShow.checkOverlap(show.getStart(), show.getEnd())) {
				return true;
			}
		}
		if(!checkShow.getStart().isBefore(checkShow.getEnd())) {
			return true;
		}
		return false;
		
	}
	
	
}


