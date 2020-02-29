package com.xingzhi.apartment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.io.Serializable;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "roominfo")
public class RoomInfo implements Serializable {

    public RoomInfo(String size, String priceRange, String layoutPhoto){
        this.size = size;
        this.priceRange = priceRange;
        this.layoutPhoto = layoutPhoto;
    }
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonView({Views.Public.class})
    private Integer id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_id")
    private Apartment apartment;

    @Column(name = "size")
    @JsonView({Views.Public.class})
    private String size;

    @Column(name = "price_range")
    @JsonView({Views.Public.class})
    private String priceRange;

    @Column(name = "layout_photo")
    @JsonView({Views.Public.class})
    private String layoutPhoto;

    public RoomInfo(){}

    public Integer getId(){ return id; }

    public void setId(Integer id){ this.id=id; }

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
