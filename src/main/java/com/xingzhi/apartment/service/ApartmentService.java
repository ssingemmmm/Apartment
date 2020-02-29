package com.xingzhi.apartment.service;

import com.xingzhi.apartment.model.Apartment;

import java.util.List;

public interface ApartmentService {
    boolean save(Apartment apartment);
    int updateApartmentLowestPrice(Integer id, String lowestPrice);
    int updateApartmentInfo(Integer id, Apartment apartment);
    List<Apartment> getApartments();
    Apartment getApartmentByName(String name);
    boolean deleteApartmentByName(String name);
    List<Apartment> getApartmentByNameWithAllRoomInfo(String name);
    Apartment getApartmentById(Integer id);
    boolean deleteApartmentById(Integer id);
}
