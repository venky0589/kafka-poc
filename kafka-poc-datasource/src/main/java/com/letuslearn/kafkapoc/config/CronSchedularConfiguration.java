package com.letuslearn.kafkapoc.config;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

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
	private List<Long> providerids=new ArrayList<>();
	private List<String> tables=new ArrayList<>();
	private List<String> actions=new ArrayList<>();

	public  CronSchedularConfiguration() {
		// TODO Auto-generated constructor stub
		init();
	}
	
	private void init() {
		// TODO Auto-generated method stub
		providerids.add(123L);
		providerids.add(124L);
		providerids.add(125L);
		providerids.add(126L);

		tables.add("Table1");
		tables.add("Table2");

		actions.add("INSERT");
		actions.add("UPDATE");
		actions.add("DELETE");

	}

	@Scheduled(initialDelay = 1000, fixedRate = 20000)
	public void run() {
		logger.info("Current time is :: " + Calendar.getInstance().getTime());
		long providerId = 0 + (int) (Math.random() * ((10 - 0) + 1));
		Message msg = Message.builder().providerId((Long)getRandomElement(providerids))
				.action((String)getRandomElement(actions))
				.table((String)getRandomElement(tables)).build();
		logger.info("Message Created{}", msg);

		messageService.addMessage(msg);

	}
	
	public Object getRandomElement(List list)
	{
		Random rand=new Random();
		
		return list.get(rand.nextInt(list.size()));
	}

}
