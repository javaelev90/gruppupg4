package se.javakurs.gruppupg4.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import se.javakurs.gruppupg4.entities.Movie;
import se.javakurs.gruppupg4.entities.Show;
import se.javakurs.gruppupg4.entities.Theatre;

@Controller
@RequestMapping("")
public class HomePageController {

	@GetMapping("/")
	public String getHomePage(Model model) {
		Show show = new Show(0, 0, 0, LocalDateTime.now(), LocalDateTime.now());
		List<Show> shows = new ArrayList<>();
		shows.add(show);
		model.addAttribute("shows", shows);

		Theatre theatre = new Theatre();
		theatre.setName("Blå hallen");
		theatre.setId(0);
		List<Theatre> theatres = new ArrayList<>();
		theatres.add(theatre);
		model.addAttribute("theatres", theatres);

		Movie movie = new Movie();
		movie.setDescription("A seemingly indestructible android is sent from 2029 to"
				+ " 1984 to assassinate a waitress, whose unborn son will lead humanity "
				+ "in a war against the machines, while a soldier from that war is sent to protect her at all costs.");
		movie.setName("terminator");
		List<Movie> movies = new ArrayList<>();
		movies.add(movie);
		model.addAttribute("movies", movies);
		Integer availableSeats = 30;
		List<Integer> availableSeatsForShow = new ArrayList<>();
		availableSeatsForShow.add(availableSeats);
		model.addAttribute("availableSeats", availableSeatsForShow);
		return "index";
	}

}
