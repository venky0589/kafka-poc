package com.letuslearn.kafkapoc.service;

import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letuslearn.avro.Message;
import com.letuslearn.kafkapoc.dao.FailedMessage;
import com.letuslearn.kafkapoc.kafka.Receiver;
import com.letuslearn.kafkapoc.repo.FailedMessageRepo;

@Service
public class MessageService {
	@Autowired
	private FailedMessageRepo failedMessageRepo;

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);

	public void processMessage(Message message)
	{
	
		if(new Random().nextBoolean())
		{
			LOGGER.info("Message processed successfully");
			
		}else {
			FailedMessage msg=FailedMessage.builder()
			.action(message.getAction().toString())
			.dateCreated(new Date(message.getDateCreated()))
			//.id(message.getId())
			.providerId(message.getProviderId()).build();
			failedMessageRepo.save(msg);
			
			LOGGER.info("Failed Message stored successfully");
		}
	}
	
}
