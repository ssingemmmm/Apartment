package com.xingzhi.apartment.repository;

import com.xingzhi.apartment.init.AppInitializer;
import com.xingzhi.apartment.model.Apartment;
import com.xingzhi.apartment.model.PropertyInfo;
import com.xingzhi.apartment.model.RoomInfo;
import org.junit.After;
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
    @Autowired
    private ApartmentDao apartmentDao;
    private Apartment testApartment;
    private PropertyInfo testPropertyInfo;
    private PropertyInfo updatePropertyInfo;


    @Before
    public void init() {
        testPropertyInfo = new PropertyInfo();
        updatePropertyInfo = new PropertyInfo();
        testPropertyInfo.setAddress("test");
        testApartment = new Apartment();
        testApartment.setName("TEST");
        testPropertyInfo.setApartment(testApartment);
        apartmentDao.save(testApartment);
        propertyInfoDao.save(testPropertyInfo);
    }

    @After
    public void tearDown(){
        propertyInfoDao.deletePropertyInfoById(propertyInfoDao.getPropertyInfoByName("TEST").getId());
        apartmentDao.deleteApartmentByName("TEST");
    }
    @Test
    public void saveAndDelete(){
        String address = propertyInfoDao.getPropertyInfoByName(testApartment.getName()).getAddress();
        Assert.assertEquals(testPropertyInfo.getAddress(),address);
    }

    @Test
    public void getPropertyInfos() {
        List<PropertyInfo> propertyInfos = propertyInfoDao.getPropertyInfos();
        propertyInfos.forEach(em -> System.out.println(em.toString()));
        Assert.assertNotNull(propertyInfos.size());
    }

    @Test
    public void getPropertyInfoById() {
        Integer id = 1;
        PropertyInfo propertyInfo = propertyInfoDao.getPropertyInfoById(id);
        Assert.assertEquals(id, propertyInfo.getId());
    }

    @Test
    public void getPropertyInfoByName(){
        PropertyInfo propertyInfo = propertyInfoDao.getPropertyInfoByName("TEST");
        Assert.assertNotNull(propertyInfo);
    }

    @Test
    public void updatePropertyInfo() {
        testPropertyInfo.setAddress("UPDATED!!!!!!!!!!!!!!!!!");
        propertyInfoDao.updatePropertyInfo(propertyInfoDao.getPropertyInfoByName("TEST").getId(), testPropertyInfo);
        PropertyInfo propertyInfo = propertyInfoDao.getPropertyInfoByName("TEST");
        Assert.assertEquals("UPDATED!!!!!!!!!!!!!!!!!", propertyInfo.getAddress());
    }
}
