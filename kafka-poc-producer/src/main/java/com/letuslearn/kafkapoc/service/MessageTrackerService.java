package com.letuslearn.kafkapoc.service;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letuslearn.kafkapoc.dao.MessageTracker;
import com.letuslearn.kafkapoc.repo.MessageTrackerRepo;

@Service
public class MessageTrackerService {
	
	
	
	@Autowired
	private MessageTrackerRepo messageTrackerRepo;

	@Transactional
	public void updateMessageTracker(Date dateCreated) {
		MessageTracker messageTracker = messageTrackerRepo.findById(1L).orElse(null);
		if (messageTracker == null) {

			messageTracker = MessageTracker.builder().id(1L).build();
		}

		messageTracker.setLastFeched(dateCreated);
		messageTrackerRepo.save(messageTracker);
	}

	@Transactional
	public Date getLastFetced() {
		MessageTracker messageTracker = messageTrackerRepo.findById(1L).orElse(null);
		if (messageTracker == null) {
			return null ;
		}

		return messageTracker.getLastFeched();

	}
}
