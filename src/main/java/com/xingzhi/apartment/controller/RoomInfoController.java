package com.xingzhi.apartment.controller;

import com.xingzhi.apartment.model.Apartment;
import com.xingzhi.apartment.model.PropertyInfo;
import com.xingzhi.apartment.model.RoomInfo;
import com.xingzhi.apartment.service.PropertyInfoService;
import com.xingzhi.apartment.service.RoomInfoService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/roomInfos"})
public class RoomInfoController {
    private Logger logger;
    private RoomInfoService roomInfoService;

    @Autowired
    public RoomInfoController(Logger logger, RoomInfoService roomInfoService){
        this.logger = logger;
        this.roomInfoService = roomInfoService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String createRoomInfo(@RequestBody RoomInfo roomInfo){
        logger.debug("RoomInfo: " + roomInfo.toString());
        String msg = "The roomInfo was saved";
        boolean isSuccessful = roomInfoService.save(roomInfo);
        if (!isSuccessful) msg = "The roomInfo was not saved.";
        return msg;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String updateRoomInfoPrice(@PathVariable int id, @RequestBody String priceRange){
        logger.debug("RoomInfo: " + id);
        String msg = "The roomInfo was updated";
        if(roomInfoService.updateRoomInfoPrice(id, priceRange)<1) msg = "The roomInfo was not updated." ;
        return msg;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE})
    public List<RoomInfo> getRoomInfos(){
        List<RoomInfo> roomInfos = roomInfoService.getRoomInfos();
        return roomInfos;
    }

    @RequestMapping(value = "/byName/{aptName}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<RoomInfo> getRoomInfoByApartmentName(@PathVariable String aptName){
        List<RoomInfo> roomInfos = roomInfoService.getRoomInfoByApartmentName(aptName);
        return roomInfos;
    }

    @RequestMapping(value = "/byID/{roomInfoId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE})
    public RoomInfo getPropertyInfoById(@PathVariable int roomInfoId){
        RoomInfo roomInfo = roomInfoService.getRoomInfoById(roomInfoId);
        return roomInfo;
    }

    @RequestMapping(value = "/{roomInfoId}", method = RequestMethod.DELETE, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String deleteRoomInfoById(@PathVariable int roomInfoId){
        logger.debug("RoomInfo Id: " + roomInfoId);
        String msg = "The roomInfo was deleted.";
        boolean isSuccessful = roomInfoService.deleteRoomInfoById(roomInfoId);
        if (!isSuccessful) msg = "The roomInfo was not deleted.";
        return msg;
    }
}
