package com.xingzhi.apartment.service;

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
public class RoomInfoServiceTest {

    @Autowired
    private RoomInfoService roomInfoService;
    @Autowired
    private ApartmentService apartmentService;
    private RoomInfo testRoomInfo;
    private Apartment testApartment;


    @Before
    public void init() {
        testRoomInfo = new RoomInfo();
        testRoomInfo.setSize("test");
        testRoomInfo.setPriceRange("test");
        testApartment = new Apartment();
        testApartment.setName("TEST");
        testRoomInfo.setApartment(testApartment);
        apartmentService.save(testApartment);
        roomInfoService.save(testRoomInfo);
    }

    @After
    public void tearDown(){
        roomInfoService.deleteRoomInfoByNameSize(testApartment.getName(),testRoomInfo.getSize());
        apartmentService.deleteApartmentByName(testApartment.getName());
    }


    @Test
    public void getRoomInfos() {
        List<RoomInfo> roomInfos = roomInfoService.getRoomInfos();
        roomInfos.forEach(em -> System.out.println(em.toString()));
        Assert.assertNotNull(roomInfos);
    }

    @Test
    public void getRoomInfoByApartmentName() {
        List<RoomInfo> roomInfos = roomInfoService.getRoomInfoByApartmentName("A");
        roomInfos.forEach(em -> System.out.println(em.toString()));
        Assert.assertNotNull(roomInfos);
    }

    @Test
    public void updateRoomInfoPrice() {
        String priceRange = "$1-$2";
        testRoomInfo.setPriceRange(priceRange);
        int id = roomInfoService.getRoomInfoByNameSize(testApartment.getName(),testRoomInfo.getSize()).getId();
        roomInfoService.updateRoomInfoPrice(id, priceRange);
        RoomInfo roomInfo = roomInfoService.getRoomInfoByNameSize(testApartment.getName(),testRoomInfo.getSize());
        Assert.assertEquals(priceRange, roomInfo.getPriceRange());
    }

    @Test
    public void updateRoomInfo(){
        int id = roomInfoService.getRoomInfoByNameSize(testApartment.getName(),testRoomInfo.getSize()).getId();
        String size = "UPDATED!!!!!!!!!!!!!!!!!";
        testRoomInfo.setSize("UPDATED!!!!!!!!!!!!!!!!!");
        roomInfoService.updateRoomInfo(id, testRoomInfo);
        RoomInfo roomInfo = roomInfoService.getRoomInfoByNameSize(testApartment.getName(),testRoomInfo.getSize());
        Assert.assertEquals(size, roomInfo.getSize());
    }

    @Test
    public void getRoomInfoByNameSize(){
        RoomInfo roomInfo = roomInfoService.getRoomInfoByNameSize(testApartment.getName(),testRoomInfo.getSize());
        Assert.assertNotNull(roomInfo);
    }

    @Test
    public void getRoomInfoByNamePriceRange(){
        RoomInfo roomInfo = roomInfoService.getRoomInfoByNameSize(testApartment.getName(),testRoomInfo.getPriceRange());
        Assert.assertNotNull(roomInfo);
    }

}