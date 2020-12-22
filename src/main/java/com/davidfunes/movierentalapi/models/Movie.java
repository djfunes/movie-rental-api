package com.davidfunes.movierentalapi.models;

import java.util.Objects;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(	name = "movies", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "title")
		})
public class Movie {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
	@Size(max = 100)
	private String title;
    
    private String description;

    @Lob
    private byte[] image;
    
    @Column
    private Integer stock;
    
    @Column
    private Double rentalprice;

    @Column
    private Double saleprice;

    @Column
    private boolean available;

    @Column
    private Double penaltyrate;

    @OneToMany(mappedBy = "movie")
    @JsonIgnore
    Set<Rental> rentals;

    @OneToMany(mappedBy = "movie")
    @JsonIgnore
    Set<Sale> sales;
    

    public Movie() {
    }

    public Movie(Long id, String title, String description, byte[] image, Integer stock, Double rentalprice, Double saleprice, boolean available, Double penaltyrate, Set<Rental> rentals, Set<Sale> sales) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.stock = stock;
        this.rentalprice = rentalprice;
        this.saleprice = saleprice;
        this.available = available;
        this.penaltyrate = penaltyrate;
        this.rentals = rentals;
        this.sales = sales;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return this.image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Integer getStock() {
        return this.stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getRentalprice() {
        return this.rentalprice;
    }

    public void setRentalprice(Double rentalprice) {
        this.rentalprice = rentalprice;
    }

    public Double getSaleprice() {
        return this.saleprice;
    }

    public void setSaleprice(Double saleprice) {
        this.saleprice = saleprice;
    }

    public boolean isAvailable() {
        return this.available;
    }

    public boolean getAvailable() {
        return this.available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Double getPenaltyrate() {
        return this.penaltyrate;
    }

    public void setPenaltyrate(Double penaltyrate) {
        this.penaltyrate = penaltyrate;
    }

    public Set<Rental> getRentals() {
        return this.rentals;
    }

    public void setRentals(Set<Rental> rentals) {
        this.rentals = rentals;
    }

    public Set<Sale> getSales() {
        return this.sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }

    public Movie id(Long id) {
        this.id = id;
        return this;
    }

    public Movie title(String title) {
        this.title = title;
        return this;
    }

    public Movie description(String description) {
        this.description = description;
        return this;
    }

    public Movie image(byte[] image) {
        this.image = image;
        return this;
    }

    public Movie stock(Integer stock) {
        this.stock = stock;
        return this;
    }

    public Movie rentalprice(Double rentalprice) {
        this.rentalprice = rentalprice;
        return this;
    }

    public Movie saleprice(Double saleprice) {
        this.saleprice = saleprice;
        return this;
    }

    public Movie available(boolean available) {
        this.available = available;
        return this;
    }

    public Movie penaltyrate(Double penaltyrate) {
        this.penaltyrate = penaltyrate;
        return this;
    }

    public Movie rentals(Set<Rental> rentals) {
        this.rentals = rentals;
        return this;
    }

    public Movie sales(Set<Sale> sales) {
        this.sales = sales;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Movie)) {
            return false;
        }
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id) && Objects.equals(title, movie.title) && Objects.equals(description, movie.description) && Objects.equals(image, movie.image) && Objects.equals(stock, movie.stock) && Objects.equals(rentalprice, movie.rentalprice) && Objects.equals(saleprice, movie.saleprice) && available == movie.available && Objects.equals(penaltyrate, movie.penaltyrate) && Objects.equals(rentals, movie.rentals) && Objects.equals(sales, movie.sales);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, image, stock, rentalprice, saleprice, available, penaltyrate, rentals, sales);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", image='" + getImage() + "'" +
            ", stock='" + getStock() + "'" +
            ", rentalprice='" + getRentalprice() + "'" +
            ", saleprice='" + getSaleprice() + "'" +
            ", available='" + isAvailable() + "'" +
            ", penaltyrate='" + getPenaltyrate() + "'" +
            ", rentals='" + getRentals() + "'" +
            ", sales='" + getSales() + "'" +
            "}";
    }

}
