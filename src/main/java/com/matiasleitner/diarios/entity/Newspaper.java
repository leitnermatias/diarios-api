package com.matiasleitner.diarios.entity;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Newspaper extends BaseEntity {
	
	private String name;
	
	private String baseUrl;
	
	private String titlesUrl;
	
}
