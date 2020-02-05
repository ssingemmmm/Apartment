package com.xingzhi.apartment.model;
import java.util.ArrayList;
import java.util.List;

public class Apartment {
    private int id;
    private int propertyInfoId;
    private String name;
    private String lowestPrice;
    private String smallestSize;
    private String photo;
    private List<RoomInfo> roomInfos = new ArrayList();


    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLowestPrice(){ return lowestPrice; }

    public void setLowestPrice(String lowestPrice){ this.lowestPrice=lowestPrice; }

    public String getSmallestSize(){ return smallestSize; }

    public void setSmallestSize(String smallestSize){ this.smallestSize=smallestSize;}

    public String getPhoto(){
        return photo;
    }

    public void setPhoto(String photo){
        this.photo=photo;
    }

    public int getPropertyInfoId() { return propertyInfoId; }

    public void setPropertyInfoId(int propertyInfoId) { this.propertyInfoId = propertyInfoId; }

    public List<RoomInfo> getRoomInfos() {
        return roomInfos;
    }

    public void setRoomInfos(List<RoomInfo> roomInfos) {
        this.roomInfos = roomInfos;
    }
    @Override
    public String toString() {
        return String.format("[%d | %s | %s | %s]", id, name, lowestPrice, smallestSize, photo, propertyInfoId);
    }
}
