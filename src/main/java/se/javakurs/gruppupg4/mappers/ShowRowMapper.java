package se.javakurs.gruppupg4.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.jdbc.core.RowMapper;

import se.javakurs.gruppupg4.entities.Show;

public class ShowRowMapper implements RowMapper<Show> {

	
	@Override
	public Show mapRow(ResultSet rs, int rowNum) throws SQLException {
		Show aShow = new Show();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
		
		aShow.setId(rs.getInt("id"));
		aShow.setMovieId(rs.getInt("movie_id"));
		aShow.setTheatreId(rs.getInt("theatre_id"));

		aShow.setStart(rs.getTimestamp("starttime").toLocalDateTime());
		aShow.setEnd(rs.getTimestamp("endtime").toLocalDateTime());
		return aShow;
	}

}
