
package com.davidfunes.movierentalapi.models;

import java.util.Date;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(	name = "rentals")
public class Rental{
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idmovie")
    Movie movie;

    @ManyToOne
    @JoinColumn(name = "iduser")
    User user;

    @Column
    private Date rentaldate;

    @Column
    private Date returndate; 

    @Column
    private Date returneddate; 

    @Column
    private Double penalty;


    public Rental() {
    }

    public Rental(Long id, Movie movie, User user, Date rentaldate, Date returndate, Double penalty) {
        this.id = id;
        this.movie = movie;
        this.user = user;
        this.rentaldate = rentaldate;
        this.returndate = returndate;
        this.penalty = penalty;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return this.movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getRentaldate() {
        return this.rentaldate;
    }

    public void setRentaldate(Date rentaldate) {
        this.rentaldate = rentaldate;
    }

    public Date getReturndate() {
        return this.returndate;
    }

    public void setReturndate(Date returndate) {
        this.returndate = returndate;
    }

    public Double getPenalty() {
        return this.penalty;
    }

    public void setPenalty(double penalty2) {
        this.penalty = penalty2;
    }

    public Rental id(Long id) {
        this.id = id;
        return this;
    }

    public Rental movie(Movie movie) {
        this.movie = movie;
        return this;
    }

    public Rental user(User user) {
        this.user = user;
        return this;
    }

    public Rental rentaldate(Date rentaldate) {
        this.rentaldate = rentaldate;
        return this;
    }

    public Rental returndate(Date returndate) {
        this.returndate = returndate;
        return this;
    }

    public Rental penalty(Double penalty) {
        this.penalty = penalty;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Rental)) {
            return false;
        }
        Rental rental = (Rental) o;
        return Objects.equals(id, rental.id) && Objects.equals(movie, rental.movie) && Objects.equals(user, rental.user) && Objects.equals(rentaldate, rental.rentaldate) && Objects.equals(returndate, rental.returndate) && Objects.equals(penalty, rental.penalty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movie, user, rentaldate, returndate, penalty);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", movie='" + getMovie() + "'" +
            ", user='" + getUser() + "'" +
            ", rentaldate='" + getRentaldate() + "'" +
            ", returndate='" + getReturndate() + "'" +
            ", returneddate='" + getReturneddate() + "'" +
            ", penalty='" + getPenalty() + "'" +
            "}";
    }



    public Rental(Date returneddate) {
        this.returneddate = returneddate;
    }

    public Date getReturneddate() {
        return this.returneddate;
    }

    public void setReturneddate(Date returneddate) {
        this.returneddate = returneddate;
    }

    public Rental returneddate(Date returneddate) {
        this.returneddate = returneddate;
        return this;
    }

}