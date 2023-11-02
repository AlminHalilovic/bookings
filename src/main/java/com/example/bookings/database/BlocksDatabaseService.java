package com.example.bookings.database;

import com.example.bookings.database.mappers.BlockMapper;
import com.example.bookings.database.repositories.BlockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlocksDatabaseService {

    private final BlockRepository blockRepository;
    private final BlockMapper blockMapper;
}
