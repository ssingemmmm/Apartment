package com.xingzhi.apartment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "propertyinfo")
public class PropertyInfo extends Model{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "office_hours")
    private String officeHours;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_id")
    @JsonIgnore
    private Apartment apartment;

    public PropertyInfo(int id, String phoneNumber, String address, String email, String officeHours) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.officeHours = officeHours;
    }

    public PropertyInfo() {}

    public int getId(){ return id; }

    public void setId(int id){ this.id=id; }

    public String getPhoneNumber(){ return phoneNumber; }

    public void setPhoneNumber(String phoneNumber){ this.phoneNumber=phoneNumber; }

    public String getAddress(){ return address; }

    public void setAddress(String address){ this.address=address; }

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

    public Apartment getApartment(){ return apartment; }

    public void setApartment(Apartment apartment){ this.apartment = apartment; }

}
