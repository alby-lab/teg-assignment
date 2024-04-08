package com.teg.message.assignment.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
	private static final Logger logger = LoggerFactory.getLogger(Producer.class);
	@Autowired
	KafkaTemplate<String, String> kafkaTemplete;

	public void sendMessageToTopic(String message) {
		try {
			kafkaTemplete.send("contact_Topic", message);
		} catch (Exception e) {
			logger.error("Producer:sendMessageToTopic{}" + e.getMessage());
		}
	}

}
