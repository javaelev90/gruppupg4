package se.javakurs.gruppupg4.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import se.javakurs.gruppupg4.entities.Show;
import se.javakurs.gruppupg4.mappers.ShowRowMapper;

@Repository
public class ShowDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Show findShow(int id) {
		String selectShow = "SELECT * FROM cinematronic.show WHERE id=?;";
		return jdbcTemplate.queryForObject(selectShow, new Integer[] {id}, new ShowRowMapper());
	}
	
	public List<Show> findAllShows() {
		String selectShow = "SELECT * FROM cinematronic.show;";
		return jdbcTemplate.query(selectShow, new ShowRowMapper());
		
	}
	
	public List<Show> findAllShowsForTheatre(int theatreId) {
		String selectShow = "SELECT * FROM cinematronic.show WHERE theatre_id = ?;";
		return jdbcTemplate.query(selectShow, new Integer[] {theatreId}, new ShowRowMapper());
		
	}
	
	public int create(Show show) {
		 
		jdbcTemplate.update("INSERT INTO cinematronic.show (movie_id, theatre_id, starttime, endtime) VALUES (?,?,?,?);"
				,show.getMovieId(), show.getTheatreId(), show.getStart(), show.getEnd());
		
		return 0;
		
	}

}
