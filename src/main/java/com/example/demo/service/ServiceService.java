package com.example.demo.service;

import com.example.demo.dto.Barber;
import com.example.demo.dto.Service;
import com.example.demo.dto.filter.ServiceFilter;
import com.example.demo.dto.request_response.SearchResponse;
import com.example.demo.model.ServiceEntity;
import com.example.demo.model.UserEntity;


import java.util.List;
import java.util.UUID;

public interface ServiceService {

    Service addService(Service service);

    Service deleteService(String uuid);

    Service updateService( Service service);

    SearchResponse<Service> search(ServiceFilter filter);

    SearchResponse<Service> findMyServices(UserEntity userEntity);
}
