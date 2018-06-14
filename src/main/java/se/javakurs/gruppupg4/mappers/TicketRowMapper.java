package se.javakurs.gruppupg4.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import se.javakurs.gruppupg4.entities.Ticket;

public class TicketRowMapper implements RowMapper<Ticket>{

	@Override
	public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
		Ticket ticket = new Ticket();
		ticket.setId(rs.getInt("id"));
		ticket.setBookingId(rs.getInt("booking_id"));
		ticket.setRow(rs.getInt("row"));
		ticket.setCol(rs.getInt("col"));
		return ticket;
	}

}
