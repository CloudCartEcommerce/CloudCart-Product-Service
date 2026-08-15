package com.cloudcart.product.service;

import com.cloudcart.product.model.IdempotencyRecord;

import java.util.Optional;
import java.util.UUID;

public interface IdempotencyService {

    Optional<IdempotencyRecord> findByKey(String key);

    IdempotencyRecord saveRecord(String key, UUID product_id);
}
