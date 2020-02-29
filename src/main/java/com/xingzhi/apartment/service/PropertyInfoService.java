package com.xingzhi.apartment.service;

import com.xingzhi.apartment.model.PropertyInfo;

import java.util.List;

public interface PropertyInfoService {
    boolean saveByName(String name, PropertyInfo propertyInfo);
    boolean saveById(Integer id, PropertyInfo propertyInfo);
    int updatePropertyInfo(Integer id, PropertyInfo propertyInfo);
    List<PropertyInfo> getPropertyInfos();
    PropertyInfo getPropertyInfoById(Integer id);
    PropertyInfo getPropertyInfoByName(String name);
    boolean deletePropertyInfoByName(String name);
    boolean deletePropertyInfoById(Integer id);
    boolean save(PropertyInfo propertyInfo);
}
