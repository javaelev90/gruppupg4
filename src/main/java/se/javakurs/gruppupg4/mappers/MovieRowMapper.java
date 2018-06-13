package se.javakurs.gruppupg4.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import se.javakurs.gruppupg4.entities.Movie;

public class MovieRowMapper implements RowMapper<Movie>{

	@Override
	public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
		Movie aMovie = new Movie();
		aMovie.setId(rs.getInt("id"));
		aMovie.setName(rs.getString("name"));
		aMovie.setDescription(rs.getString("description"));

		return aMovie;
	}

}
