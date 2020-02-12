package com.xingzhi.apartment.service;

import com.xingzhi.apartment.model.PropertyInfo;

import java.util.List;

public interface PropertyInfoService {
    boolean save(PropertyInfo propertyInfo);
    int updatePropertyInfo(int id, PropertyInfo propertyInfo);
    List<PropertyInfo> getPropertyInfos();
    PropertyInfo getPropertyInfoById(int id);
    boolean deletePropertyInfoById(int id);
}
