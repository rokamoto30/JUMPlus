package com.furnitureApp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class newPrododuct {
//	@NotNull
    private String name;
//	@NotNull
    private String imgUrl;
//    @NotNull
//    @Min(0)
    private Integer quantity;
//    @NotNull
//    @Positive
    private Double price;
    
    public newPrododuct() {}

    public newPrododuct(String name, String imgUrl, Integer quantity, Double price) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.quantity = quantity;
        this.price = price;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

	@Override
	public String toString() {
		return "newPrododuct [name=" + name + ", imgUrl=" + imgUrl + ", quantity=" + quantity + ", price=" + price
				+ "]";
	}
    
    

    

    

}
