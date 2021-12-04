package com.matiasleitner.diarios.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.matiasleitner.diarios.entity.Title;
import com.matiasleitner.diarios.repository.TitleRepository;
import com.matiasleitner.diarios.service.TitleService;

@Component
public class TitleServiceImpl implements TitleService {
	
	@Autowired
	private TitleRepository titleRepository;
	
	@Override
	public Iterable<Title> getTitles() {
		Iterable<Title> titles = titleRepository.findAll();
		return titles;
	}

	@Override
	public List<Title> getByApiRef(String name) {
		return titleRepository.findByNewspaperApiRef(name);
	}

}
