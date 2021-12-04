package com.matiasleitner.diarios.entity;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Newspaper extends BaseEntity {
	
	@NonNull
	private String name;
	
	@NonNull
	private String apiRef;
	
	@NonNull
	private String baseUrl;
	
	@NonNull
	private String titlesUrl;
	
}
