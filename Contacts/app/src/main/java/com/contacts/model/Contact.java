package com.contacts.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Contact implements Parcelable
{

    @SerializedName("name")
    private String name;
    @SerializedName("company")
    private String company;
    @SerializedName("favorite")
    private String favorite;
    @SerializedName("smallImageURL")
    private String smallImageURL;
    @SerializedName("largeImageURL")
    private String largeImageURL;
    @SerializedName("email")
    private String email;
    @SerializedName("website")
    private String website;
    @SerializedName("phone")
    private Phone phone;
    @SerializedName("address")
    private Address address;

    public final static Parcelable.Creator<Contact> CREATOR = new Creator<Contact>() {

        public Contact createFromParcel(Parcel in) {
            Contact instance = new Contact();
            instance.name = ((String) in.readValue((String.class.getClassLoader())));
            instance.company = ((String) in.readValue((String.class.getClassLoader())));
            instance.favorite = ((String) in.readValue((Boolean.class.getClassLoader())));
            instance.smallImageURL = ((String) in.readValue((String.class.getClassLoader())));
            instance.largeImageURL = ((String) in.readValue((String.class.getClassLoader())));
            instance.email = ((String) in.readValue((String.class.getClassLoader())));
            instance.website = ((String) in.readValue((String.class.getClassLoader())));
            instance.phone = ((Phone) in.readValue((Phone.class.getClassLoader())));
            instance.address = ((Address) in.readValue((Address.class.getClassLoader())));
            return instance;
        }

        public Contact[] newArray(int size) {
            return (new Contact[size]);
        }

    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public String getSmallImageURL() {
        return smallImageURL;
    }

    public void setSmallImageURL(String smallImageURL) {
        this.smallImageURL = smallImageURL;
    }

    public String getLargeImageURL() {
        return largeImageURL;
    }

    public void setLargeImageURL(String largeImageURL) {
        this.largeImageURL = largeImageURL;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeValue(company);
        dest.writeValue(favorite);
        dest.writeValue(smallImageURL);
        dest.writeValue(largeImageURL);
        dest.writeValue(email);
        dest.writeValue(website);
        dest.writeValue(phone);
        dest.writeValue(address);
    }

    public int describeContents() {
        return 0;
    }

    public Boolean hasPhone()
    {
        if ((phone.getMobile() != null && phone.getMobile().length() > 0) ||
                (phone.getWork() != null && phone.getWork().length() > 0) ||
                (phone.getHome() != null && phone.getHome().length() > 0))
        {
            return true;
        }
        return false;
    }

    public String getDefaultPhone()
    {
        if (phone.getMobile() != null && phone.getMobile().length() > 0)
        {
            return phone.getMobile();
        }
        if (phone.getWork() != null && phone.getWork().length() > 0)
        {
            return phone.getWork();
        }
        return phone.getHome();
    }

}