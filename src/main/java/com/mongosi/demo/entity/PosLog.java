package com.mongosi.demo.entity;

public class PosLog {
    private String id;
    private String customerName;
    private String storeName;
    private Integer amount;
    private boolean premium;

    public String getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getStoreName() {
        return storeName;
    }

    public Integer getAmount() {
        return amount;
    }

    public boolean isPremium() {
        return premium;
    }

    public PosLog(String customerName, String storeName, Integer amount, boolean premium) {
        this.id = String.valueOf(Math.random());
        this.customerName = customerName;
        this.storeName = storeName;
        this.amount = amount;
        this.premium = premium;
    }

    public PosLog() {
    }
}
