package com.xingzhi.apartment.repository;

import com.xingzhi.apartment.model.Apartment;
import com.xingzhi.apartment.model.RoomInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class RoomInfoDaoTest {
    private ApartmentDao apartmentDao;
    private RoomInfoDao roomInfoDao;
    private RoomInfo testRoomInfo;


    @Before
    public void init() {
        apartmentDao = new ApartmentDaoImpl();
        roomInfoDao = new RoomInfoDaoImpl();
        testRoomInfo = new RoomInfo();
        testRoomInfo.setId(9999);
        testRoomInfo.setSize("test");

    }

    @Test
    public void saveAndDelete(){
        roomInfoDao.save(testRoomInfo);
        int id = roomInfoDao.getRoomInfoById(testRoomInfo.getId()).getId();
        Assert.assertEquals(testRoomInfo.getId(),id);
        roomInfoDao.deleteRoomInfoById(id);
    }

    @Test
    public void getRoomInfos() {
        List<RoomInfo> roomInfos = roomInfoDao.getRoomInfos();
        int expectedNumOfRoomInfos = 4;
        roomInfos.forEach(em -> System.out.println(em.toString()));
        Assert.assertEquals(expectedNumOfRoomInfos, roomInfos.size());
    }

    @Test
    public void getRoomInfoByApartmentName() {
        int id = 2;
        List<RoomInfo> roomInfos = roomInfoDao.getRoomInfoByApartmentName("A");
        int expectedNumOfRoomInfos = 1;
        roomInfos.forEach(em -> System.out.println(em.toString()));
        Assert.assertEquals(expectedNumOfRoomInfos, roomInfos.size());
    }

    @Test
    public void updateRoomInfoPrice() {
        int id = 1;
        String priceRange = "$1-$2";
        String rightPrice = roomInfoDao.getRoomInfoById(id).getPriceRange();
        roomInfoDao.updateRoomInfoPrice(id, priceRange);
        RoomInfo roomInfo = roomInfoDao.getRoomInfoById(id);
        Assert.assertEquals(priceRange, roomInfo.getPriceRange());
        roomInfoDao.updateRoomInfoPrice(id,rightPrice);
    }

}
