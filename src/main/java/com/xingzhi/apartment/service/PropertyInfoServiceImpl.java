package com.xingzhi.apartment.service;

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

    @Autowired
    public PropertyInfoServiceImpl(PropertyInfoDao propertyInfoDao){
        this.propertyInfoDao = propertyInfoDao;
    }
    @Override
    public boolean save(PropertyInfo propertyInfo) {
        return propertyInfoDao.save(propertyInfo);
    }

    @Override
    public int updatePropertyInfo(int id, PropertyInfo propertyInfo) {
        return propertyInfoDao.updatePropertyInfo(id, propertyInfo);
    }

    @Override
    public List<PropertyInfo> getPropertyInfos() {
        return propertyInfoDao.getPropertyInfos();
    }

    @Override
    public PropertyInfo getPropertyInfoById(int id) {
        return propertyInfoDao.getPropertyInfoById(id);
    }

    @Override
    public boolean deletePropertyInfoById(int id) {
        return propertyInfoDao.deletePropertyInfoById(id);
    }
}
