package com.davidfunes.movierentalapi.repository;

import java.util.List;
import java.util.Optional;

import com.davidfunes.movierentalapi.models.Movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long>{
    Optional<Movie> findByTitle(String title);
    List<Movie> findByTitleContaining(String title);
    Boolean existsByTitle(String title);
}
