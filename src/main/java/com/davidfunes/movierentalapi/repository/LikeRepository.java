package com.davidfunes.movierentalapi.repository;

import java.util.Optional;

import com.davidfunes.movierentalapi.models.Like;
import com.davidfunes.movierentalapi.models.Movie;
import com.davidfunes.movierentalapi.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long>{
    Optional<Like> findByUserAndMovie(User user, Movie movie);
}
