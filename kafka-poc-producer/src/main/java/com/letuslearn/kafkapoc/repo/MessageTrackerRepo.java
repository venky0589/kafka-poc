package com.letuslearn.kafkapoc.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.letuslearn.kafkapoc.dao.MessageTracker;

public interface MessageTrackerRepo extends JpaRepository<MessageTracker, Long>{


}
