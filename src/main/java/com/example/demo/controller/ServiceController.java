package com.example.demo.controller;

import com.example.demo.dto.Service;
import com.example.demo.dto.filter.ServiceFilter;
import com.example.demo.dto.request_response.SearchResponse;
import com.example.demo.model.UserEntity;
import com.example.demo.service.ServiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/service")
public class ServiceController {

    private static Logger logger = LoggerFactory.getLogger(ServiceController.class);

    private final ServiceService serviceService;

    @Autowired
    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @PostMapping("/search")
    public SearchResponse<Service> search(@RequestBody ServiceFilter filter){
        logger.debug("====================[SEARCH SERVICES]====================");
        return serviceService.search(filter);
    }

    @GetMapping("/my-services")
    public SearchResponse<Service> findMyServices(@AuthenticationPrincipal UserEntity userEntity){
        logger.debug("====================[FIND MY SERVICES]====================");
        return serviceService.findForUser(userEntity);
    }

    @PostMapping("/add")
    public Service addService(@RequestPart Service service,
                              @RequestPart(value = "photo", required = false) MultipartFile photo) throws IOException {
        logger.debug("====================[ADD SERVICE]====================");
        byte[] bytes = new byte[0];
        if(photo != null){
            bytes = photo.getBytes();
        }
        return serviceService.add(service, bytes);
    }

    @PutMapping()
    public Service updateService(@RequestBody Service service) {
        logger.debug("====================[UPDATE SERVICE]====================");
        return serviceService.update( service);
    }

    @DeleteMapping("/{uuid}")
    public Service deleteService(@PathVariable String uuid) {
        logger.debug("====================[DELETE SERVICE]====================");
        return serviceService.delete(uuid);
    }

}
