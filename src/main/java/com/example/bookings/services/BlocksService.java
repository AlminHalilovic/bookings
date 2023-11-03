package com.example.bookings.services;

import com.example.bookings.database.BlocksDatabaseService;
import com.example.bookings.domain.Block;
import com.example.bookings.domain.request.CreateBlockRequest;
import com.example.bookings.domain.request.UpdateBlockRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlocksService {

    private final BlocksDatabaseService blocksDatabaseService;

    public Block getBlock(String id) {
        return blocksDatabaseService.getBlock(id);
    }

    List<Block> getBlocksByPropertyId(String propertyId) {
        return blocksDatabaseService.getBlocksByPropertyId(propertyId);
    }

    public Block createBlock(CreateBlockRequest request) {
        // todo validations
        return blocksDatabaseService.createBlock(request);
    }

    public Block updateBlock(String id, UpdateBlockRequest request) {
        // todo validations
        return blocksDatabaseService.updateBlock(id, request);
    }
}
