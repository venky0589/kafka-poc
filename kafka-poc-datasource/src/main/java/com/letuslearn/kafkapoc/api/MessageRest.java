package com.letuslearn.kafkapoc.api;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import com.letuslearn.kafkapoc.dao.Message;

public interface MessageRest {
	public List<Message> getMessages();
	public List<Message> getMessagePostDate(@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME) Date dateCreated);
	Message addMessage(Message message);
	
}
