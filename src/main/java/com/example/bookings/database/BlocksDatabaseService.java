package com.example.bookings.database;

import com.example.bookings.database.mappers.BlockMapper;
import com.example.bookings.database.models.Property;
import com.example.bookings.database.repositories.BlockRepository;
import com.example.bookings.database.repositories.PropertyRepository;
import com.example.bookings.domain.Block;
import com.example.bookings.domain.request.CreateBlockRequest;
import com.example.bookings.domain.request.UpdateBlockRequest;
import com.example.bookings.exceptions.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.bookings.exceptions.EntityType.BLOCK;
import static com.example.bookings.exceptions.EntityType.PROPERTY;
import static com.example.bookings.exceptions.ExceptionType.ENTITY_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class BlocksDatabaseService {

    private final BlockRepository blockRepository;
    private final PropertyRepository propertyRepository;
    private final BlockMapper blockMapper;

    public Block getBlock(String id) {
        long idLong;
        try {
            idLong = Long.parseLong(id);
        } catch (Exception e) {
            throw ApplicationException.buildException(BLOCK, ENTITY_NOT_FOUND, id);
        }

        com.example.bookings.database.models.Block block = blockRepository.findById(idLong).orElseThrow(
                () -> ApplicationException.buildException(BLOCK, ENTITY_NOT_FOUND, id)
        );

        return blockMapper.toDomain(block);
    }

    public List<Block> getBlocksByPropertyId(String propertyId) {
        long idLong;
        try {
            idLong = Long.parseLong(propertyId);
        } catch (Exception e) {
            throw ApplicationException.buildException(PROPERTY, ENTITY_NOT_FOUND, propertyId);
        }
        return blockRepository.findByPropertyId(idLong).stream().map(blockMapper::toDomain).toList();
    }

    public Block createBlock(CreateBlockRequest request) {
        Property property = propertyRepository.findById(Long.parseLong(request.propertyId())).orElseThrow(
                () -> ApplicationException.buildException(PROPERTY, ENTITY_NOT_FOUND, request.propertyId())
        );

        com.example.bookings.database.models.Block block = blockMapper.toDb(request, property);
        block = blockRepository.save(block);
        return blockMapper.toDomain(block);
    }

    public Block updateBlock(String id, UpdateBlockRequest request) {
        long idLong;
        try {
            idLong = Long.parseLong(id);
        } catch (Exception e) {
            throw ApplicationException.buildException(BLOCK, ENTITY_NOT_FOUND, id);
        }

        com.example.bookings.database.models.Block block = blockRepository.findById(idLong).orElseThrow(
                () -> ApplicationException.buildException(BLOCK, ENTITY_NOT_FOUND, id)
        );

        block = blockMapper.toDb(request, block);
        blockRepository.save(block);

        return blockMapper.toDomain(block);

    }
}
