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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

@Controller
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
	public String getBookingPage(@PathVariable("id") Integer showId, Model model) {
		
	    Show show = showDAO.findShow(showId);
	    model.addAttribute("show", show);
	    
	    Movie movie = movieDAO.findMovie(show.getMovieId());
	    model.addAttribute("movie", movie);
	    
		List<Theatre> theatres = theatreDAO.findAllTheatres();
		model.addAttribute("theatres", theatres);
		
		List<Booking> bookings = bookingDAO.findAllBookings(showId);
		List<Ticket> ticketsForShow = new ArrayList<>();
		for(Booking booking : bookings) {
			ticketsForShow.addAll(ticketDAO.findAllTickets(booking.getId()));
		}
		Map<String, Ticket> ticketMap = new HashMap<>();
		for(Ticket ticket : ticketsForShow) {
			ticketMap.put("row"+ticket.getRow()+"col"+ticket.getCol(), ticket);
		}
		model.addAttribute("tickets", ticketMap);
		
		return "booking";
	}
	
	@PostMapping("/{id}")
	public ModelAndView makeBooking(@PathVariable("id") Integer showId, @RequestParam("seats") List<Integer> seats) {
		if(seats.size() > 0) {
			Booking booking = new Booking();
			booking.setShowId(showId);
			booking.setCustomerId(0);
			int bookingId = bookingDAO.create(booking);
			Ticket ticket;
			for(int i = 0; i< seats.size(); i+=2) {
				ticket = new Ticket();
				ticket.setBookingId(bookingId);
				ticket.setRow(seats.get(i));
				ticket.setCol(seats.get(i+1));
				ticketDAO.create(ticket);
			}
		}
		
		return new ModelAndView("redirect:/");
	}
	
}
