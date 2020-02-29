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
        testApartment.setName("test");
        apartmentDao.save(testApartment);
    }

    @After
    public void tearDown(){
        apartmentDao.deleteApartmentByName(testApartment.getName());
    }

    @Test
    public void getApartments() {
        List<Apartment> apartments = apartmentDao.getApartments();
        apartments.forEach(em -> System.out.println(em.toString()));
    }

    @Test
    public void getApartmentById() {
        Integer id = 2;
        Apartment apartment = apartmentDao.getApartmentById(id);
        Assert.assertEquals(id, apartment.getId());
    }

    @Test
    public void getApartmentByName() {
        Apartment apartment = apartmentDao.getApartmentByName(testApartment.getName());
        Assert.assertNotNull(apartment);
    }

    @Test
    public void updateApartmentLowestPrice() {
        String lowestPrice="test";
        int a = apartmentDao.updateApartmentLowestPrice(apartmentDao.getApartmentByName(testApartment.getName()).getId(),lowestPrice);
        Assert.assertEquals(1,a);
    }

    @Test
    public void getApartmentByNameWithRoomInfo(){
        List<Apartment> results = apartmentDao.getApartmentByNameWithAllRoomInfo(testApartment.getName());
        Assert.assertEquals(1,results.size());
    }

}
