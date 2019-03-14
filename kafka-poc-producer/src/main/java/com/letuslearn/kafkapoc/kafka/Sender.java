package com.letuslearn.kafkapoc.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.letuslearn.avro.Message;


@Component
public class Sender {
	
	private static final Logger LOGGER =
		      LoggerFactory.getLogger(Sender.class);

	@Autowired
	KafkaTemplate< String, Message> template;
	
	@Value("${kafka.topic.boot}")
	String kafkaTopic;
	
	public void publishToKafka(Message message)
	{
		LOGGER.info("Publicsh message to kafka {} to topic {}",message,kafkaTopic);
		template.send(kafkaTopic,message);
		
	}

}
