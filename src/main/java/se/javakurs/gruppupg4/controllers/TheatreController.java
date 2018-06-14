package se.javakurs.gruppupg4.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
@RequestMapping("/theatre")
public class TheatreController {
	
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

	@GetMapping("/{theatreId}")
	public String getTheatrePage(@PathVariable("theatreId") Integer theatreId, Model model) {

		List<Theatre> theatres = theatreDAO.findAllTheatres();
		model.addAttribute("theatres", theatres);
		
		List<Show> shows = showDAO.findAllShowsForTheatre(theatreId);
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
	
}
