package com.letuslearn.kafkapoc.config;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.letuslearn.kafkapoc.dao.Message;
import com.letuslearn.kafkapoc.service.MessageService;

@Configuration
@EnableScheduling
public class CronSchedularConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(CronSchedularConfiguration.class);
	@Autowired
	private MessageService messageService;

	@Scheduled(initialDelay = 1000, fixedRate = 60000)
	public void run() {
		logger.info("Current time is :: " + Calendar.getInstance().getTime());
		long providerId = 0 + (int) (Math.random() * ((10 - 0) + 1));
		Message msg = Message.builder().providerId(providerId).action("Update").query("Select * from abc").build();
		logger.info("Message Created{}", msg);

		messageService.addMessage(msg);

	}

}
