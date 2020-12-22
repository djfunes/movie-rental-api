package com.davidfunes.movierentalapi.payload.request;

import java.util.Objects;

public class SaleRequest {

    private Long user;

    private Long movie;


    public SaleRequest() {
    }

    public SaleRequest(Long user, Long movie) {
        this.user = user;
        this.movie = movie;
    }

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

    public SaleRequest user(Long user) {
        this.user = user;
        return this;
    }

    public SaleRequest movie(Long movie) {
        this.movie = movie;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SaleRequest)) {
            return false;
        }
        SaleRequest saleRequest = (SaleRequest) o;
        return Objects.equals(user, saleRequest.user) && Objects.equals(movie, saleRequest.movie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, movie);
    }

    @Override
    public String toString() {
        return "{" +
            " user='" + getUser() + "'" +
            ", movie='" + getMovie() + "'" +
            "}";
    }

}