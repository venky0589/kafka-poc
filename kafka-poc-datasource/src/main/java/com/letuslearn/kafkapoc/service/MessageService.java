package com.letuslearn.kafkapoc.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letuslearn.kafkapoc.dao.ActionEnum;
import com.letuslearn.kafkapoc.dao.Message;
import com.letuslearn.kafkapoc.repo.MessageRepo;

@Service
public class MessageService {

	@Autowired
	private MessageRepo messageRepo;

	public List<Message> getAllMessages() {
		return this.messageRepo.findAll();

	}

	public Message addMessage(Message message) {
		return this.messageRepo.save(message);
	}

	public List<Message> getMessagePostDate(Date dateCreated) {
		Set<String> uniqueSet = new HashSet<>();

		// TODO Auto-generated method stub
		List<Message> messages = this.messageRepo
				.findAllByDateCreatedGreaterThanOrderByProviderIdAscTableAsc(dateCreated).stream()
				.filter(m -> uniqueSet.add(m.getProviderId() + m.getTable() + m.getAction()))
				.sorted((m1, m2) -> {
					if (m1.getProviderId() == m2.getProviderId()) {
						if (m1.getTable().equalsIgnoreCase(m2.getTable())) {
							ActionEnum m1action = ActionEnum.valueOf(m1.getAction());
							ActionEnum m2action = ActionEnum.valueOf(m2.getAction());
							return m1action.compareTo(m2action);

						} else {
							return 0;
						}

					} else {
						return 0;
					}
					// return 1;
				})
				.collect(Collectors.toList());

		return messages;
	}
}
