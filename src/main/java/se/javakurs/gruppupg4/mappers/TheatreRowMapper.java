package se.javakurs.gruppupg4.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import se.javakurs.gruppupg4.entities.Theatre;

public class TheatreRowMapper implements RowMapper<Theatre>{

	@Override
	public Theatre mapRow(ResultSet rs, int rowNum) throws SQLException {
		Theatre aTheatre = new Theatre();
		
		aTheatre.setId(rs.getInt("id"));
		aTheatre.setName(rs.getString("name"));

		return aTheatre;
	}

}
