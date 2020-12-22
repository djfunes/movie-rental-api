
package com.davidfunes.movierentalapi.models;

import java.util.Date;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(	name = "sales")
public class Sale{
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
    private Date saledate;

    @Column
    private Double saleprice;



    public Sale() {
    }

    public Sale(Long id, Movie movie, User user, Date saledate, Double saleprice) {
        this.id = id;
        this.movie = movie;
        this.user = user;
        this.saledate = saledate;
        this.saleprice = saleprice;
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

    public Date getSaledate() {
        return this.saledate;
    }

    public void setSaledate(Date saledate) {
        this.saledate = saledate;
    }

    public Double getSaleprice() {
        return this.saleprice;
    }

    public void setSaleprice(Double saleprice) {
        this.saleprice = saleprice;
    }

    public Sale id(Long id) {
        this.id = id;
        return this;
    }

    public Sale movie(Movie movie) {
        this.movie = movie;
        return this;
    }

    public Sale user(User user) {
        this.user = user;
        return this;
    }

    public Sale saledate(Date saledate) {
        this.saledate = saledate;
        return this;
    }

    public Sale saleprice(Double saleprice) {
        this.saleprice = saleprice;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Sale)) {
            return false;
        }
        Sale sale = (Sale) o;
        return Objects.equals(id, sale.id) && Objects.equals(movie, sale.movie) && Objects.equals(user, sale.user) && Objects.equals(saledate, sale.saledate) && Objects.equals(saleprice, sale.saleprice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movie, user, saledate, saleprice);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", movie='" + getMovie() + "'" +
            ", user='" + getUser() + "'" +
            ", saledate='" + getSaledate() + "'" +
            ", saleprice='" + getSaleprice() + "'" +
            "}";
    }
        
}