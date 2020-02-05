package com.xingzhi.apartment.model;

public class PropertyInfo {
    private int id;
    private int apartmentId;
    private String phoneNumber;
    private String address;
    private String email;
    private String officeHours;
    private Apartment apartment;

    public int getId(){ return id; }

    public void setId(int id){ this.id=id; }

    public int getApartmentid(){ return apartmentId; }

    public void setApartmentId(int apartmentId){ this.apartmentId=apartmentId; }

    public String getPhoneNumber(){ return phoneNumber; }

    public void setPhoneNumber(String phoneNumber){ this.phoneNumber=phoneNumber; }

    public String getAddress(){ return address; }

    public void setAddress(String streetAddress){ this.address=address; }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public String getOfficeHours(){
        return officeHours;
    }

    public void setOfficeHours(String officeHours){
        this.officeHours=officeHours;
    }

}
