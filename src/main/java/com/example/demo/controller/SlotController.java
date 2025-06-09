package com.example.demo.controller;

import com.example.demo.dto.Slot;
import com.example.demo.dto.filter.SlotFilter;
import com.example.demo.dto.request_response.SearchResponse;
import com.example.demo.dto.request_response.SlotAllocationRequest;
import com.example.demo.service.SlotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/slot")
public class SlotController {

    private static final Logger logger = LoggerFactory.getLogger(SlotController.class);

    private final SlotService slotService;

    public SlotController(SlotService slotService) {
        this.slotService = slotService;
    }

    @PostMapping("/allocate")
    public List<Slot> allocateSlots(@RequestBody SlotAllocationRequest request){
        logger.debug("====================[ALLOCATE SLOTS]====================");
        logger.debug("From: {}", request.from().getTime());
        logger.debug("To: {}", request.to().getTime());
        return slotService.allocateSlots(request);
    }

    @PostMapping("/search")
    public SearchResponse<Slot> search(@RequestBody SlotFilter filter){
        logger.debug("====================[SEARCH SLOTS]====================");
        return slotService.search(filter);
    }
}
