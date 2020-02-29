package com.xingzhi.apartment.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "apartment")
public class Apartment implements Serializable {

    public Apartment(String name, String lowestPrice, String smallestSize, String photo) {
        this.name = name;
        this.lowestPrice = lowestPrice;
        this.smallestSize = smallestSize;
        this.photo = photo;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonView({Views.Public.class})
    private Integer id;

    @Column(name = "name")
    @JsonView({Views.Public.class})
    private String name;

    @Column(name = "lowest_price")
    @JsonView({Views.Public.class})
    private String lowestPrice;

    @Column(name = "smallest_size")
    @JsonView({Views.Public.class})
    private String smallestSize;

    @Column(name = "photo")
    @JsonView({Views.Public.class})
    private String photo;

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonView({Views.Internal.class})
    private Set<RoomInfo> roomInfos;

    @OneToOne(mappedBy = "apartment", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonIgnore
    private PropertyInfo propertyInfo;

    public Apartment() {}

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

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
        try {
            int size = roomInfos.size();
        }
        catch (Exception e) {
            return null;
        }
        return roomInfos;
    }

    public void setRoomInfos(Set<RoomInfo> roomInfos) {
//        for (RoomInfo r : roomInfos) {
//            if (r.getApartment() == null) r.setApartment(this);
//        }
        this.roomInfos = roomInfos;
    }

    public PropertyInfo getPropertyInfo(){ return propertyInfo; }

    public void setPropertyInfo(PropertyInfo propertyInfo){ this.propertyInfo = propertyInfo; }
    @Override
    public String toString() {
        return String.format("[%d | %s | %s | %s | %s]", id, name, lowestPrice, smallestSize, photo);
    }
}
