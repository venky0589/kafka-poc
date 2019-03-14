package com.letuslearn.kafkapoc.kafka;

import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.letuslearn.avro.Message;

//@Component
public class Receiver {
	private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

	private CountDownLatch latch = new CountDownLatch(1);

	public CountDownLatch getLatch() {
		return latch;
	}
	
	@KafkaListener(topics = "${kafka.topic.boot}")
	//public void recieve(ConsumerRecord<String,?> payload)
	public void recieve(Message payload)
	{
		System.out.println("Reciever.........................");
	
		LOGGER.info(" Pay load recieved {}",payload.getAction());
		
		
		latch.countDown();
	}
}
