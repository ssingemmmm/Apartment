package com.xingzhi.apartment.repository;

import com.xingzhi.apartment.model.Apartment;
import com.xingzhi.apartment.model.RoomInfo;

import java.util.List;

public interface RoomInfoDao {
    void save(RoomInfo roomInfo);
    int updateRoomInfoPrice(int id, String priceRange);
    List<RoomInfo> getRoomInfos();
    List<RoomInfo> getRoomInfoByApartmentName(String name);
    RoomInfo getRoomInfoById(int id);
    boolean deleteRoomInfoById(int id);
}
