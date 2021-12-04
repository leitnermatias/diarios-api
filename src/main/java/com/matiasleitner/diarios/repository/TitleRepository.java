package com.matiasleitner.diarios.repository;

import org.springframework.data.repository.CrudRepository;

import com.matiasleitner.diarios.entity.Title;

public interface TitleRepository extends CrudRepository<Title, Long> {
	public Title findByTitleUrl(String titleUrl);
}
