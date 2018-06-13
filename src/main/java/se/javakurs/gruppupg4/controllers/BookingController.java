package se.javakurs.gruppupg4.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/booking")
public class BookingController {
	
	@Autowired
	private TheatreDAO theatreDAO;
	@Autowired
	private MovieDAO movieDAO;
	@Autowired
	private ShowDAO showDAO;

	@GetMapping("/{id}")
	public String getBookingPage(@PathVariable("id") Integer showId, @RequestParam("seats") Integer seats, Model model) {
		
	    Show show = showDAO.findShow(showId);
	    model.addAttribute("show", show);
	    
	    Movie movie = movieDAO.findMovie(show.getMovieId());
	    model.addAttribute("movie", movie);
	    
		List<Theatre> theatres = theatreDAO.findAllTheatres();
		model.addAttribute("theatres", theatres);
		
		return "booking";
	}
	
	@PostMapping("")
	public String makeBooking(@RequestParam("seats") List<Integer> seats) {
		
		System.out.println(seats);
//		for(int i = 0; i< seats.length; i++) {
//			for(int j = 0; j< seats[i].length; j++) {
//				System.out.println("row:"+i+" col:"+j+" "+seats[i][j]);
//			}
//		}
		
//		System.out.println(seats[0][0] + " " + seats[0][1]);
//		System.out.println(seats[0][0] + " " + seats[1][0]);
//		System.out.println(seats.get(1).get(0) + " " + seats.get(1).get(1));
		return "booking";
	}
	
}
