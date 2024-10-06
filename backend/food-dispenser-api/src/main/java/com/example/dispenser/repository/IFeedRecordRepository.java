package com.example.dispenser.repository;

import com.example.dispenser.model.FeedRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IFeedRecordRepository extends MongoRepository<FeedRecord, Long> {
}
