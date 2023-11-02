package com.example.bookings.controllers;

import com.example.bookings.services.PropertiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/properties")
@RequiredArgsConstructor
public class PropertiesController {

    private final PropertiesService propertiesService;
}
