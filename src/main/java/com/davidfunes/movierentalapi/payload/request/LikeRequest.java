package com.davidfunes.movierentalapi.payload.request;

public class LikeRequest {

    private Long user;

    private Long movie;


    public Long getUser() {
        return this.user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getMovie() {
        return this.movie;
    }

    public void setMovie(Long movie) {
        this.movie = movie;
    }
}