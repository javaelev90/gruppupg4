package se.javakurs.gruppupg4.controllers;

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
import se.javakurs.gruppupg4.entities.Ticket;
import se.javakurs.gruppupg4.entities.wrappers.BookingPageWrapper;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/booking")
public class BookingController {
	
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

	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<?> getBookingPage(@PathVariable("id") Integer showId) {
		
		BookingPageWrapper bookingPageWrapper = new BookingPageWrapper();
		
	    Show show = showDAO.findShow(showId);
//	    model.addAttribute("show", show);
	    bookingPageWrapper.setShow(show);
	    
	    Movie movie = movieDAO.findMovie(show.getMovieId());
//	    model.addAttribute("movie", movie);
	    bookingPageWrapper.setMovie(movie);
	    
		List<Theatre> theatres = theatreDAO.findAllTheatres();
//		model.addAttribute("theatres", theatres);
		bookingPageWrapper.setTheatres(theatres);
		
		List<Booking> bookings = bookingDAO.findAllBookings(showId);
		List<Ticket> ticketsForShow = new ArrayList<>();
		for(Booking booking : bookings) {
			ticketsForShow.addAll(ticketDAO.findAllTickets(booking.getId()));
		}
		Map<String, Ticket> ticketMap = new HashMap<>();
		for(Ticket ticket : ticketsForShow) {
			ticketMap.put("row"+ticket.getRow()+"col"+ticket.getCol(), ticket);
		}
//		model.addAttribute("tickets", ticketMap);
		bookingPageWrapper.setTicketMap(ticketMap);
		
		return new ResponseEntity<BookingPageWrapper>(bookingPageWrapper, HttpStatus.OK);
	}
	
	@PostMapping("/{id}")
	@ResponseBody
	public ResponseEntity<?> makeBooking(@PathVariable("id") Integer showId, @RequestBody List<List<Integer>> seats) {
		if(seats.size() > 0) {
			Booking booking = new Booking();
			booking.setShowId(showId);
			booking.setCustomerId(0);
			int bookingId = bookingDAO.create(booking);
			Ticket ticket;
			for(int i = 0; i< seats.size(); i++) {
				ticket = new Ticket();
				ticket.setBookingId(bookingId);
				ticket.setRow(seats.get(i).get(0));
				ticket.setCol(seats.get(i).get(1));
			}
		}
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
}
