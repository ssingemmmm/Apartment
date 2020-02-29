package com.xingzhi.apartment.service;

import com.xingzhi.apartment.model.Apartment;
import com.xingzhi.apartment.repository.ApartmentDao;
import com.xingzhi.apartment.repository.PropertyInfoDao;
import com.xingzhi.apartment.model.PropertyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PropertyInfoServiceImpl implements PropertyInfoService {

    private PropertyInfoDao propertyInfoDao;
    private ApartmentDao apartmentDao;

    @Autowired
    public PropertyInfoServiceImpl(PropertyInfoDao propertyInfoDao, ApartmentDao apartmentDao){
        this.propertyInfoDao = propertyInfoDao;
        this.apartmentDao = apartmentDao;
    }

    @Override
    public boolean saveByName(String name, PropertyInfo propertyInfo) {
        Apartment apartment = apartmentDao.getApartmentByName(name);
        propertyInfo.setApartment(apartment);
        return propertyInfoDao.save(propertyInfo);
    }

    @Override
    public boolean save(PropertyInfo propertyInfo){
        return propertyInfoDao.save(propertyInfo);
    }

    @Override
    public int updatePropertyInfo(Integer id, PropertyInfo propertyInfo) {
        return propertyInfoDao.updatePropertyInfo(id, propertyInfo);
    }

    @Override
    public List<PropertyInfo> getPropertyInfos() {
        return propertyInfoDao.getPropertyInfos();
    }

    @Override
    public PropertyInfo getPropertyInfoById(Integer id){ return propertyInfoDao.getPropertyInfoById(id);}

    @Override
    public PropertyInfo getPropertyInfoByName(String name) {
        return propertyInfoDao.getPropertyInfoByName(name);
    }

    @Override
    public boolean deletePropertyInfoByName(String name) {
        Integer id = getPropertyInfoByName(name).getId();
        return propertyInfoDao.deletePropertyInfoById(id);
    }

    @Override
    public boolean deletePropertyInfoById(Integer id) {
        return propertyInfoDao.deletePropertyInfoById(id);
    }

    @Override
    public boolean saveById(Integer id, PropertyInfo propertyInfo){
        Apartment apartment = apartmentDao.getApartmentById(id);
        propertyInfo.setApartment(apartment);
        return propertyInfoDao.save(propertyInfo);
    }

}
