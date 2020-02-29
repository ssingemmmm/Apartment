package com.xingzhi.apartment.controller;

import com.xingzhi.apartment.model.Apartment;
import com.xingzhi.apartment.model.PropertyInfo;
import com.xingzhi.apartment.model.RoomInfo;
import com.xingzhi.apartment.service.ApartmentService;
import com.xingzhi.apartment.service.PropertyInfoService;
import com.xingzhi.apartment.service.RoomInfoService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping(value = {"/roomInfos"})
public class RoomInfoController {
    private Logger logger;
    private RoomInfoService roomInfoService;
    private ApartmentService apartmentService;

    @Autowired
    public RoomInfoController(Logger logger, RoomInfoService roomInfoService, ApartmentService apartmentService){
        this.logger = logger;
        this.roomInfoService = roomInfoService;
        this.apartmentService = apartmentService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE})
    public List<RoomInfo> getRoomInfos(){
        List<RoomInfo> roomInfos = roomInfoService.getRoomInfos();
        return roomInfos;
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> updateRoomInfo(@RequestBody RoomInfo newRI){
        Integer id = newRI.getId();
        if (id != null) {
            RoomInfo ri = roomInfoService.getRoomInfoById(id);
            if (ri != null) {
                //update object
                try {
                    roomInfoService.updateRoomInfo(id, ri);
                    return ResponseEntity.status(HttpStatus.OK).body(newRI);
                }catch(IllegalStateException e){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Oops! Check updating value. Cannot be updated!");
                }
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Oops! Room Info not found.");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createRoomInfoById(@PathVariable Integer id , @RequestBody RoomInfo ri){
        if(apartmentService.getApartmentById(id)==null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Oops! No room info found in your request.");;
        if(roomInfoService.getRoomInfoByNameSize(apartmentService.getApartmentById(id).getName(), ri.getSize())!=null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Oops! Related room info already existed. ");
        boolean isSuccessful = roomInfoService.saveById(id,ri);
        if (!isSuccessful) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Oops! The room info cannot be saved.");
        RoomInfo newRI = roomInfoService.getRoomInfoByNameSize(ri.getApartment().getName(),ri.getSize());
        return ResponseEntity.status(HttpStatus.OK).body(newRI);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE},params = {"name"})
    public ResponseEntity<?> createRoomInfoByName(@RequestParam String name, @RequestBody RoomInfo ri){
        if(apartmentService.getApartmentByName(name)==null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Oops! No apartment found.");
        if(roomInfoService.getRoomInfoByNameSize(name,ri.getSize())!=null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Oops! Related room info already existed. ");
        boolean isSuccessful = roomInfoService.saveByName(name,ri);
        if (!isSuccessful) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Oops! The roomInfo cannot be saved.");
        RoomInfo newRI = roomInfoService.getRoomInfoByNameSize(name, ri.getSize());
        return ResponseEntity.status(HttpStatus.OK).body(newRI);
    }

    @RequestMapping(value = "/price", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> updateRoomInfoPrice(@RequestParam Integer id, @RequestParam String priceRange){
        RoomInfo ri = roomInfoService.getRoomInfoById(id);
        if (ri != null) {
            try {
                roomInfoService.updateRoomInfoPrice(id, priceRange);
                ri = roomInfoService.getRoomInfoById(id);
                return ResponseEntity.status(HttpStatus.OK).body(ri);
            } catch(Exception e){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Oops! Something wrong with your request.");
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Oops! Room not found.");
    }

    @RequestMapping(value = "/byName/{aptName}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<RoomInfo> getRoomInfoByApartmentName(@PathVariable String aptName){
        List<RoomInfo> roomInfos = roomInfoService.getRoomInfoByApartmentName(aptName);
        return roomInfos;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> deleteRoomInfoById(@PathVariable int id){
        RoomInfo ri = roomInfoService.getRoomInfoById(id);
        if (ri != null) {
            try {
                roomInfoService.deleteRoomInfoById(id);
                return ResponseEntity.status(HttpStatus.OK).body(ri);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Oops! Something wrong with your request.");
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Oops! Room Info not found.");
    }
}
