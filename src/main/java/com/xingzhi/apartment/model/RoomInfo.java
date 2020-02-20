package com.xingzhi.apartment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "roominfo")
public class RoomInfo extends Model{

    public RoomInfo(int id, String size, String priceRange, String layoutPhoto){
        this.id = id;
        this.size = size;
        this.priceRange = priceRange;
        this.layoutPhoto = layoutPhoto;
    }
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    @Column(name = "size")
    private String size;

    @Column(name = "price_range")
    private String priceRange;

    @Column(name = "layout_photo")
    private String layoutPhoto;

    public RoomInfo(){}

    public int getId(){ return id; }

    public void setId(int id){ this.id=id; }

    public String getSize(){
        return size;
    }

    public void setSize(String size){
        this.size=size;
    }

    public String getPriceRange(){
        return priceRange;
    }

    public void setPriceRange(String priceRange){
        this.priceRange=priceRange;
    }

    public String getLayoutPhoto(){
        return layoutPhoto;
    }

    public void setLayoutPhoto(String layoutPhoto){
        this.layoutPhoto=layoutPhoto;
    }

    public Apartment getApartment(){ return apartment; }

    public void setApartment(Apartment apartment){ this.apartment = apartment; }
}
