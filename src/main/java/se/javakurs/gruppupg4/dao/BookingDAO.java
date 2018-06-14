package se.javakurs.gruppupg4.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import se.javakurs.gruppupg4.entities.Booking;
import se.javakurs.gruppupg4.mappers.BookingRowMapper;

@Repository
public class BookingDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Booking> findAllBookings(int showId){
		String sql = "SELECT * FROM cinematronic.booking WHERE show_id = ?;";
		return jdbcTemplate.query(sql, new Integer[] {showId}, new BookingRowMapper());
	}
	
	
	public int create(Booking booking) {
		String sql = "INSERT INTO cinematronic.booking(show_id, customer_id) VALUES (?,?);";
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement prepStmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				prepStmt.setInt(1, booking.getShowId());
				prepStmt.setInt(2, booking.getCustomerId());
				return prepStmt;
			}
		}, keyHolder);
		
		return (int)keyHolder.getKeys().get("id");
	}

}
