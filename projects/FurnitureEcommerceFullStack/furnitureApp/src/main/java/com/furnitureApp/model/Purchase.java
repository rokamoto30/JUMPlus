package com.furnitureApp.model;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;

@Entity
public class Purchase implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Min(0)
    @Column(nullable = false)
    @Schema(description="quantity bought")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name="history_id", referencedColumnName = "id")
    @Schema(description="associated checkout")
    private Checkout checkout;

    @ManyToOne
    @JoinColumn(name="product_id", referencedColumnName = "id")
    @Schema(description="associated product")
    private Product product;

    public Purchase(){}

    public Purchase(@Min(0) Integer quantity, Checkout checkout, Product product) {
        this.quantity = quantity;
        this.checkout = checkout;
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Checkout getCheckout() {
        return checkout;
    }

    public void setCheckout(Checkout checkout) {
        this.checkout = checkout;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    
}
