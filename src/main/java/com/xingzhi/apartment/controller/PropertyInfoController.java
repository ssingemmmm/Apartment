package com.xingzhi.apartment.controller;

import com.xingzhi.apartment.model.PropertyInfo;
import com.xingzhi.apartment.service.PropertyInfoService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"/propertyInfos"})
public class PropertyInfoController {
    private Logger logger;
    private PropertyInfoService propertyInfoService;

    @Autowired
    public PropertyInfoController(Logger logger, PropertyInfoService propertyInfoService){
        this.logger = logger;
        this.propertyInfoService = propertyInfoService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String createPropertyInfo(@RequestBody PropertyInfo propertyInfo){
        logger.debug("PropertyInfo: " + propertyInfo.toString());
        String msg = "The propertyInfo was saved";
        boolean isSuccessful = propertyInfoService.save(propertyInfo);
        if (!isSuccessful) msg = "The propertyInfo was not saved.";
        return msg;
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String updatePropertyInfo(@RequestParam int id, @RequestBody PropertyInfo propertyInfo){
        logger.debug("PropertyInfo: " + id);
        String msg = "The propertyInfo was updated";
        if(propertyInfoService.updatePropertyInfo(id, propertyInfo)<1) msg = "The apartment was not updated." ;
        return msg;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE})
    public List<PropertyInfo> getPropertyInfos(){
        List<PropertyInfo> propertyInfos = propertyInfoService.getPropertyInfos();
        return propertyInfos;
    }

    @RequestMapping(value = "/{propertyInfoId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE})
    public PropertyInfo getPropertyInfoById(@PathVariable int propertyInfoId){
        PropertyInfo propertyInfo = propertyInfoService.getPropertyInfoById(propertyInfoId);
        return propertyInfo;
    }

    @RequestMapping(value = "/{propertyInfoId}", method = RequestMethod.DELETE, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String deletePropertyInfoById(@PathVariable int propertyInfoId){
        logger.debug("PropertyInfo Id: " + propertyInfoId);
        String msg = "The propertyInfo was deleted.";
        boolean isSuccessful = propertyInfoService.deletePropertyInfoById(propertyInfoId);
        if (!isSuccessful) msg = "The propertyInfo was not deleted.";
        return msg;
    }
}
