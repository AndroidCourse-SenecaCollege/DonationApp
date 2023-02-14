package com.example.donationapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Donation implements Parcelable {

    String paymentMethod;
    double amount;
    boolean isShared;


    public Donation(String paymentMethod, double amount, boolean isShared) {
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.isShared = isShared;
    }

    protected Donation(Parcel in) {
        paymentMethod = in.readString();
        amount = in.readDouble();
        isShared = in.readByte() != 0;

    }

    public static final Creator<Donation> CREATOR = new Creator<Donation>() {
        @Override
        public Donation createFromParcel(Parcel in) {
            return new Donation(in);
        }

        @Override
        public Donation[] newArray(int size) {
            return new Donation[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(paymentMethod);
        parcel.writeDouble(amount);
        parcel.writeByte((byte) (isShared ? 1 : 0));

    }
}
