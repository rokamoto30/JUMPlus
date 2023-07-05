package com.furnitureApp.dto;


public class newPrododuct {
    private String name;
    private String imgUrl;
    private Integer quantity;
    private Double price;

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

    

    

}
