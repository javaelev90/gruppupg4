package se.javakurs.gruppupg4.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import se.javakurs.gruppupg4.entities.Booking;

public class BookingRowMapper  implements RowMapper<Booking>{

	@Override
	public Booking mapRow(ResultSet rs, int rowNum) throws SQLException {
		Booking booking = new Booking();
		
		booking.setId(rs.getInt("id"));
		booking.setShowId(rs.getInt("show_id"));
		booking.setCustomerId(rs.getInt("customer_id"));
		
		return booking;
	}

}
