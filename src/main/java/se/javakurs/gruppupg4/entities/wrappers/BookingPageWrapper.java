package se.javakurs.gruppupg4.entities.wrappers;

import java.util.List;
import java.util.Map;

import se.javakurs.gruppupg4.entities.Movie;
import se.javakurs.gruppupg4.entities.Show;
import se.javakurs.gruppupg4.entities.Theatre;
import se.javakurs.gruppupg4.entities.Ticket;

public class BookingPageWrapper {

	private List<Theatre> theatres;
	private Show show;
	private Movie movie;
	private Map<String, Ticket> ticketMap;
	
	public List<Theatre> getTheatres() {
		return theatres;
	}
	public void setTheatres(List<Theatre> theatres) {
		this.theatres = theatres;
	}
	public Show getShow() {
		return show;
	}
	public void setShow(Show show) {
		this.show = show;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public Map<String, Ticket> getTicketMap() {
		return ticketMap;
	}
	public void setTicketMap(Map<String, Ticket> ticketMap) {
		this.ticketMap = ticketMap;
	}
	
	
}
