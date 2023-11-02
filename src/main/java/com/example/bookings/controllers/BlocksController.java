package com.example.bookings.controllers;

import com.example.bookings.services.BlocksService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/blocks")
@RequiredArgsConstructor
public class BlocksController {

    private final BlocksService blocksService;
}
