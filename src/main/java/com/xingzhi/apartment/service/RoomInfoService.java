package com.xingzhi.apartment.service;

import com.xingzhi.apartment.model.RoomInfo;

import java.util.List;

public interface RoomInfoService {
    boolean saveByName(String name, RoomInfo roomInfo);
    boolean save(RoomInfo roomInfo);
    int updateRoomInfoPrice(Integer id, String priceRange);
    int updateRoomInfo(Integer id, RoomInfo roomInfo);
    List<RoomInfo> getRoomInfos();
    List<RoomInfo> getRoomInfoByApartmentName(String name);
    RoomInfo getRoomInfoByNameSize(String name, String size);
    boolean deleteRoomInfoByNameSize(String name, String size);
    RoomInfo getRoomInfoByNamePriceRange(String name, String priceRange);
    RoomInfo getRoomInfoById(Integer id);
    boolean deleteRoomInfoById(Integer id);
    boolean saveById(Integer id, RoomInfo roomInfo);
}
