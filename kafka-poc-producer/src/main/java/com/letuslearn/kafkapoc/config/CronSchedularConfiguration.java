package com.letuslearn.kafkapoc.config;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.letuslearn.avro.Message;
import com.letuslearn.kafkapoc.dto.MessageDTO;
import com.letuslearn.kafkapoc.kafka.Sender;
import com.letuslearn.kafkapoc.restclient.MessageRestClient;
import com.letuslearn.kafkapoc.service.MessageTrackerService;

@Configuration
@EnableScheduling
public class CronSchedularConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(CronSchedularConfiguration.class);

	@Autowired
	private MessageRestClient messageRestClient;

	@Autowired
	private MessageTrackerService messageTrackerService;

	@Autowired
	private Sender sender;

	@Scheduled(initialDelay = 1000, fixedRate = 60000)
	public void run() {

		logger.info("Current time is :: " + Calendar.getInstance().getTime());
		long providerId = 0 + (int) (Math.random() * ((10 - 0) + 1));
		// Message
		// msg=Message.builder().providerId(providerId).action("Update").query("Select *
		// from abc").build();
		// logger.info("Message Created{}",msg);

		Date lastFetchedDate = messageTrackerService.getLastFetced();
		List<MessageDTO> messageDTOs = null;

		if (lastFetchedDate == null) {
			messageDTOs = messageRestClient.getAllMessages();
		} else {
			messageDTOs = messageRestClient.getMessagePostDate(lastFetchedDate);
		}
		Date lastFetchedDate1 = null;
		for (MessageDTO messageDTO : messageDTOs) {
			logger.info(messageDTO.getTable());
			if (lastFetchedDate1 == null) {
				lastFetchedDate1 = messageDTO.getDateCreated();
			} else if (lastFetchedDate1.after(messageDTO.getDateCreated())) {
				lastFetchedDate1 = messageDTO.getDateCreated();
			}

			sender.publishToKafka(Message.newBuilder().setAction(messageDTO.getAction())
					.setDateCreated(messageDTO.getDateCreated().getTime()).setId(messageDTO.getId())
					.setProviderId(messageDTO.getProviderId())
					.setTable(messageDTO.getTable()).build());

		}

		messageTrackerService.updateMessageTracker(lastFetchedDate1);
		logger.info("Received {} msgs", messageDTOs.size());
	}

}
