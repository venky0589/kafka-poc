package com.letuslearn.kafkapoc.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDTO {
	private Long id;

	private Long providerId;

	private String query;

	private String action;

	private Date dateCreated;
}
