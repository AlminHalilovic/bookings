package com.example.bookings.services;

import com.example.bookings.database.BlocksDatabaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlocksService {

    private final BlocksDatabaseService blocksDatabaseService;
}
