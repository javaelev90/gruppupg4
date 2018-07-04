package se.javakurs.gruppupg4.entities.wrappers;

import java.util.List;
import java.util.Map;

import se.javakurs.gruppupg4.entities.Movie;
import se.javakurs.gruppupg4.entities.Show;
import se.javakurs.gruppupg4.entities.Theatre;

public class TheatrepageWrapper {

	private List<Theatre> theatres;
	private List<Show> shows;
	private List<Movie> movies;
	private Map<Integer, Integer> totalSeatsTaken;
	
	public List<Theatre> getTheatres() {
		return theatres;
	}
	public void setTheatres(List<Theatre> theatres) {
		this.theatres = theatres;
	}
	public List<Show> getShows() {
		return shows;
	}
	public void setShows(List<Show> shows) {
		this.shows = shows;
	}
	public List<Movie> getMovies() {
		return movies;
	}
	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}
	public Map<Integer, Integer> getTotalSeatsTaken() {
		return totalSeatsTaken;
	}
	public void setTotalSeatsTaken(Map<Integer, Integer> totalSeatsTaken) {
		this.totalSeatsTaken = totalSeatsTaken;
	}
	
	
}
