package com.xingzhi.apartment.service;

import com.xingzhi.apartment.init.AppInitializer;
import com.xingzhi.apartment.model.RoomInfo;
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
    private RoomInfo testRoomInfo;


    @Before
    public void init() {
        testRoomInfo = new RoomInfo();
        testRoomInfo.setId(9999);
        testRoomInfo.setSize("test");

    }

    @Test
    public void saveAndDelete(){
        roomInfoService.save(testRoomInfo);
        int id = roomInfoService.getRoomInfoById(testRoomInfo.getId()).getId();
        Assert.assertEquals(testRoomInfo.getId(),id);
        roomInfoService.deleteRoomInfoById(id);
    }

    @Test
    public void getRoomInfos() {
        List<RoomInfo> roomInfos = roomInfoService.getRoomInfos();
        int expectedNumOfRoomInfos = 4;
        roomInfos.forEach(em -> System.out.println(em.toString()));
        Assert.assertEquals(expectedNumOfRoomInfos, roomInfos.size());
    }

    @Test
    public void getRoomInfoByApartmentName() {
        int id = 2;
        List<RoomInfo> roomInfos = roomInfoService.getRoomInfoByApartmentName("A");
        int expectedNumOfRoomInfos = 1;
        roomInfos.forEach(em -> System.out.println(em.toString()));
        Assert.assertEquals(expectedNumOfRoomInfos, roomInfos.size());
    }

    @Test
    public void updateRoomInfoPrice() {
        int id = 1;
        String priceRange = "$1-$2";
        String rightPrice = roomInfoService.getRoomInfoById(id).getPriceRange();
        roomInfoService.updateRoomInfoPrice(id, priceRange);
        RoomInfo roomInfo = roomInfoService.getRoomInfoById(id);
        Assert.assertEquals(priceRange, roomInfo.getPriceRange());
        roomInfoService.updateRoomInfoPrice(id,rightPrice);
    }

}