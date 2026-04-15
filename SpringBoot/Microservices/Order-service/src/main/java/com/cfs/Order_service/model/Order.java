package com.cfs.Order_service.model;

public class Order {

    private Long id;
    private Long userId;
    private String product;
    private Double amount;

    public Order() {};

    public Order(Long id, Long userId, String product, Double amount) {
        this.id = id;
        this.userId = userId;
        this.product = product;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
