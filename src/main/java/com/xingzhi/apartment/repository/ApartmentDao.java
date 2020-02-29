package com.xingzhi.apartment.repository;

import com.xingzhi.apartment.model.Apartment;
import com.xingzhi.apartment.model.PropertyInfo;

import java.util.List;

public interface ApartmentDao {
    boolean save(Apartment apartment);
    int updateApartmentLowestPrice(Integer id, String lowestPrice);
    List<Apartment> getApartments();
    Apartment getApartmentByName(String name);
    Apartment getApartmentById(Integer id);
    boolean deleteApartmentByName(String name);
    boolean deleteApartmentById(Integer id);
    List<Apartment> getApartmentByNameWithAllRoomInfo(String name);
    int updateApartment(Integer id, Apartment apartment);
}
