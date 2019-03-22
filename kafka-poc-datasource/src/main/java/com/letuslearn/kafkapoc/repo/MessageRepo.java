package com.letuslearn.kafkapoc.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.letuslearn.kafkapoc.dao.Message;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long>{


	List<Message> findAllByDateCreatedGreaterThanOrderByProviderIdAscTableAsc(Date createdDate);

	

}
