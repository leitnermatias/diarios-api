package com.matiasleitner.diarios.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TitleDto implements Serializable {

	private static final long serialVersionUID = 2737766609192694729L;
	

	private String header;
	
	private String titleUrl;

	private String imgUrl;
	
	private NewspaperDto newspaper;

}
