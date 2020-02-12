package com.xingzhi.apartment.service;
import com.xingzhi.apartment.model.RoomInfo;
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

    @Autowired
    public RoomInfoServiceImpl(RoomInfoDao roomInfoDao){
        this.roomInfoDao = roomInfoDao;
    }

    @Override
    public void save(RoomInfo roomInfo) {
        roomInfoDao.save(roomInfo);
    }

    @Override
    public int updateRoomInfoPrice(int id, String priceRange) {
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
    public RoomInfo getRoomInfoById(int id) {
        return roomInfoDao.getRoomInfoById(id);
    }

    @Override
    public boolean deleteRoomInfoById(int id) {
        return roomInfoDao.deleteRoomInfoById(id);
    }
}
