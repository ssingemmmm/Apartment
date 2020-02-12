package com.xingzhi.apartment.repository;

import com.xingzhi.apartment.init.AppInitializer;
import com.xingzhi.apartment.model.Apartment;
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
public class ApartmentDaoTest {
    @Autowired
    private ApartmentDao apartmentDao;
    @Autowired
    private RoomInfoDao roomInfoDao;
    private Apartment testApartment;

    @Before
    public void init() {
        testApartment = new Apartment();
        testApartment.setId(9999);
        testApartment.setName("test");
    }


    @Test
    public void saveAndDelete(){
        apartmentDao.save(testApartment);
        int id = apartmentDao.getApartmentById(testApartment.getId()).getId();
        Assert.assertEquals(testApartment.getId(),id);
        apartmentDao.deleteApartmentByName(testApartment.getName());
    }

    @Test
    public void getApartments() {
        List<Apartment> apartments = apartmentDao.getApartments();
        int expectedNumOfDept = 4;
        apartments.forEach(em -> System.out.println(em.toString()));
        Assert.assertEquals(expectedNumOfDept, apartments.size());
    }

    @Test
    public void getApartmentById() {
        int id = 2;
        Apartment apartment = apartmentDao.getApartmentById(id);
        Assert.assertEquals(id, apartment.getId());
    }

    @Test
    public void getApartmentByName() {
        String name = "A";
        Apartment apartment = apartmentDao.getApartmentByName(name);
        Assert.assertNotNull(apartment);
    }


    @Test
    public void updateApartmentLowestPrice() {
        String name = "A";
        String lowestPrice = "$1";
        String rightPrice = apartmentDao.getApartmentByName(name).getLowestPrice();
        apartmentDao.updateApartmentLowestPrice(name, lowestPrice);
        Apartment apartment = apartmentDao.getApartmentByName(name);
        Assert.assertEquals(lowestPrice, apartment.getLowestPrice());
        apartmentDao.updateApartmentLowestPrice(name,rightPrice);
    }

    @Test
    public void getApartmentByNameWithRoomInfo(){
        String name = "A";
        List<Apartment> results = apartmentDao.getApartmentByNameWithAllRoomInfo(name);
        Assert.assertEquals(1,results.size());
    }

}
