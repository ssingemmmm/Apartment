package com.xingzhi.apartment.repository;

import com.xingzhi.apartment.init.AppInitializer;
import com.xingzhi.apartment.model.Apartment;
import com.xingzhi.apartment.model.PropertyInfo;
import com.xingzhi.apartment.model.RoomInfo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= AppInitializer.class)
public class RoomInfoDaoTest {
    @Autowired
    private ApartmentDao apartmentDao;
    @Autowired
    private RoomInfoDao roomInfoDao;
    @Autowired
    private Logger logger;
    private RoomInfo testRoomInfo;
    private RoomInfo updateRoomInfo;
    private Apartment testApartment;


    @Before
    public void init() {
        testRoomInfo = new RoomInfo();
        testRoomInfo.setSize("studio");
        testRoomInfo.setPriceRange("test");
        updateRoomInfo = new RoomInfo();
        testApartment = new Apartment();
        testApartment.setName("TEST");
        testRoomInfo.setApartment(testApartment);
        apartmentDao.save(testApartment);
        roomInfoDao.save(testRoomInfo);
    }

    @After
    public void tearDown(){
        roomInfoDao.deleteRoomInfoById(roomInfoDao.getRoomInfoByNameSize(testApartment.getName(),testRoomInfo.getSize()).getId());
        apartmentDao.deleteApartmentByName(testApartment.getName());
    }

    @Test
    public void getRoomInfos() {
        List<RoomInfo> roomInfos = roomInfoDao.getRoomInfos();
        roomInfos.forEach(em -> logger.debug(em.toString()));
        Assert.assertNotNull(roomInfos);
    }

    @Test
    public void getRoomInfoByApartmentName() {
        List<RoomInfo> roomInfos = roomInfoDao.getRoomInfoByApartmentName("TEST");
        int expectedNumOfRoomInfos = 1;
        roomInfos.forEach(em -> logger.debug(em.toString()));
        Assert.assertEquals(expectedNumOfRoomInfos, roomInfos.size());
    }

    @Test
    public void updateRoomInfoPrice() {
        int id = roomInfoDao.getRoomInfoByNameSize(testApartment.getName(),testRoomInfo.getSize()).getId();
        String priceRange = "$1-$2";
        roomInfoDao.updateRoomInfoPrice(id, priceRange);
        RoomInfo roomInfo = roomInfoDao.getRoomInfoById(id);
        Assert.assertEquals(priceRange, roomInfo.getPriceRange());
    }

    @Test
    public void updateRoomInfo(){
        int id = roomInfoDao.getRoomInfoByNameSize(testApartment.getName(),testRoomInfo.getSize()).getId();
        String size = "UPDATED!!!!!!!!!!!!!!!!!";
        testRoomInfo.setSize(size);
        roomInfoDao.updateRoomInfo(id, testRoomInfo);
        RoomInfo roomInfo = roomInfoDao.getRoomInfoById(id);
        Assert.assertEquals(size, roomInfo.getSize());
    }

    @Test
    public void getRoomInfoByNameSize(){
        RoomInfo roomInfo = roomInfoDao.getRoomInfoByNameSize("TEST","studio");
        logger.debug(roomInfo.toString());
        Assert.assertNotNull(roomInfo);
    }

    @Test
    public void getRoomInfoByNamePriceRange(){
        RoomInfo roomInfo = roomInfoDao.getRoomInfoByNamePriceRange("TEST","test");
        int id = 1;
        logger.debug(roomInfo.toString());
        Assert.assertNotNull(roomInfo);
    }

}
