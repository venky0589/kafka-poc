package com.letuslearn.kafkapoc.config;

import org.springframework.cloud.openfeign.FeignFormatterRegistrar;
//import org.springframework.cloud.openfeign.FeignFormatterRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;


@Configuration
public class MessageRestClientConfiguration {
	
	@Bean
	public FeignFormatterRegistrar localDateFeignFormatterRegistrar() {
	    return new FeignFormatterRegistrar() {
	        @Override
	        public void registerFormatters(FormatterRegistry formatterRegistry) {
	            DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
	            registrar.setUseIsoFormat(true);
	            registrar.registerFormatters(formatterRegistry);
	        }
	    };
	}
}
