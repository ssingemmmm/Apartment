package com.xingzhi.apartment.service;

import com.xingzhi.apartment.init.AppInitializer;
import com.xingzhi.apartment.model.Apartment;
import com.xingzhi.apartment.model.PropertyInfo;
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
public class PropertyInfoServiceTest {
    @Autowired
    private PropertyInfoService propertyInfoService;
    @Autowired
    private ApartmentService apartmentService;
    private PropertyInfo testPropertyInfo;
    private PropertyInfo updatePropertyInfo;
    private Apartment testApartment;

    @Before
    public void init() {
        testPropertyInfo = new PropertyInfo();
        updatePropertyInfo = new PropertyInfo();
        testPropertyInfo.setAddress("test");
        testApartment = new Apartment();
        testApartment.setName("TEST");
        testPropertyInfo.setApartment(testApartment);
        apartmentService.save(testApartment);
        propertyInfoService.save(testPropertyInfo);
    }

    @After
    public void tearDown(){
        propertyInfoService.deletePropertyInfoByName("TEST");
        apartmentService.deleteApartmentByName("TEST");
    }

    @Test
    public void getPropertyInfos() {
        List<PropertyInfo> propertyInfos = propertyInfoService.getPropertyInfos();
        propertyInfos.forEach(em -> System.out.println(em.toString()));
    }

    @Test
    public void getPropertyInfoById() {
        Integer id = 1;
        PropertyInfo propertyInfo = propertyInfoService.getPropertyInfoById(id);
        Assert.assertEquals(id, propertyInfo.getId());
    }

    @Test
    public void getPropertyInfoByName() {
        PropertyInfo propertyInfo = propertyInfoService.getPropertyInfoByName("TEST");;
        Assert.assertNotNull(propertyInfo);
    }

    @Test
    public void updatePropertyInfo() {
        testPropertyInfo.setAddress("CHANGED");
        propertyInfoService.updatePropertyInfo(propertyInfoService.getPropertyInfoByName("TEST").getId(),testPropertyInfo);
        Assert.assertEquals("CHANGED", propertyInfoService.getPropertyInfoByName("TEST").getAddress());
    }
}
