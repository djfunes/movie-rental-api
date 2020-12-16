package com.davidfunes.movierentalapi.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.davidfunes.movierentalapi.models.Movie;
import com.davidfunes.movierentalapi.payload.response.MessageResponse;
import com.davidfunes.movierentalapi.repository.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
* RentalController is the Main controller of the API
*
* @author David Funes
* 
*/

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/rental")
public class RentalController {
	@Autowired
	MovieRepository movieRepository;

	/**
	 * <p>Retrieves all registered movies</p>
	 * @param title (Optional) When included, only movies containing this in the Title will be displayed
	 * @return List of movies
	 */
	@GetMapping("/movies/all")
	public ResponseEntity<List<Movie>> getAllMovies(@RequestParam(required = false) String title) {
		try{
			List<Movie> movieList = new ArrayList<Movie>();
			if(title == null)
				movieRepository.findAll().forEach(movieList::add);
			else
				movieRepository.findByTitleContaining(title).forEach(movieList::add);
			if(movieList.isEmpty())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			else
				return new ResponseEntity<>(movieList,HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * <p>Retrieves a movie by its id</p>
	 * @param id Id of the movie
	 * @return Movie
	 */
	@GetMapping("/movies/{id}")
	public ResponseEntity<Movie> getMovieById(@PathVariable("id") long id) {
		Optional<Movie> movieData = movieRepository.findById(id);
	
		if (movieData.isPresent()) {
		  return new ResponseEntity<>(movieData.get(), HttpStatus.OK);
		} else {
		  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	  }

	/**
	 * <p>Create a new Movie</p>
	 * @param movie in RequesBody
	 * @return Movie
	 */  
	@PostMapping("/movies")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> saveMovie(@Valid @RequestBody Movie movie){
		try{
			if(movieRepository.existsByTitle(movie.getTitle())){
				return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: The movie title already exists!"));
			}else{
				Movie _movie = movieRepository.save(movie);
				return new ResponseEntity<>(_movie, HttpStatus.CREATED);
			}
		} catch (Exception e) {
		  return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * <p>Updates a movie by its id</p>
	 * @param id Id of the movie
	 * @param movie  in RequestBody
	 * @return Movie
	 */
	@PutMapping("/movies/{id}")
	@PreAuthorize("hasRole('ADMIN')")
  	public ResponseEntity<Movie> updateMovie(@PathVariable("id") long id, @RequestBody Movie movie) {
		Optional<Movie> movieData = movieRepository.findById(id);

		if (movieData.isPresent()) {
			Movie _movie = movieData.get();
			_movie.setTitle(movie.getTitle());
			_movie.setDescription(movie.getDescription());
			_movie.setImage(movie.getImage());
			_movie.setStock(movie.getStock());
			_movie.setRentalprice(movie.getRentalprice());
			_movie.setSaleprice(movie.getSaleprice());
			_movie.setPenaltyrate(movie.getPenaltyrate());
			_movie.setAvailable(movie.getAvailable());

			return new ResponseEntity<>(movieRepository.save(_movie), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	 
	/**
	 * <p>Deletes a movie by its id</p>
	 * @param id Id of the movie
	 * @return Message
	 */
	@DeleteMapping("/movies/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteMovie(@PathVariable("id") long id){
		try{
			Optional<Movie> movieData = movieRepository.findById(id);
			if (movieData.isPresent()){ 
				movieRepository.deleteById(id);
				return ResponseEntity.ok(new MessageResponse("Movie deleted"));
			}else{
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new MessageResponse("Movie not found!"));
			}
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
