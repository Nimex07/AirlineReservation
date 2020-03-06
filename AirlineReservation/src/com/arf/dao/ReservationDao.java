package com.arf.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.arf.model.Reservation;

public class ReservationDao {

	// instance variable
	private JdbcTemplate jdbcTemplate;

	// setter
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// query for insert
	private static String CREATE_RESERVATION = "INSERT INTO airlinereservations (name,phone,email,dob,address,idproof,departuredate,flightno,departurecity,destinationcity,bookingstatus,isactive) VALUES (?,?,?,?,?,?,?,?,?,?,false,true)";

	// query for view all
	private static String VIEW_ALL = "SELECT * FROM airlinereservations";

	// query for search by flight number
	private static String SEARCH_BY_FLIGHTNO = "SELECT * FROM airlinereservations WHERE flightno=?";

	// query for updating isactive
	private static String DELETE = "UPDATE airlinereservations SET isactive=false WHERE reservationid=?";

	// query to update bookingstatus
	private static String UPDATE_BOOKING = "UPDATE airlinereservations SET bookingstatus=true WHERE reservationid=?";

	// query to search by id
	private static String SEARCH_BY_ID = "SELECT * FROM airlinereservations WHERE reservationid=?";

	// query to search by departure date
	private static String SEARCH_BY_DEPT_DATE = "SELECT * FROM airlinereservations WHERE departuredate=?";

	// method to insert
	public void insert(final Reservation reservation) {

		jdbcTemplate.update(CREATE_RESERVATION, reservation.getName(),
				reservation.getPhone(), reservation.getEmail(),
				reservation.getDob(), reservation.getAddress(),
				reservation.getIdProof(), reservation.getDepartureDate(),
				reservation.getFlightNo(), reservation.getDepartureCity(),
				reservation.getDestinationCity());
	}

	// method to list all
	public List<Reservation> displayAll() {

		// list object
		List<Reservation> listReservation = jdbcTemplate.query(VIEW_ALL,
				new ReservationMapper());
		return listReservation;
	}

	// method to list all
	public List<Reservation> searchByFlightNo(Integer id) {

		// list object
		List<Reservation> listReservation = jdbcTemplate.query(
				SEARCH_BY_FLIGHTNO, new Object[] { id },
				new ReservationMapper());
		return listReservation;
	}

	// method to list all
	public Reservation searchById(Integer id) {

		// object
		Reservation reservation = jdbcTemplate.queryForObject(SEARCH_BY_ID,
				new Object[] { id }, new ReservationMapper());

		return reservation;
	}

	// method to update isactive
	public void updateIsActive(Integer id) {

		jdbcTemplate.update(DELETE, new Object[] { id });
	}

	// method to update booking status
	public void updateBookingStatus(Integer id) {

		jdbcTemplate.update(UPDATE_BOOKING, new Object[] { id });
	}

	// method to search by departuredate
	public List<Reservation> searchByDeptDate(java.sql.Date deptDate) {

		System.out.println(deptDate);
		// list object
		List<Reservation> listReservation = jdbcTemplate.query(
				SEARCH_BY_DEPT_DATE, new Object[] { deptDate },
				new ReservationMapper());
		return listReservation;

	}

	public class ReservationMapper implements RowMapper<Reservation> {

		@Override
		public Reservation mapRow(ResultSet resultSet, int rowNumber)
				throws SQLException {

			// creating object for Reservation
			Reservation reservation = new Reservation();

			reservation.setReservationId(resultSet.getInt("reservationid"));
			reservation.setName(resultSet.getString("name"));
			reservation.setPhone(resultSet.getString("phone"));
			reservation.setEmail(resultSet.getString("email"));
			reservation.setDob(resultSet.getDate("dob"));
			reservation.setAddress(resultSet.getString("address"));
			reservation.setIdProof(resultSet.getString("idproof"));
			reservation.setDepartureDate(resultSet.getDate("departuredate"));
			reservation.setDepartureCity(resultSet.getString("departurecity"));
			reservation.setFlightNo(resultSet.getString("flightno"));
			reservation.setDestinationCity(resultSet
					.getString("destinationcity"));
			reservation.setBookingStatus(resultSet.getBoolean("bookingstatus"));
			reservation.setIsActive(resultSet.getBoolean("isactive"));
			return reservation;
		}
	}

}
