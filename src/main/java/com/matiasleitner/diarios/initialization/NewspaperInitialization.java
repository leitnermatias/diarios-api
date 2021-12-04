package com.matiasleitner.diarios.initialization;

import javax.annotation.PostConstruct;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.matiasleitner.diarios.entity.Newspaper;
import com.matiasleitner.diarios.repository.NewspaperRepository;

@Component
public class NewspaperInitialization {
	
	private static final Logger LOG = Logger.getLogger(NewspaperInitialization.class);
	
	@Autowired
	private NewspaperRepository newspaperRepository;
	
	@PostConstruct
	public void init() {
		LOG.info("Starting NewspaperInitialization");
		createNewspaper("Clarin", "https://www.clarin.com", "https://www.clarin.com/ultimo-momento/");
		createNewspaper("La Nacion", "https://www.lanacion.com.ar", "https://www.lanacion.com.ar/ultimas-noticias/");
		createNewspaper("Rosario3", "https://www.rosario3.com/", "https://www.rosario3.com/seccion/ultimas-noticias/");
	}
	
	private void createNewspaper(String name, String baseUrl, String titlesUrl) {
		LOG.info("Adding newspaper " + name + " to database");
		Newspaper newspaper = new Newspaper(name, baseUrl, titlesUrl);
		newspaperRepository.save(newspaper);
		LOG.info("Successfully added newspaper " + name + " to the database");
	}
}
