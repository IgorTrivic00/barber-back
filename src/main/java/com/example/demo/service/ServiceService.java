package com.example.demo.service;

import com.example.demo.dto.Service;
import com.example.demo.dto.filter.ServiceFilter;
import com.example.demo.dto.request_response.SearchResponse;
import com.example.demo.model.UserEntity;

import java.io.IOException;

public interface ServiceService {

    Service add(Service service, byte[] bytes) throws IOException;

    Service delete(String uuid);

    Service update(Service service);

    SearchResponse<Service> search(ServiceFilter filter);

    SearchResponse<Service> findForUser(UserEntity userEntity);
}
