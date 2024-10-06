package com.example.dispenser.model;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "feed_record")
@Builder
@Data
public class FeedRecord {
	@Id
	private ObjectId id;
	private String name;
	private Long timestamp;
}
