package se.javakurs.gruppupg4.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import se.javakurs.gruppupg4.entities.Theatre;
import se.javakurs.gruppupg4.mappers.TheatreRowMapper;

@Repository
public class TheatreDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Theatre findTheatre(int id) {
		String selectTheatre = "SELECT * FROM cinematronic.theatre WHERE id=?;";
		return jdbcTemplate.queryForObject(selectTheatre, new Integer[] {id}, new TheatreRowMapper());
	}
	
	public List<Theatre> findAllTheatres(){
		String selectTheatre = "SELECT * FROM cinematronic.theatre;";
		return jdbcTemplate.query(selectTheatre, new TheatreRowMapper());
	}
}
