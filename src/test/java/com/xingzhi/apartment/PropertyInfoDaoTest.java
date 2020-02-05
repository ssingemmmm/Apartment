package com.xingzhi.apartment;

import com.xingzhi.apartment.JDBC.ApartmentDao;
import com.xingzhi.apartment.JDBC.PropertyInfoDao;
import com.xingzhi.apartment.model.Apartment;
import com.xingzhi.apartment.model.PropertyInfo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PropertyInfoDaoTest {
    private ApartmentDao apartmentDao;
    private Apartment c;
    private PropertyInfoDao propertyInfoDao;
    private PropertyInfo a;
    private PropertyInfo b;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private String loggerInfo = System.getenv("logging.level.");
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
        c.setPropertyInfoId(1);
        apartmentDao.addApartment(c);
        propertyInfoDao = new PropertyInfoDao();
        a = new PropertyInfo();

        a.setApartmentId(9998);

        a.setEmail("abc@abc.com");

        a.setId(9998);
        a.setOfficeHours("closed");
        a.setPhoneNumber("123123123");
        a.setAddress("Abc st");
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
        for(int i=0;i<propertyInfos.size();i++) {
            logger.info(propertyInfos.get(i).getEmail());
        }
    }

    @Test
    public void searchPropertyInfoTest(){
        PropertyInfo b = propertyInfoDao.searchPropertyInfo(a.getId());
        logger.info("Searching for: "+a.getId()+" , Searching result returned: "+ b.getId());
    }

    @Test
    public void addPropertyInfoTest(){
        List<PropertyInfo> propertyInfos = propertyInfoDao.getPropertyInfo();
        int a=propertyInfos.size();
        b = new PropertyInfo();
        b.setId(9999);
        b.setApartmentId(9998);
        b.setEmail("abb@abc.com");
        b.setOfficeHours("closed");
        b.setPhoneNumber("123123124");
        b.setAddress("Abcd st");
        propertyInfoDao.addPropertyInfo(b);
        propertyInfos = propertyInfoDao.getPropertyInfo();
        int b=propertyInfos.size();
        logger.info("size before adding "+a+" , size after adding "+b);
        propertyInfoDao.deletePropertyInfo(9999);
        propertyInfos = propertyInfoDao.getPropertyInfo();
        logger.info("size before deleting "+b+" , size after deleting "+ propertyInfos.size() );
    }

    @Test
    public void deletePropertyInfoTest(){
        List<PropertyInfo> propertyInfos = propertyInfoDao.getPropertyInfo();
        int a=propertyInfos.size();
        b = new PropertyInfo();
        b.setId(9999);

        b.setApartmentId(9998);

        b.setEmail("abb@abc.com");

        b.setOfficeHours("closed");
        b.setPhoneNumber("123123124");
        b.setAddress("Abcd st");
        propertyInfoDao.addPropertyInfo(b);
        propertyInfos = propertyInfoDao.getPropertyInfo();
        int b=propertyInfos.size();
        logger.info("size before adding "+a+" , size after adding "+b);
        propertyInfoDao.deletePropertyInfo(9999);
        propertyInfos = propertyInfoDao.getPropertyInfo();
        logger.info("size before deleting "+b+" , size after deleting "+ propertyInfos.size() );
    }

    @Test
    public void updatePropertyInfoTest(){
        a.setEmail("changed!!!!");
        propertyInfoDao.updatePropertyInfo(a);
        PropertyInfo d = propertyInfoDao.searchPropertyInfo(9998);
        logger.info("name before update: best , name after update: "+d.getEmail());
    }

}