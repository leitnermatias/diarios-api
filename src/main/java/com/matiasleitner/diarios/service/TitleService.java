package com.matiasleitner.diarios.service;


import org.springframework.stereotype.Service;

import com.matiasleitner.diarios.entity.Title;

@Service
public interface TitleService {
	public Iterable<Title> getTitles();
}
