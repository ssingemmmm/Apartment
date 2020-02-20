package com.xingzhi.apartment.repository;

import com.xingzhi.apartment.init.AppInitializer;
import com.xingzhi.apartment.model.PropertyInfo;
import com.xingzhi.apartment.model.RoomInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= AppInitializer.class)
public class PropertyInfoDaoTest {
    @Autowired
    private PropertyInfoDao propertyInfoDao;
    private PropertyInfo testPropertyInfo;
    private PropertyInfo updatePropertyInfo;


    @Before
    public void init() {
        testPropertyInfo = new PropertyInfo();
        updatePropertyInfo = new PropertyInfo();
        testPropertyInfo.setId(9999);
        testPropertyInfo.setAddress("test");

    }

    @Test
    public void saveAndDelete(){
        propertyInfoDao.save(testPropertyInfo);
        int id = propertyInfoDao.getPropertyInfoById(testPropertyInfo.getId()).getId();
        Assert.assertEquals(testPropertyInfo.getId(),id);
        propertyInfoDao.deletePropertyInfoById(id);
    }

    @Test
    public void getPropertyInfos() {
        List<PropertyInfo> propertyInfos = propertyInfoDao.getPropertyInfos();
        int expectedNumOfRoomInfos = 4;
        propertyInfos.forEach(em -> System.out.println(em.toString()));
        Assert.assertEquals(expectedNumOfRoomInfos, propertyInfos.size());
    }

    @Test
    public void getPropertyInfoById() {
        int id = 2;
        PropertyInfo propertyInfo = propertyInfoDao.getPropertyInfoById(id);
        Assert.assertEquals(id, propertyInfo.getId());
    }

    @Test
    public void updatePropertyInfo() {
        int id = 1;
        PropertyInfo rightPropertyInfo = propertyInfoDao.getPropertyInfoById(id);
        String address = "UPDATED!!!!!!!!!!!!!!!!!";
        updatePropertyInfo.setAddress("UPDATED!!!!!!!!!!!!!!!!!");
        propertyInfoDao.updatePropertyInfo(id, updatePropertyInfo );
        PropertyInfo propertyInfo = propertyInfoDao.getPropertyInfoById(id);
        Assert.assertEquals(address, propertyInfo.getAddress());
        propertyInfoDao.updatePropertyInfo(id, rightPropertyInfo);
    }
}