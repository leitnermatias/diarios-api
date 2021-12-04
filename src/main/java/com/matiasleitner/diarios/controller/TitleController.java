package com.matiasleitner.diarios.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matiasleitner.diarios.dto.TitleDto;
import com.matiasleitner.diarios.entity.Title;
import com.matiasleitner.diarios.service.TitleService;

@RestController
@RequestMapping("titles")
public class TitleController {
	
	@Autowired
	private TitleService titleService;
	
	protected TitleDto entityToDto(Title title) {
		TitleDto dto = new TitleDto();
		BeanUtils.copyProperties(title, dto);
		return dto;
	}
	
	protected Title dtoToEntity(TitleDto dto) {
		Title title = new Title();
		BeanUtils.copyProperties(dto, title);
		return title;
	}
	
	protected List<TitleDto> entityToDto(List<Title> titles) {
		
		List<TitleDto> dtos = new ArrayList<TitleDto>();
		
		for (Title title : titles) {
			TitleDto dto = entityToDto(title);
			dtos.add(dto);
		}
		
		return dtos;
	}
	
	protected List<Title> dtoToEntity(List<TitleDto> dtos) {
		List<Title> titles = new ArrayList<Title>();
		
		for (TitleDto dto : dtos) {
			Title title = dtoToEntity(dto);
			titles.add(title);
		}
		
		return titles;
	}
	
	@GetMapping
	public ResponseEntity<List<TitleDto>> index() {
		Iterable<Title> titles = titleService.getTitles();
		List<TitleDto> titlesDto = new ArrayList<TitleDto>();
		
		for (Title title : titles) {
			TitleDto dto = this.entityToDto(title);
			titlesDto.add(dto);
		}
		
		return new ResponseEntity<List<TitleDto>>(titlesDto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{apiRef}")
	public ResponseEntity<List<TitleDto>> getByName(@PathVariable String apiRef) {
		List<Title> titles = titleService.getByApiRef(apiRef);
		
		List<TitleDto> dtos = entityToDto(titles);
		
		return new ResponseEntity<List<TitleDto>>(dtos, HttpStatus.OK);
		
		
	}
	
}
