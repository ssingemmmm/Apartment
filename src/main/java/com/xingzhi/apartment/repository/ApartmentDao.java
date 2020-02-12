package com.xingzhi.apartment.repository;

import com.xingzhi.apartment.model.Apartment;

import java.util.List;

public interface ApartmentDao {
    boolean save(Apartment apartment);
    int updateApartmentLowestPrice(String name, String lowestPrice);
    List<Apartment> getApartments();
    Apartment getApartmentByName(String name);
    Apartment getApartmentById(int id);
    boolean deleteApartmentByName(String name);
    List<Apartment> getApartmentByNameWithAllRoomInfo(String name);
}
