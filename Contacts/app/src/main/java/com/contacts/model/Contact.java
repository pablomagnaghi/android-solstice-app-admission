package com.contacts.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
    @SerializedName("birthdate")
    private String birthdate;
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
            instance.birthdate = ((String) in.readValue((String.class.getClassLoader())));
            instance.phone = ((Phone) in.readValue((Phone.class.getClassLoader())));
            instance.address = ((Address) in.readValue((Address.class.getClassLoader())));
            return instance;
        }

        public Contact[] newArray(int size) {
            return (new Contact[size]);
        }

    };

    public Contact()
    {
        phone = new Phone();
        address = new Address();
    }

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

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
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
        dest.writeValue(birthdate);
        dest.writeValue(phone);
        dest.writeValue(address);
    }

    public int describeContents() {
        return 0;
    }

    public Boolean hasMobilePhone() {
        if (phone.getMobile() != null && phone.getMobile().length() > 0)
        {
            return true;
        }
        return false;
    }

    public Boolean hasWorkPhone() {
        if (phone.getWork() != null && phone.getWork().length() > 0)
        {
            return true;
        }
        return false;
    }

    public Boolean hasHomePhone() {
        if (phone.getHome() != null && phone.getHome().length() > 0)
        {
            return true;
        }
        return false;
    }

    public Boolean hasPhone()
    {
        if (hasMobilePhone() || hasWorkPhone() || hasHomePhone())
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

    public String getMobilePhone()
    {
        return phone.getMobile();
    }

    public String getWorkPhone()
    {
        return phone.getWork();
    }

    public String getHomePhone()
    {
        return phone.getHome();
    }

    public void setMobilePhone(String mobile)
    {
        phone.setMobile(mobile);
    }

    public void setWorkPhone(String work)
    {
        phone.setWork(work);
    }

    public void setHomePhone(String home)
    {
        phone.setHome(home);
    }

    public String getAddressWithFormat()
    {
        return address.getStreet() + "\n" + address.getCity() + ", " + address.getState() + " " + address.getZip();
    }

    public String getBirthdateWithFormat()
    {
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            Date date = originalFormat.parse(birthdate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            String monthString = new DateFormatSymbols().getMonths()[month];
            return monthString + " " + String.valueOf(day) + ", " + String.valueOf(year);
        } catch (ParseException ex) {
            return "";
        }
    }

    public Boolean isFavorite() {
        return favorite.equals("true") || favorite.equals("1");
    }
}