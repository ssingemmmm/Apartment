package com.xingzhi.apartment.JDBC;

import com.xingzhi.apartment.JDBC.ApartmentDao;
import com.xingzhi.apartment.JDBC.RoomInfoDao;
import com.xingzhi.apartment.model.Apartment;
import com.xingzhi.apartment.model.RoomInfo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RoomInfoDaoTest {
    private ApartmentDao apartmentDao;
    private Apartment c;
    private RoomInfoDao roomInfoDao;
    private RoomInfo a;
    private RoomInfo b;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
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
        apartmentDao.addApartment(c);

        roomInfoDao = new RoomInfoDao();
        a = new RoomInfo();
        a.setId(99999);
        a.setSize("1b1b");
        a.setPriceRange("1000-1500");
        a.setLayoutPhoto("photo");
        a.setApartment(c);
        roomInfoDao.addRoomInfo(a);
    }

    @After
    public void tearDown(){
        roomInfoDao.deleteRoomInfo(a.getId());
        apartmentDao.deleteApartment(c.getId());
    }

    @Test
    public void getRoomInfoTest(){
        List<RoomInfo> roomInfos = roomInfoDao.getRoomInfo();
        Assert.assertNotNull(roomInfos);
    }

    @Test
    public void searchRoomInfoTest(){
        RoomInfo b = roomInfoDao.searchRoomInfo(a.getId());
        Assert.assertEquals(b.getSize(),a.getSize());
    }

    @Test
    public void addAndDeleteRoomInfoTest(){
        List<RoomInfo> roomInfos = roomInfoDao.getRoomInfo();
        int a=roomInfos.size();
        b = new RoomInfo();
        b.setId(9999);
        b.setSize("1b2b");
        b.setPriceRange("1000-1509");
        b.setLayoutPhoto("photo1");
        b.setApartment(c);
        roomInfoDao.addRoomInfo(b);
        roomInfos = roomInfoDao.getRoomInfo();
        int b=roomInfos.size();
        Assert.assertNotEquals(a,b);
        roomInfoDao.deleteRoomInfo(9999);
        roomInfos = roomInfoDao.getRoomInfo();
        b=roomInfos.size();
        Assert.assertEquals(a,b);
    }


    @Test
    public void updateRoomInfoTest(){
        a.setSize("changed!!!!");
        roomInfoDao.updateRoomInfo(a);
        RoomInfo d = roomInfoDao.searchRoomInfo(99999);
        Assert.assertEquals(a.getSize(),d.getSize());
    }


}