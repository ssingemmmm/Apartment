package com.xingzhi.apartment.service;

import com.xingzhi.apartment.model.Apartment;
import com.xingzhi.apartment.repository.ApartmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ApartmentServiceImpl implements ApartmentService {

    private ApartmentDao apartmentDao;

    @Autowired
    public ApartmentServiceImpl(ApartmentDao apartmentDao){
        this.apartmentDao = apartmentDao;
    }

    @Override
    public boolean save(Apartment apartment) {
        return apartmentDao.save(apartment);
    }

    @Override
    public int updateApartmentLowestPrice(Integer id, String lowestPrice) {
        return apartmentDao.updateApartmentLowestPrice(id,lowestPrice);
    }

    @Override
    public List<Apartment> getApartments() {
        return apartmentDao.getApartments();
    }

    @Override
    public Apartment getApartmentByName(String name) {
        return apartmentDao.getApartmentByName(name);
    }

    @Override
    public boolean deleteApartmentByName(String name) {
        return apartmentDao.deleteApartmentByName(name);
    }

    @Override
    public List<Apartment> getApartmentByNameWithAllRoomInfo(String name) {
        return apartmentDao.getApartmentByNameWithAllRoomInfo(name);
    }

    @Override
    public int updateApartmentInfo(Integer id, Apartment apartment){
        return apartmentDao.updateApartment(id, apartment);
    }

    @Override
    public Apartment getApartmentById(Integer id){ return apartmentDao.getApartmentById(id);}

    @Override
    public boolean deleteApartmentById(Integer id){ return apartmentDao.deleteApartmentById(id);}
}
