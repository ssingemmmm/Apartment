package com.xingzhi.apartment.repository;
import com.xingzhi.apartment.model.PropertyInfo;

import java.util.List;

public interface PropertyInfoDao {
    boolean save(PropertyInfo propertyInfo);
    int updatePropertyInfo(Integer id, PropertyInfo propertyInfo);
    List<PropertyInfo> getPropertyInfos();
    PropertyInfo getPropertyInfoByName(String name);
    PropertyInfo getPropertyInfoById(Integer id);
    boolean deletePropertyInfoById(Integer id);
}
