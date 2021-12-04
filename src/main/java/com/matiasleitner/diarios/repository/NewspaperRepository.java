package com.matiasleitner.diarios.repository;

import org.springframework.data.repository.CrudRepository;

import com.matiasleitner.diarios.entity.Newspaper;

public interface NewspaperRepository extends CrudRepository<Newspaper, Long> {
	public Newspaper findByName(String name);
}
