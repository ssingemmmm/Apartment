package com.xingzhi.apartment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "propertyinfo")
public class PropertyInfo implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonView({Views.Public.class})
    private Integer id;

    @Column(name = "phone_number")
    @JsonView({Views.Public.class})
    private String phoneNumber;

    @Column(name = "address")
    @JsonView({Views.Public.class})
    private String address;

    @Column(name = "email")
    @JsonView({Views.Public.class})
    private String email;

    @Column(name = "office_hours")
    @JsonView({Views.Public.class})
    private String officeHours;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_id")
    @JsonIgnore
    private Apartment apartment;

    public PropertyInfo(String phoneNumber, String address, String email, String officeHours) {
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.officeHours = officeHours;
    }

    public PropertyInfo() {}

    public Integer getId(){ return id; }

    public void setId(Integer id){ this.id=id; }

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
