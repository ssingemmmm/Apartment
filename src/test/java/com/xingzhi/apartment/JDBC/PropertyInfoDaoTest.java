package com.xingzhi.apartment.JDBC;

import com.xingzhi.apartment.JDBC.ApartmentDao;
import com.xingzhi.apartment.JDBC.PropertyInfoDao;
import com.xingzhi.apartment.model.Apartment;
import com.xingzhi.apartment.model.PropertyInfo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PropertyInfoDaoTest {
    private ApartmentDao apartmentDao;
    private Apartment c;
    private Apartment d;
    private PropertyInfoDao propertyInfoDao;
    private PropertyInfo a;
    private PropertyInfo b;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

   /* @BeforeClass
    public void classSetup(){

    }

    @AfterClass
    public void classTearDown(){

    }*/

    @Before
    public void init(){
        apartmentDao = new ApartmentDao();
        c = new Apartment();
        c.setId(9998);
        c.setLowestPrice("$2000");
        c.setName("ABC Apartment");
        c.setSmallestSize("studio");
        c.setPhoto("No Photo Available");
        apartmentDao.addApartment(c);

        propertyInfoDao = new PropertyInfoDao();
        a = new PropertyInfo();
        a.setEmail("abc@abc.com");
        a.setId(9998);
        a.setOfficeHours("closed");
        a.setPhoneNumber("123123123");
        a.setAddress("Abc st");
        a.setApartment(c);
        propertyInfoDao.addPropertyInfo(a);
    }

    @After
    public void tearDown(){

        propertyInfoDao.deletePropertyInfo(a.getId());
        apartmentDao.deleteApartment(c.getId());
    }

    @Test
    public void getPropertyInfoTest(){
        List<PropertyInfo> propertyInfos = propertyInfoDao.getPropertyInfo();
        Assert.assertNotNull(propertyInfos);
    }

    @Test
    public void searchPropertyInfoTest(){
        PropertyInfo b = propertyInfoDao.searchPropertyInfo(a.getId());
        Assert.assertEquals(b.getEmail(),a.getEmail());
    }

    @Test
    public void addAndDeletePropertyInfoTest(){
        List<PropertyInfo> propertyInfos = propertyInfoDao.getPropertyInfo();
        int a=propertyInfos.size();
        b = new PropertyInfo();
        b.setId(9999);
        b.setEmail("abb@abc.com");
        b.setOfficeHours("closed");
        b.setPhoneNumber("123123124");
        b.setAddress("Abcd st");
        d = new Apartment();
        d.setId(9999);
        d.setLowestPrice("$2000");
        d.setName("ABCDEFG Apartment");
        d.setSmallestSize("studio");
        d.setPhoto("No Photo Available");
        apartmentDao.addApartment(d);
        b.setApartment(d);
        propertyInfoDao.addPropertyInfo(b);
        propertyInfos = propertyInfoDao.getPropertyInfo();
        int b=propertyInfos.size();
        Assert.assertNotEquals(a,b);
        propertyInfoDao.deletePropertyInfo(9999);
        apartmentDao.deleteApartment(9999);
        propertyInfos = propertyInfoDao.getPropertyInfo();
        b= propertyInfos.size();
        Assert.assertEquals(a,b);
    }

    @Test
    public void updatePropertyInfoTest(){
        a.setEmail("changed!!!!");
        propertyInfoDao.updatePropertyInfo(a);
        PropertyInfo d = propertyInfoDao.searchPropertyInfo(9998);
        Assert.assertEquals("changed!!!!",d.getEmail());
    }

}