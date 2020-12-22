
package com.davidfunes.movierentalapi.models;

import java.util.Date;
import java.util.Objects;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(	name = "likes")
public class Like{
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "iduser", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idmovie", referencedColumnName = "id")
    @JsonIgnore
    private Movie movie;

    @Column
    private Date likedate;



    public Like() {
    }

    public Like(Long id, User user, Movie movie, Date likedate) {
        this.id = id;
        this.user = user;
        this.movie = movie;
        this.likedate = likedate;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return this.movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Date getLikedate() {
        return this.likedate;
    }

    public void setLikedate(Date likedate) {
        this.likedate = likedate;
    }

    public Like id(Long id) {
        this.id = id;
        return this;
    }

    public Like user(User user) {
        this.user = user;
        return this;
    }

    public Like movie(Movie movie) {
        this.movie = movie;
        return this;
    }

    public Like likedate(Date likedate) {
        this.likedate = likedate;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Like)) {
            return false;
        }
        Like like = (Like) o;
        return Objects.equals(id, like.id) && Objects.equals(user, like.user) && Objects.equals(movie, like.movie) && Objects.equals(likedate, like.likedate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, movie, likedate);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", user='" + getUser() + "'" +
            ", movie='" + getMovie() + "'" +
            ", likedate='" + getLikedate() + "'" +
            "}";
    }
    
}