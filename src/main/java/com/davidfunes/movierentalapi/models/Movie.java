package com.davidfunes.movierentalapi.models;

import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
    private Long rentalprice;

    @Column
    private Long saleprice;

    @Column
    private boolean available;

    @Column
    private Long penaltyrate;


    public Movie() {
    }

    public Movie(Long id, String title, String description, byte[] image, Integer stock, Long rentalprice, Long saleprice, boolean available, Long penaltyrate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.stock = stock;
        this.rentalprice = rentalprice;
        this.saleprice = saleprice;
        this.available = available;
        this.penaltyrate = penaltyrate;
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

    public Long getRentalprice() {
        return this.rentalprice;
    }

    public void setRentalprice(Long rentalprice) {
        this.rentalprice = rentalprice;
    }

    public Long getSaleprice() {
        return this.saleprice;
    }

    public void setSaleprice(Long saleprice) {
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

    public Long getPenaltyrate() {
        return this.penaltyrate;
    }

    public void setPenaltyrate(Long penaltyrate) {
        this.penaltyrate = penaltyrate;
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

    public Movie rentalprice(Long rentalprice) {
        this.rentalprice = rentalprice;
        return this;
    }

    public Movie saleprice(Long saleprice) {
        this.saleprice = saleprice;
        return this;
    }

    public Movie available(boolean available) {
        this.available = available;
        return this;
    }

    public Movie penaltyrate(Long penaltyrate) {
        this.penaltyrate = penaltyrate;
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
        return Objects.equals(id, movie.id) && Objects.equals(title, movie.title) && Objects.equals(description, movie.description) && Objects.equals(image, movie.image) && Objects.equals(stock, movie.stock) && Objects.equals(rentalprice, movie.rentalprice) && Objects.equals(saleprice, movie.saleprice) && available == movie.available && Objects.equals(penaltyrate, movie.penaltyrate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, image, stock, rentalprice, saleprice, available, penaltyrate);
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
            "}";
    }


}
