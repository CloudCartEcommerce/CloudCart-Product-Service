package com.cloudcart.product.service;

import com.cloudcart.product.model.IdempotencyRecord;
import com.cloudcart.product.repository.IdempotencyRepository;
import jakarta.persistence.Id;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@Service
public class IdempotencyServiceImpl implements IdempotencyService {

    private final IdempotencyRepository idempotencyRepository;

    public IdempotencyServiceImpl(IdempotencyRepository idempotencyRepository) {
        this.idempotencyRepository = idempotencyRepository;
    }
    @Override
    public Optional<IdempotencyRecord> findByKey(String key) {

        return idempotencyRepository.findByIdempotencyKey(key);
    }

    @Override
    public IdempotencyRecord saveRecord(String key, UUID productId) {

        IdempotencyRecord record = new IdempotencyRecord();
        record.setIdempotencyKey(key);
        record.setProductId(productId);
        record.setStatus("COMPLETED");
        record.setCreatedAt(LocalDateTime.now());
        return idempotencyRepository.save(record);
    }
}
