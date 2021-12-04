package com.matiasleitner.diarios.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewspaperDto implements Serializable {

	private static final long serialVersionUID = 8658822340321252401L;
	
	private String name;
	
	private String titlesUrl;
}
