package se.javakurs.gruppupg4.entities;

public class Theatre {
	
	private int SEAT_ROWS = 4;
	private int SEAT_COLS = 9;
	private int id;
	private String name;						
												


	public Theatre() {
		
	}
	// Constructor
	public Theatre(int id, String name) {
		this.id = id;
		this.name = name;
	}

	
	public void setId(int id) {
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	
	

	public int getSEAT_ROWS() {
		return SEAT_ROWS;
	}
	public void setSEAT_ROWS(int sEAT_ROWS) {
		SEAT_ROWS = sEAT_ROWS;
	}
	public int getSEAT_COLS() {
		return SEAT_COLS;
	}
	public void setSEAT_COLS(int sEAT_COLS) {
		SEAT_COLS = sEAT_COLS;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
