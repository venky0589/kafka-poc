package com.letuslearn.kafkapoc.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letuslearn.kafkapoc.dao.Message;
import com.letuslearn.kafkapoc.repo.MessageRepo;

@Service
public class MessageService {

	@Autowired
	private MessageRepo messageRepo;
	public List<Message> getAllMessages(){		
		return this.messageRepo.findAll();
		
	}
	
	public Message addMessage(Message message)
	{
		return this.messageRepo.save(message);
	}

	public List<Message> getMessagePostDate(Date dateCreated) {
		// TODO Auto-generated method stub
		return this.messageRepo.findAllByDateCreatedLessThanEqual(dateCreated);
	}
}
