package com.arf.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arf.dao.ReservationDao;
import com.arf.model.Reservation;

@RequestMapping("/airline")
@RestController
public class ReservationController {

	// object for ReservationDao
	@Autowired
	private ReservationDao reservationDao;

	// method to insert data
	@RequestMapping(value = "/reservation", method = RequestMethod.POST)
	public void addReservation(@RequestBody Reservation reservation) {

		// calling insert method
		reservationDao.insert(reservation);
	}

	// method to view all
	@RequestMapping(value = "/reservations", method = RequestMethod.GET)
	public List viewAllReservations() {

		// getting all details into list
		List list = reservationDao.displayAll();
		return list;
	}

	@RequestMapping(value = "/reservationflightno/{id}", method = RequestMethod.GET)
	public List searchByFlightNo(@PathVariable("id") Integer resId) {

		// getting all details into list
		List list = reservationDao.searchByFlightNo(resId);
		return list;
	}

	@RequestMapping(value = "/reservationcancel/{id}", method = RequestMethod.PUT)
	public void cancelBooking(@PathVariable("id") Integer resId) {

		// calling method
		reservationDao.updateIsActive(resId);

	}

	@RequestMapping(value = "/reservationconfirm/{id}", method = RequestMethod.PUT)
	public void updateBookingStatus(@PathVariable("id") Integer resId) {

		// calling method
		reservationDao.updateBookingStatus(resId);

	}

	@RequestMapping(value = "/reservationid/{id}", method = RequestMethod.GET)
	public Reservation searchById(@PathVariable("id") Integer resId) {

		// calling method
		Reservation reservation = reservationDao.searchById(resId);
		return reservation;
	}

	@RequestMapping(value = "/reservationdeptdate/{date}", method = RequestMethod.GET)
	public List searchByDeptDate(@PathVariable("date") java.sql.Date deptDate) {

		// calling method
		List list = reservationDao.searchByDeptDate(deptDate);

		return list;
	}
}
