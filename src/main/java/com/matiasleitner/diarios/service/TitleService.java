package com.matiasleitner.diarios.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.matiasleitner.diarios.entity.Title;

@Service
public interface TitleService {
	public Iterable<Title> getTitles();
	public List<Title> getByApiRef(String name);
}
