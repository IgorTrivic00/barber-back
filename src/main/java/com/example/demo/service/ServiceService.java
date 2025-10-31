package com.example.demo.service;

import com.example.demo.dto.Service;
import com.example.demo.dto.filter.ServiceFilter;
import com.example.demo.dto.request_response.SearchResponse;
import com.example.demo.model.UserEntity;

import java.io.IOException;

public interface ServiceService {

    Service addService(Service service, byte[] bytes) throws IOException;

    Service deleteService(String uuid);

    Service updateService( Service service);

    SearchResponse<Service> search(ServiceFilter filter);

    SearchResponse<Service> findMyServices(UserEntity userEntity);
}
