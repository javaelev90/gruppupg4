package se.javakurs.gruppupg4.entities.wrappers;

import java.util.List;
import java.util.Map;

import se.javakurs.gruppupg4.entities.Movie;
import se.javakurs.gruppupg4.entities.Show;
import se.javakurs.gruppupg4.entities.Theatre;

public class TheatrepageWrapper {

	private List<Theatre> theatres;
	private List<Show> shows;
	private Map<Integer, Movie> movieMap;
	private List<Integer> totalSeatsTaken;
	
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
	public Map<Integer, Movie> getMovieMap() {
		return movieMap;
	}
	public void setMovieMap(Map<Integer, Movie> movieMap) {
		this.movieMap = movieMap;
	}
	public List<Integer> getTotalSeatsTaken() {
		return totalSeatsTaken;
	}
	public void setTotalSeatsTaken(List<Integer> totalSeatsTaken) {
		this.totalSeatsTaken = totalSeatsTaken;
	}
	
	
}
