package com.example.bookings.database.mappers;

import com.example.bookings.database.models.Property;
import com.example.bookings.domain.Block;
import com.example.bookings.domain.request.CreateBlockRequest;
import com.example.bookings.domain.request.UpdateBlockRequest;
import org.springframework.stereotype.Component;

@Component
public class BlockMapper {

    public Block toDomain(com.example.bookings.database.models.Block dbBlock) {
        return new Block(dbBlock.getId().toString(), dbBlock.getUser().getId().toString(),
                dbBlock.getProperty().getId().toString(), dbBlock.getStartDate(), dbBlock.getEndDate());
    }

    public com.example.bookings.database.models.Block toDb(CreateBlockRequest request, Property property) {
        return new com.example.bookings.database.models.Block()
                .setStartDate(request.startDate())
                .setEndDate(request.endDate())
                .setUser(property.getUser())
                .setProperty(property);
    }

    public com.example.bookings.database.models.Block toDb(UpdateBlockRequest request,
                                                           com.example.bookings.database.models.Block dbBlock) {
        dbBlock.setStartDate(request.startDate());
        dbBlock.setEndDate(request.endDate());

        return dbBlock;
    }
}
