package com.xingzhi.apartment.service;

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
@SpringBootTest(classes = AppInitializer.class)
public class ApartmentServiceTest {
    @Autowired
    private ApartmentService apartmentService;

    private Apartment testApartment;

    @Before
    public void init() {
        testApartment = new Apartment();
        testApartment.setName("test");
        apartmentService.save(testApartment);
    }

    @After
    public void tearDown(){
        apartmentService.deleteApartmentByName(testApartment.getName());
    }


    @Test
    public void getApartments() {
        List<Apartment> apartments = apartmentService.getApartments();
        apartments.forEach(em -> System.out.println(em.toString()));
    }

    @Test
    public void getApartmentByName() {
        Apartment apartment = apartmentService.getApartmentByName(testApartment.getName());
        Assert.assertNotNull(apartment);
    }


    @Test
    public void updateApartmentLowestPrice() {
        String lowestPrice="test";
        int a = apartmentService.updateApartmentLowestPrice(apartmentService.getApartmentByName(testApartment.getName()).getId(),lowestPrice);
        Assert.assertEquals(1,a);
    }

    @Test
    public void getApartmentByNameWithRoomInfo(){
        List<Apartment> results = apartmentService.getApartmentByNameWithAllRoomInfo(testApartment.getName());
        Assert.assertEquals(1,results.size());
    }

    @Test
    public void updateApartmentInfo(){
        testApartment.setLowestPrice("test");
        int a = apartmentService.updateApartmentInfo(apartmentService.getApartmentByName(testApartment.getName()).getId(),testApartment);
        Assert.assertEquals(1,a); }

}
