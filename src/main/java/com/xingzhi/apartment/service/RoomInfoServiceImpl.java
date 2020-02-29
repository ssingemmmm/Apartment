package com.xingzhi.apartment.service;
import com.amazonaws.services.alexaforbusiness.model.Room;
import com.xingzhi.apartment.model.Apartment;
import com.xingzhi.apartment.model.PropertyInfo;
import com.xingzhi.apartment.model.RoomInfo;
import com.xingzhi.apartment.repository.ApartmentDao;
import com.xingzhi.apartment.repository.RoomInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class RoomInfoServiceImpl implements RoomInfoService{

    private RoomInfoDao roomInfoDao;
    private ApartmentDao apartmentDao;

    @Autowired
    public RoomInfoServiceImpl(RoomInfoDao roomInfoDao, ApartmentDao apartmentDao){
        this.roomInfoDao = roomInfoDao;
        this.apartmentDao = apartmentDao;
    }

    @Override
    public boolean saveByName(String name, RoomInfo roomInfo) {
        Apartment apartment = apartmentDao.getApartmentByName(name);
        roomInfo.setApartment(apartment);
        return roomInfoDao.save(roomInfo);
    }

    @Override
    public boolean save(RoomInfo roomInfo) {
        return roomInfoDao.save(roomInfo);
    }

    @Override
    public int updateRoomInfo(Integer id, RoomInfo roomInfo) {
        return roomInfoDao.updateRoomInfo(id, roomInfo);
    }

    @Override
    public int updateRoomInfoPrice(Integer id, String priceRange) {
        return roomInfoDao.updateRoomInfoPrice(id, priceRange);
    }

    @Override
    public List<RoomInfo> getRoomInfos() {
        return roomInfoDao.getRoomInfos();
    }

    @Override
    public List<RoomInfo> getRoomInfoByApartmentName(String name) {
        return roomInfoDao.getRoomInfoByApartmentName(name);
    }

    @Override
    public RoomInfo getRoomInfoByNameSize(String name, String size) {
        return roomInfoDao.getRoomInfoByNameSize(name,size);
    }

    @Override
    public boolean deleteRoomInfoByNameSize(String name, String size) {
        int id = roomInfoDao.getRoomInfoByNameSize(name,size).getId();
        return roomInfoDao.deleteRoomInfoById(id);
    }

    @Override
    public RoomInfo getRoomInfoByNamePriceRange(String name, String priceRange){
        return roomInfoDao.getRoomInfoByNamePriceRange(name,priceRange);
    }

    @Override
    public RoomInfo getRoomInfoById(Integer id) {
        return roomInfoDao.getRoomInfoById(id);
    }

    @Override
    public boolean deleteRoomInfoById(Integer id) {
        return roomInfoDao.deleteRoomInfoById(id);
    }

    @Override
    public boolean saveById(Integer id, RoomInfo roomInfo){
        Apartment apartment = apartmentDao.getApartmentById(id);
        roomInfo.setApartment(apartment);
        return roomInfoDao.save(roomInfo);
    }
}
