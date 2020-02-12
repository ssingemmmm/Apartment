package com.xingzhi.apartment.service;

import com.xingzhi.apartment.init.AppInitializer;
import com.xingzhi.apartment.model.Apartment;
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
        testApartment.setId(9999);
        testApartment.setName("test");
    }


    @Test
    public void saveAndDelete(){
        apartmentService.save(testApartment);
        int id = apartmentService.getApartmentById(testApartment.getId()).getId();
        Assert.assertEquals(testApartment.getId(),id);
        apartmentService.deleteApartmentByName(testApartment.getName());
    }

    @Test
    public void getApartments() {
        List<Apartment> apartments = apartmentService.getApartments();
        int expectedNumOfDept = 4;
        apartments.forEach(em -> System.out.println(em.toString()));
        Assert.assertEquals(expectedNumOfDept, apartments.size());
    }

    @Test
    public void getApartmentById() {
        int id = 2;
        Apartment apartment = apartmentService.getApartmentById(id);
        Assert.assertEquals(id, apartment.getId());
    }

    @Test
    public void getApartmentByName() {
        String name = "A";
        Apartment apartment = apartmentService.getApartmentByName(name);
        Assert.assertNotNull(apartment);
    }


    @Test
    public void updateApartmentLowestPrice() {
        String name = "A";
        String lowestPrice = "$1";
        String rightPrice = apartmentService.getApartmentByName(name).getLowestPrice();
        apartmentService.updateApartmentLowestPrice(name, lowestPrice);
        Apartment apartment = apartmentService.getApartmentByName(name);
        Assert.assertEquals(lowestPrice, apartment.getLowestPrice());
        apartmentService.updateApartmentLowestPrice(name,rightPrice);
    }

    @Test
    public void getApartmentByNameWithRoomInfo(){
        String name = "A";
        List<Object[]> results = apartmentService.getApartmentByNameWithAllRoomInfo(name);
        Assert.assertEquals(1,results.size());
    }

}
