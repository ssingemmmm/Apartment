package com.xingzhi.apartment.controller;

import com.xingzhi.apartment.model.Apartment;
import com.xingzhi.apartment.model.PropertyInfo;
import com.xingzhi.apartment.service.ApartmentService;
import com.xingzhi.apartment.service.PropertyInfoService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping(value = {"/propertyInfos"})
public class PropertyInfoController {
    private Logger logger;
    private PropertyInfoService propertyInfoService;
    private ApartmentService apartmentService;

    @Autowired
    public PropertyInfoController(Logger logger, PropertyInfoService propertyInfoService, ApartmentService apartmentService){
        this.logger = logger;
        this.propertyInfoService = propertyInfoService;
        this.apartmentService = apartmentService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE},params = {"name"})
    public ResponseEntity<?> createPropertyInfoByName(@RequestParam String name, @RequestBody PropertyInfo pi){
        if(apartmentService.getApartmentByName(name)==null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Oops! No apartment found.");
        if(propertyInfoService.getPropertyInfoByName(name)!=null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Oops! Related property info already existed. ");
        boolean isSuccessful = propertyInfoService.saveByName(name,pi);
        if (!isSuccessful) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Oops! The propertyInfo cannot be saved.");
        PropertyInfo newPI = propertyInfoService.getPropertyInfoByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(newPI);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createPropertyInfoById(@PathVariable Integer id, @RequestBody PropertyInfo pi) {
        if(apartmentService.getApartmentById(id)==null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Oops! No apartment info found in your request.");;
        if(propertyInfoService.getPropertyInfoByName(apartmentService.getApartmentById(id).getName())!=null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Oops! Related property info already existed. ");
        boolean isSuccessful = propertyInfoService.saveById(id,pi);
        if (!isSuccessful) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Oops! The propertyInfo cannot be saved.");
        PropertyInfo newPI = propertyInfoService.getPropertyInfoByName(pi.getApartment().getName());
        return ResponseEntity.status(HttpStatus.OK).body(newPI);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> updatePropertyInfo(@RequestBody PropertyInfo newPI){
        Integer id = newPI.getId();
        if (id != null) {
            PropertyInfo pi = propertyInfoService.getPropertyInfoById(id);
            if (pi != null) {
                //update object
                try {
                    propertyInfoService.updatePropertyInfo(id, pi);
                    return ResponseEntity.status(HttpStatus.OK).body(newPI);
                }catch(IllegalStateException e){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Oops! Check updating value. Cannot be updated!");
                }
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Oops! Property Info not found.");
    }


    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE})
    public List<PropertyInfo> getPropertyInfos(){
        List<PropertyInfo> propertyInfos = propertyInfoService.getPropertyInfos();
        return propertyInfos;
    }

    @RequestMapping(value = "/{propertyInfoName}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> getPropertyInfoByName(@PathVariable String propertyInfoName){
        PropertyInfo pi = propertyInfoService.getPropertyInfoByName(propertyInfoName);
        if (pi != null) {
            try {
                return ResponseEntity.status(HttpStatus.OK).body(pi);
            } catch(Exception e){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Oops! Something wrong with your request.");
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Oops! Property Info not found.");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> deletePropertyInfoById(@PathVariable Integer id) {
        PropertyInfo pi = propertyInfoService.getPropertyInfoById(id);
        if (pi != null) {
            try {
                propertyInfoService.deletePropertyInfoById(id);
                return ResponseEntity.status(HttpStatus.OK).body(pi);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Oops! Something wrong with your request.");
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Oops! Property Info not found.");
    }
}
