package com.xingzhi.apartment.model;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings({"JpaDataSourceORMInspection", "JpaAttributeTypeInspection", "JpaModelReferenceInspection"})
@Entity
@Table(name = "apartment")
public class Apartment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "lowest_price")
    private String lowestPrice;

    @Column(name = "smallest_size")
    private String smallestSize;

    @Column(name = "photo")
    private String photo;

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<RoomInfo> roomInfos;

    @OneToOne(mappedBy = "apartment", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private PropertyInfo propertyInfo;

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

    public Set<RoomInfo> getRoomInfos() {
        return roomInfos;
    }

    public void setRoomInfos(Set<RoomInfo> roomInfos) {
        this.roomInfos = roomInfos;
    }

    public PropertyInfo getPropertyInfo(){ return propertyInfo; }

    public void setPropertyInfo(PropertyInfo propertyInfo){ this.propertyInfo = propertyInfo; }
    @Override
    public String toString() {
        return String.format("[%d | %s | %s | %s | %s]", id, name, lowestPrice, smallestSize, photo);
    }
}
