package com.matiasleitner.diarios.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.matiasleitner.diarios.entity.Title;

public interface TitleRepository extends CrudRepository<Title, Long> {
	public Title findByTitleUrl(String titleUrl);
	public List<Title> findByNewspaperApiRef(String apiRef);
}
