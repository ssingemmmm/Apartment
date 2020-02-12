package com.xingzhi.apartment.service;

import com.xingzhi.apartment.model.Apartment;

import java.util.List;

public interface ApartmentService {
    boolean save(Apartment apartment);
    int updateApartmentLowestPrice(String name, String lowestPrice);
    List<Apartment> getApartments();
    Apartment getApartmentByName(String name);
    Apartment getApartmentById(int id);
    boolean deleteApartmentByName(String name);
    List<Apartment> getApartmentByNameWithAllRoomInfo(String name);
}
