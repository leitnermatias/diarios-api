package com.matiasleitner.diarios.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.matiasleitner.diarios.domain.NewspaperStrategy;

@Component
@EnableScheduling
public class TitleTasks {
	private static final Logger LOG = LoggerFactory.getLogger(TitleTasks.class);
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	@Autowired
	private List<NewspaperStrategy> newspapers;

	
	@Scheduled(fixedRate = 60, timeUnit = TimeUnit.SECONDS)
	public void getAllTitles() {
		LOG.info("Executing task " + "getAllTitles" + " :: Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
		
		for (NewspaperStrategy newspaper : newspapers) {
			newspaper.getTitles();
		}
		
	}
	
}
