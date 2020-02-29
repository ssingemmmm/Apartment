package com.xingzhi.apartment.repository;

import com.xingzhi.apartment.model.Apartment;
import com.xingzhi.apartment.model.RoomInfo;

import java.util.List;

public interface RoomInfoDao {
    boolean save(RoomInfo roomInfo);
    int updateRoomInfoPrice(Integer id, String priceRange);
    int updateRoomInfo(Integer id, RoomInfo roomInfo);
    List<RoomInfo> getRoomInfos();
    List<RoomInfo> getRoomInfoByApartmentName(String name);
    RoomInfo getRoomInfoById(Integer id);
    boolean deleteRoomInfoById(Integer id);
    RoomInfo getRoomInfoByNameSize(String name, String size);
    RoomInfo getRoomInfoByNamePriceRange(String name, String priceRange);
}
