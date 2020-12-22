package com.davidfunes.movierentalapi.payload.request;

import java.util.Date;

public class RentalRequest {

    private Long user;

    private Long movie;

    private Integer days;

    private Date returnedDate;

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

    public Integer getDays() {
        return this.days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Date getReturnedDate() {
        return this.returnedDate;
    }

    public void setReturnedDate(Date returnedDate) {
        this.returnedDate = returnedDate;
    }

}