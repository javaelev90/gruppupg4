package se.javakurs.gruppupg4.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

public class Show {	
	private int id; 
	private int movieId;
	private int theatreId;
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime start;
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime end;
	
	public Show() {
		
	}
	public Show(int id, int movieId, int theatreId, LocalDateTime start, LocalDateTime end) {
		this.id = id;
		this.movieId = movieId;
		this.theatreId = theatreId;
		this.start = start;
		this.end = end;
		
	}	
	public int getMovieId(){
		return this.movieId;
	}
	public void setMovieId(int id) {
		this.movieId = id;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public int getTheatreId() {
		return this.theatreId;
	}
	public void setTheatreId(int id) {
		this.theatreId = id;
	}
	public LocalDateTime getStart() {
		return start;
	}	
	public void setStart(LocalDateTime start) {
		this.start = start;
	}
	public LocalDateTime getEnd() {
		return end;
	}
	public void setEnd(LocalDateTime end) {
		this.end = end;
	}
	public long getDuration() {
		return this.getStart().until(this.getEnd(), ChronoUnit.MINUTES);
	}
	
	public boolean checkOverlap(LocalDateTime startTime, LocalDateTime endTime) {
		return (start.isBefore(endTime) && startTime.isBefore(end));
	}

	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm");
		String startTime = formatter.format(start);
		String endTime = formatter.format(end);
		return "ShowId:" + this.id + " Start:" + startTime + " Slut:" + endTime; 
	}
}