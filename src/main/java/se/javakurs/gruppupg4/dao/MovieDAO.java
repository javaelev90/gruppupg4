package se.javakurs.gruppupg4.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import se.javakurs.gruppupg4.entities.Movie;
import se.javakurs.gruppupg4.mappers.MovieRowMapper;

@Repository
public class MovieDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Movie findMovie(int id) {
		String selectMovie = "SELECT * FROM cinematronic.movie WHERE id=?;";
		return jdbcTemplate.queryForObject(selectMovie, new Integer[] {id}, new MovieRowMapper());
	}
	
	public List<Movie> findAllMovies(){
		String selectMovie = "SELECT * FROM cinematronic.movie;";
		return jdbcTemplate.query(selectMovie, new MovieRowMapper());
	}
	
}
