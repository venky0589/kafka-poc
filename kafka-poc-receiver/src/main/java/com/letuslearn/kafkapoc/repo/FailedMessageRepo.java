package com.letuslearn.kafkapoc.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.letuslearn.kafkapoc.dao.FailedMessage;

public interface FailedMessageRepo extends JpaRepository<FailedMessage, Long>{


}
