package se.javakurs.gruppupg4.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import se.javakurs.gruppupg4.entities.Ticket;
import se.javakurs.gruppupg4.mappers.TicketRowMapper;

@Repository
public class TicketDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	public List<Ticket> findAllTickets(int bookingId){
		return jdbcTemplate.query("SELECT * FROM cinematronic.ticket WHERE booking_id = ?;", new Integer[] {bookingId}, new TicketRowMapper());
	}
	
	public int create(Ticket ticket) {
		
		jdbcTemplate.update("INSERT INTO cinematronic.ticket(booking_id, row, col) VALUES (?,?,?);",
				ticket.getBookingId(), ticket.getRow(), ticket.getCol());

		return 0;
	}

}
