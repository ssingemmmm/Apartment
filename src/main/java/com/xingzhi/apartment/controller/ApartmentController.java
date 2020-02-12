package com.xingzhi.apartment.controller;

import com.xingzhi.apartment.model.Apartment;
import com.xingzhi.apartment.service.ApartmentService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/apartments", "/apts"})
public class ApartmentController {
    private Logger logger;
    private ApartmentService apartmentService;

    @Autowired
    public ApartmentController(Logger logger, ApartmentService apartmentService){
        this.logger = logger;
        this.apartmentService = apartmentService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE})
    public List<Apartment> getApartments() {
        List<Apartment> apartments = apartmentService.getApartments();
        return apartments;
    }

    @RequestMapping(value = "/byName/{aptName}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Apartment getApartmentByName(@PathVariable String aptName){
        Apartment apartment = apartmentService.getApartmentByName(aptName);
        return apartment;
    }

    @RequestMapping(value = "/byID/{aptId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Apartment getApartmentById(@PathVariable int aptId){
        Apartment apartment = apartmentService.getApartmentById(aptId);
        return apartment;
    }

    @RequestMapping(value = "/allRoom/{aptName}", method = RequestMethod.GET, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public List<Apartment> getApartmentByNameWithAllRoomInfo(@PathVariable String aptName){
        List<Apartment> apartmentWithRoomInfos= apartmentService.getApartmentByNameWithAllRoomInfo(aptName);
        return apartmentWithRoomInfos;
    }

    @RequestMapping(value = "/{aptName}", method = RequestMethod.DELETE, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String deleteApartmentByName(@PathVariable String aptName){
        logger.debug("Apartment name: " + aptName);
        String msg = "The apartment was deleted.";
        boolean isSuccessful = apartmentService.deleteApartmentByName(aptName);
        if (!isSuccessful) msg = "The apartment was not deleted.";
        return msg;
    }



    @RequestMapping(value = "", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String createApartment(@RequestBody Apartment apartment){
        logger.debug("Apartment: " + apartment.toString());
        String msg = "The apartment was saved";
        boolean isSuccessful = apartmentService.save(apartment);
        if (!isSuccessful) msg = "The apartment was not saved.";
        return msg;
    }

    @RequestMapping(value = "/price", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String updateApartmentLowestPrice(@RequestParam String name, @RequestBody String lowestPrice){
        logger.debug("Apartment: " + name);
        String msg = "The apartment was updated";
        if(apartmentService.updateApartmentLowestPrice(name, lowestPrice)<1) msg = "The apartment was not saved." ;
        return msg;
    }
}
