package com.letuslearn.kafkapoc.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.letuslearn.kafkapoc.config.CronSchedularConfiguration;
import com.letuslearn.kafkapoc.dao.Message;
import com.letuslearn.kafkapoc.service.MessageService;

@RestController
@RequestMapping("/message")
public class MessageRestApi implements MessageRest{

	private static final Logger logger = LoggerFactory.getLogger(MessageRestApi.class);

	@Autowired
	private MessageService messageService;
	@GetMapping
	@Override
	public List<Message> getMessages()
	{
		return messageService.getAllMessages();
	}
	
	@GetMapping("/postdate")
	@Override
	public List<Message> getMessagePostDate(@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME) Date dateCreated)
	{
		List<Message> messages= messageService.getMessagePostDate(dateCreated);
		logger.debug("Messages List:{}",messages.size());
		return messages;
	}
	
	@PostMapping(consumes="application/json",produces="application/json")
	@Override
	public Message addMessage(@RequestBody Message message)
	{
		return messageService.addMessage(message);
	}
}
