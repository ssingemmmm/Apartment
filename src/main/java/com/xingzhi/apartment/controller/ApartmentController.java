package com.xingzhi.apartment.controller;

import com.amazonaws.services.iot.model.SqlParseException;
import com.fasterxml.jackson.annotation.JsonView;
import com.xingzhi.apartment.model.Apartment;
import com.xingzhi.apartment.model.PropertyInfo;
import com.xingzhi.apartment.model.Views;
import com.xingzhi.apartment.service.ApartmentService;
import org.apache.http.HttpException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
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
    public ResponseEntity<?> getApartmentByName(@PathVariable String aptName){
            Apartment apt = apartmentService.getApartmentByName(aptName);
            if (apt != null) {
                try {
                    return ResponseEntity.status(HttpStatus.OK).body(apt);
                } catch(Exception e){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Oops! Something wrong with your request.");
                }
            }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Oops! Apartment not found.");
    }

    @JsonView(Views.Internal.class)
    @RequestMapping(value = "/allRoom/{aptName}", method = RequestMethod.GET, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getApartmentByNameWithAllRoomInfo(@PathVariable String aptName){
            List<Apartment> apt = apartmentService.getApartmentByNameWithAllRoomInfo(aptName);
            if (apt != null) {
                try {
                    return ResponseEntity.status(HttpStatus.OK).body(apt);
                } catch(Exception e){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Oops! Something wrong with your request.");
                }
            }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Oops! Apartment not found.");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> deleteApartmentById(@PathVariable Integer id){
        logger.debug("Apartment id: " + id);
            Apartment apt = apartmentService.getApartmentById(id);
            if (apt != null) {
                try {
                    apartmentService.deleteApartmentById(id);
                    return ResponseEntity.status(HttpStatus.OK).body(apt);
                } catch(Exception e){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Oops! Something wrong with your request.");
                }
            }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Oops! Apartment not found.");
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createApartment(@RequestBody Apartment apartment){
        logger.debug("Apartment: " + apartment.toString());
        if(apartment.getName()==null||apartment.getName()=="") return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Oops! No apartment name found in your request");;
        if(apartmentService.getApartmentByName(apartment.getName())!=null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Oops! Apartment name already existed. ");
        boolean isSuccessful = apartmentService.save(apartment);
        if (!isSuccessful) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Oops! The apartment cannot be saved.");
        return ResponseEntity.status(HttpStatus.OK).body(apartment);
    }

    @RequestMapping(value = "/price", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> updateApartmentLowestPrice(@RequestParam Integer id, @RequestParam String lowestPrice){
            Apartment apt = apartmentService.getApartmentById(id);
            if (apt != null) {
                try {
                    apartmentService.updateApartmentLowestPrice(id, lowestPrice);
                    apt = apartmentService.getApartmentById(id);
                    return ResponseEntity.status(HttpStatus.OK).body(apt);
                } catch(Exception e){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Oops! Something wrong with your request.");
                }
            }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Oops! Apartment not found.");
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> updateApartmentInfo(@RequestBody Apartment newApt) {
            Integer id = newApt.getId();
            if (id != null) {
                Apartment apt = apartmentService.getApartmentById(id);
                if (apt != null) {
                    //update object
                    try {
                        apartmentService.updateApartmentInfo(id, newApt);
                        return ResponseEntity.status(HttpStatus.OK).body(apartmentService.getApartmentById(id));
                    }catch(IllegalStateException e){
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Oops! Check updating value. Cannot be updated!");
                    }
                }
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Oops! Apartment not found.");
    }
}
