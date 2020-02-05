package com.xingzhi.apartment;

import com.xingzhi.apartment.JDBC.ApartmentDao;
import com.xingzhi.apartment.JDBC.RoomInfoDao;
import com.xingzhi.apartment.model.Apartment;
import com.xingzhi.apartment.model.RoomInfo;
import org.junit.After;
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
        c = new Apartment();
        c.setId(9998);
        c.setLowestPrice("$2000");
        c.setName("ABC Apartment");
        c.setSmallestSize("studio");
        c.setPhoto("No Photo Available");
        c.setPropertyInfoId(1);
        apartmentDao.addApartment(c);
        roomInfoDao = new RoomInfoDao();
        a = new RoomInfo();
        a.setId(99999);
        a.setApartmentId(9998);
        a.setSize("1b1b");
        a.setPriceRange("1000-1500");
        a.setLayoutPhoto("photo");
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
        for(int i=0;i<roomInfos.size();i++) {
            logger.info(roomInfos.get(i).getSize());
        }
    }

    @Test
    public void searchRoomInfoTest(){
        RoomInfo b = roomInfoDao.searchRoomInfo(a.getId());
        logger.info("Searching for: "+a.getSize()+" , Searching result returned: "+ b.getSize());
    }

    @Test
    public void addRoomInfoTest(){
        List<RoomInfo> roomInfos = roomInfoDao.getRoomInfo();
        int a=roomInfos.size();
        b = new RoomInfo();
        b.setId(9999);
        b.setApartmentId(9998);
        b.setSize("1b2b");
        b.setPriceRange("1000-1509");
        b.setLayoutPhoto("photo1");
        roomInfoDao.addRoomInfo(b);
        roomInfos = roomInfoDao.getRoomInfo();
        int b=roomInfos.size();
        logger.info("size before adding "+a+" , size after adding "+b);
        roomInfoDao.deleteRoomInfo(9999);
        roomInfos = roomInfoDao.getRoomInfo();
        logger.info("size before deleting "+b+" , size after deleting "+ roomInfos.size() );
    }


    @Test
    public void updateRoomInfoTest(){
        a.setSize("changed!!!!");
        roomInfoDao.updateRoomInfo(a);
        RoomInfo d = roomInfoDao.searchRoomInfo(99999);
        logger.info("name before update: 1b1b , name after update: "+d.getSize());
    }


}