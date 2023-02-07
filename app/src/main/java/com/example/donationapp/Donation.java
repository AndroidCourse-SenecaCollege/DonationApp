package com.example.donationapp;

public class Donation {

    String paymentMethod;
    double amount;
    boolean isShared;

    public Donation(String paymentMethod, double amount, boolean isShared) {
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.isShared = isShared;
    }
}
