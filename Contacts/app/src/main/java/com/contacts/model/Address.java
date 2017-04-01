package com.contacts.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Address implements Parcelable
{

    @SerializedName("street")
    private String street;
    @SerializedName("city")
    private String city;
    @SerializedName("state")
    private String state;
    @SerializedName("country")
    private String country;
    @SerializedName("zip")
    private String zip;
    @SerializedName("latitude")
    private Double latitude;
    @SerializedName("longitude")
    private Double longitude;

    public final static Parcelable.Creator<Address> CREATOR = new Creator<Address>() {

        public Address createFromParcel(Parcel in) {
            Address instance = new Address();
            instance.street = ((String) in.readValue((String.class.getClassLoader())));
            instance.city = ((String) in.readValue((String.class.getClassLoader())));
            instance.state = ((String) in.readValue((String.class.getClassLoader())));
            instance.country = ((String) in.readValue((String.class.getClassLoader())));
            instance.zip = ((String) in.readValue((String.class.getClassLoader())));
            instance.latitude = ((Double) in.readValue((Double.class.getClassLoader())));
            instance.longitude = ((Double) in.readValue((Double.class.getClassLoader())));
            return instance;
        }

        public Address[] newArray(int size) {
            return (new Address[size]);
        }

    };

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(street);
        dest.writeValue(city);
        dest.writeValue(state);
        dest.writeValue(country);
        dest.writeValue(zip);
        dest.writeValue(latitude);
        dest.writeValue(longitude);
    }

    public int describeContents() {
        return 0;
    }

}