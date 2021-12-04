package com.matiasleitner.diarios.domain;

import java.util.List;

import com.matiasleitner.diarios.dto.TitleDto;
import com.matiasleitner.diarios.entity.Title;

public interface NewspaperStrategy {
	
	public List<Title> getTitles();
}
