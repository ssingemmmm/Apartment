package com.xingzhi.apartment.repository;
import com.xingzhi.apartment.model.PropertyInfo;

import java.util.List;

public interface PropertyInfoDao {
    boolean save(PropertyInfo propertyInfo);
    int updatePropertyInfo(int id, PropertyInfo propertyInfo);
    List<PropertyInfo> getPropertyInfos();
    PropertyInfo getPropertyInfoById(int id);
    boolean deletePropertyInfoById(int id);
}
