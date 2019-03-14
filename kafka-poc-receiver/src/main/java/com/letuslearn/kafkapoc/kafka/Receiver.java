package com.letuslearn.kafkapoc.kafka;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.letuslearn.avro.Message;
import com.letuslearn.kafkapoc.service.MessageService;

@Component
public class Receiver {
	private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

@Autowired
private MessageService messageService;
	
	
	
	@KafkaListener(topics = "${kafka.topic.boot}")
	//public void recieve(ConsumerRecord<String,?> payload)
	public void recieve(Message payload)
	{
		System.out.println("Reciever.........................");
	
		// create random object
	      Random randomno = new Random();

	      // get next next boolean value 
	      boolean value = randomno.nextBoolean();

		LOGGER.info(" Pay load recieved {}",payload.getAction());		
		
		messageService.processMessage(payload);

		
		
	}
}
