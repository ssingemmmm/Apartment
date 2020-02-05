package com.xingzhi.apartment.model;

public class RoomInfo {
    private int id;
    private int apartmentId;
    private String size;
    private String priceRange;
    private String layoutPhoto;

    public int getId(){ return id; }

    public void setId(int id){ this.id=id; }

    public int getApartmentid(){ return apartmentId; }

    public void setApartmentId(int apartmentId){ this.apartmentId=apartmentId; }

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
}
