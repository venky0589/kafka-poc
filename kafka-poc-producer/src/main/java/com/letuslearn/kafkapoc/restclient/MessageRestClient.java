package com.letuslearn.kafkapoc.restclient;

import java.util.Date;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.letuslearn.kafkapoc.config.MessageRestClientConfiguration;
import com.letuslearn.kafkapoc.dto.MessageDTO;


@FeignClient(name = "messageRestClient", url = "${messag.datasource.url}", configuration = MessageRestClientConfiguration.class)
public interface MessageRestClient {
	@GetMapping("/postdate")
	public List<MessageDTO> getMessagePostDate(
			@RequestParam("dateCreated") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date dateCreated);
	
	@GetMapping("")
	public List<MessageDTO> getAllMessages();
}
