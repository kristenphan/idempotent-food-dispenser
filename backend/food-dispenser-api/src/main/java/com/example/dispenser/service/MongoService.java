package com.example.dispenser.service;

import com.example.dispenser.model.FeedRecord;
import com.example.dispenser.repository.IFeedRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MongoService {
	IFeedRecordRepository feedRecordRepository;

	@Autowired
	public MongoService(IFeedRecordRepository feedRecordRepository) {
		this.feedRecordRepository = feedRecordRepository;
	}

	public void saveFeedRecord(FeedRecord feedRecord) {
		feedRecordRepository.save(feedRecord);
	}
}
