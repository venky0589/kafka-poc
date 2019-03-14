package com.letuslearn.kafkapoc;

import java.util.Date;

import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
//import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.letuslearn.avro.Message;
import com.letuslearn.kafkapoc.kafka.Sender;


@SpringBootApplication
@EnableJpaRepositories
@EnableFeignClients
public class KafkaPocProducerApplication implements CommandLineRunner{
	@Autowired
	private Sender sender;
	
	
	private static final Logger LOG = LoggerFactory.getLogger(KafkaPocProducerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KafkaPocProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Schema message = SchemaBuilder.record("Message").namespace("com.letuslearn.avro").fields()
				.requiredString("query").requiredString("action").requiredLong("id").requiredLong("dateCreated").endRecord();
		LOG.info(message.toString());
		
		sender.publishToKafka(Message.newBuilder().setQuery("Select * from lllala").setProviderId(1L).setAction("INSERT").setDateCreated(1234568867).setId(20).build());
	}
}
