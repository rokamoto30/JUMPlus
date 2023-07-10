package com.furnitureApp.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    @Schema(description="product name")
    private String name;

    @Column(nullable = false)
    @Schema(description="product url")
    private String imgUrl;

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade=CascadeType.ALL)
    @Schema(description="list of purchases")
    private List<Purchase> purchases;

    @JsonIgnore
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    @Schema(description="product stock")
    private Stock stock;
    
    public Product(){}

    public Product(String name, String imgUrl) {
        this.name = name;
        this.imgUrl = imgUrl;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", imgUrl=" + imgUrl + ", purchases=" + purchases + ", stock="
				+ stock + "]";
	}

    
    
}
