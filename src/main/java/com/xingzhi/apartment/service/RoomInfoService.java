package com.xingzhi.apartment.service;

import com.xingzhi.apartment.model.RoomInfo;

import java.util.List;

public interface RoomInfoService {
    boolean save(RoomInfo roomInfo);
    int updateRoomInfoPrice(int id, String priceRange);
    List<RoomInfo> getRoomInfos();
    List<RoomInfo> getRoomInfoByApartmentName(String name);
    RoomInfo getRoomInfoById(int id);
    boolean deleteRoomInfoById(int id);
}
