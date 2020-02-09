package com.xingzhi.apartment.JDBC;

import com.xingzhi.apartment.JDBC.ApartmentDao;
import com.xingzhi.apartment.model.Apartment;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ApartmentDaoTest {
    private ApartmentDao apartmentDao;
    private Apartment a;
    private Apartment b;
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
        a = new Apartment();
        a.setId(9998);
        a.setLowestPrice("$2000");
        a.setName("ABC Apartment");
        a.setSmallestSize("studio");
        a.setPhoto("No Photo Available");

        apartmentDao.addApartment(a);
    }

    @After
    public void tearDown(){
        apartmentDao.deleteApartment(a.getId());
    }

    @Test
    public void getApartmentTest(){
        List<Apartment> apartments = apartmentDao.getApartment();
        for(int i=0;i<apartments.size();i++) {
            logger.info(apartments.get(i).getName());
        }
    }

    @Test
    public void searchApartmentTest(){
        Apartment b = apartmentDao.searchApartment(a.getId());
        logger.info("Searching for: "+a.getName()+" , Searching result returned: "+ b.getName());
    }

    @Test
    public void addApartmentTest(){
        List<Apartment> apartments = apartmentDao.getApartment();
        int a=apartments.size();
        b = new Apartment();
        b.setId(9999);
        b.setSmallestSize("one bed");
        b.setPhoto("no");
        b.setName("DDD");
        b.setLowestPrice("$10000");

        apartmentDao.addApartment(b);
        apartments = apartmentDao.getApartment();
        int b=apartments.size();
        logger.info("size before adding "+a+" , size after adding "+b);
        apartmentDao.deleteApartment(9999);
        apartments = apartmentDao.getApartment();
        logger.info("size before deleting "+b+" , size after deleting "+ apartments.size() );
    }

    @Test
    public void updateApartmentTest(){
        a.setName("changed!!!!");
        apartmentDao.updateApartment(a);
        Apartment d = apartmentDao.searchApartment(9998);
        logger.info("name before update: abc , name after update: "+d.getName());

    }


}