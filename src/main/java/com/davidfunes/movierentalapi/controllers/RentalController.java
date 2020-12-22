package com.davidfunes.movierentalapi.controllers;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import com.davidfunes.movierentalapi.models.Movie;
import com.davidfunes.movierentalapi.models.Rental;
import com.davidfunes.movierentalapi.models.User;
import com.davidfunes.movierentalapi.payload.request.RentalRequest;
import com.davidfunes.movierentalapi.repository.MovieRepository;
import com.davidfunes.movierentalapi.repository.RentalRepository;
import com.davidfunes.movierentalapi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RentalController is used for Rental operations (New rental, Return a movie)
 *
 * @author David Funes
 * 
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class RentalController {
	@Autowired
	RentalRepository rentalRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
    MovieRepository movieRepository;

	/**
	 * <p>
	 * Creates a rental for a movie per user
	 * </p>
	 * 
	 * @param RentalRequest with User id, Movie id and number of days to rent
	 * 
	 * @return Message
	 */
	@PostMapping("/rental")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> saveRental(@Valid @RequestBody RentalRequest rentalRequest) {
		try {
			Optional<User> user = userRepository.findById(rentalRequest.getUser());
			Optional<Movie> movie = movieRepository.findById(rentalRequest.getMovie());

			Rental rental = new Rental();

			rental.setMovie(movie.get());
			rental.setUser(user.get());

			Date currentDate = new Date();

			Calendar cal = Calendar.getInstance();
			cal.setTime(currentDate);
			cal.add(Calendar.DATE, rentalRequest.getDays());

			Date returnDate = cal.getTime();

			rental.setRentaldate(currentDate);
			rental.setReturndate(returnDate);

			rentalRepository.save(rental);

			Movie _movie = movie.get();
			int stock = movie.get().getStock() - 1;
			_movie.setStock(stock);
			movieRepository.save(_movie);

			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	/**
	 * <p>
	 * Updates a rental for a movie, calculates penalty amount if apply
	 * </p>
	 * 
	 * @param id Id of the rental 
	 * 
	 * @param RentalRequest with Returned Date
	 * 
	 * @return Message
	 */
	@PutMapping("/rental/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> updateRental(@PathVariable("id") long id, @RequestBody RentalRequest rentalRequest){
		Double penalty = 0.00;
		try{
			Optional<Rental> rental = rentalRepository.findById(id);
			if(rental.isPresent()){
				LocalDateTime localReturnDate = new java.sql.Timestamp(rental.get().getReturndate().getTime()).toLocalDateTime();
				LocalDateTime localReturnedDate = new java.sql.Timestamp(rentalRequest.getReturnedDate().getTime()).toLocalDateTime();
				long noOfDaysBetween = ChronoUnit.DAYS.between(localReturnDate, localReturnedDate);
				
				Optional<Movie> movie = movieRepository.findById(rental.get().getMovie().getId());

				if(movie.isPresent())
				 penalty = noOfDaysBetween * movie.get().getPenaltyrate();

				Movie _movie = movie.get();
				int stock = movie.get().getStock() + 1;
				_movie.setStock(stock);
				movieRepository.save(_movie);

				Rental _rental = rental.get();
				_rental.setPenalty(penalty);
				_rental.setReturneddate(rentalRequest.getReturnedDate());
				
				return new ResponseEntity<>(rentalRepository.save(_rental), HttpStatus.OK);
			}else{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	} 
}
