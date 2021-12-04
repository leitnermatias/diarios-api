package com.matiasleitner.diarios.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping(value = "/")
	public ResponseEntity<List<TitleDto>> index() {
		Iterable<Title> titles = titleService.getTitles();
		List<TitleDto> titlesDto = new ArrayList<TitleDto>();
		
		for (Title title : titles) {
			TitleDto dto = this.entityToDto(title);
			titlesDto.add(dto);
		}
		
		return new ResponseEntity<List<TitleDto>>(titlesDto, HttpStatus.OK);
	}
}
