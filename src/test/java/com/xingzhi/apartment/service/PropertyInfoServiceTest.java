package com.xingzhi.apartment.service;

import com.xingzhi.apartment.init.AppInitializer;
import com.xingzhi.apartment.model.PropertyInfo;
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
    public void saveAndDelete() {
        propertyInfoService.save(testPropertyInfo);
        int id = propertyInfoService.getPropertyInfoById(testPropertyInfo.getId()).getId();
        Assert.assertEquals(testPropertyInfo.getId(), id);
        propertyInfoService.deletePropertyInfoById(id);
    }

    @Test
    public void getPropertyInfos() {
        List<PropertyInfo> propertyInfos = propertyInfoService.getPropertyInfos();
        int expectedNumOfRoomInfos = 4;
        propertyInfos.forEach(em -> System.out.println(em.toString()));
        Assert.assertEquals(expectedNumOfRoomInfos, propertyInfos.size());
    }

    @Test
    public void getPropertyInfoById() {
        int id = 2;
        PropertyInfo propertyInfo = propertyInfoService.getPropertyInfoById(id);
        Assert.assertEquals(id, propertyInfo.getId());
    }

    @Test
    public void updatePropertyInfo() {
        int id = 1;
        PropertyInfo rightPropertyInfo = propertyInfoService.getPropertyInfoById(id);
        String address = "UPDATED!!!!!!!!!!!!!!!!!";
        updatePropertyInfo.setAddress("UPDATED!!!!!!!!!!!!!!!!!");
        propertyInfoService.updatePropertyInfo(id, updatePropertyInfo);
        PropertyInfo propertyInfo = propertyInfoService.getPropertyInfoById(id);
        Assert.assertEquals(address, propertyInfo.getAddress());
        propertyInfoService.updatePropertyInfo(id, rightPropertyInfo);
    }
}
