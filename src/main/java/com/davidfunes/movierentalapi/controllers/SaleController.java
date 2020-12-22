package com.davidfunes.movierentalapi.controllers;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import com.davidfunes.movierentalapi.models.Movie;
import com.davidfunes.movierentalapi.models.Sale;
import com.davidfunes.movierentalapi.models.User;
import com.davidfunes.movierentalapi.payload.request.SaleRequest;
import com.davidfunes.movierentalapi.payload.response.MessageResponse;
import com.davidfunes.movierentalapi.repository.MovieRepository;
import com.davidfunes.movierentalapi.repository.SaleRepository;
import com.davidfunes.movierentalapi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SaleController to register all sales of a Movie per user
 *
 * @author David Funes
 * 
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class SaleController {
    @Autowired
	UserRepository userRepository;

	@Autowired
    MovieRepository movieRepository;

    @Autowired
    SaleRepository saleRepository;

    /**
	 * <p>
	 * Creates a sale for a movie per user
	 * </p>
	 * 
	 * @param saleRequest with User id and Movie id 
	 * 
	 * @return Message
	 */

    @PostMapping("/sales")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> saveSale(@Valid @RequestBody SaleRequest saleRequest) {
        try{
            Optional<User> user = userRepository.findById(saleRequest.getUser());
            Optional<Movie> movie = movieRepository.findById(saleRequest.getMovie());
            if(user.isPresent() && movie.isPresent()){
                Sale newSale = new Sale();
                newSale.setMovie(movie.get());
                newSale.setUser(user.get());
                newSale.setSaledate(new Date());
                newSale.setSaleprice(movie.get().getSaleprice());

                Movie _movie = movie.get();
                int stock = movie.get().getStock() - 1;
                _movie.setStock(stock);
                movieRepository.save(_movie);

                return new ResponseEntity<>(saleRepository.save(newSale), HttpStatus.OK);
            }else{
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new MessageResponse("Movie or User not found!"));
            }
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
