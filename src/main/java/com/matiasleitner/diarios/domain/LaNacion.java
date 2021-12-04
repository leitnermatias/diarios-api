package com.matiasleitner.diarios.domain;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.matiasleitner.diarios.entity.Newspaper;
import com.matiasleitner.diarios.entity.Title;
import com.matiasleitner.diarios.repository.NewspaperRepository;
import com.matiasleitner.diarios.repository.TitleRepository;

@Component
public class LaNacion implements NewspaperStrategy {
	
	private static final Logger LOG = LoggerFactory.getLogger(LaNacion.class);
	
	@Autowired
	private NewspaperRepository newspaperRepository;
	
	@Autowired
	private TitleRepository titleRepository;
	
	@Override
	public List<Title> getTitles() {
		Newspaper laNacionConfig = newspaperRepository.findByName("La Nacion");
		LOG.info("Getting title from La Nacion with url: " + laNacionConfig.getTitlesUrl());
		
		List<Title> titles = new ArrayList<Title>();
		
		try {
			Document doc = Jsoup.connect(laNacionConfig.getTitlesUrl()).get();
			
			Elements titlesCards = doc.select("article.mod-article");
			
			for (Element title : titlesCards) {
				Element aTag = title.selectFirst("a.com-link");
				Element imgTag = title.selectFirst("img.com-image");
				
				String titleUrl = laNacionConfig.getBaseUrl() + aTag.attr("href");
				String titleHeader = aTag.text();
				String titleImgUrl = imgTag.attr("src");
				
				Title existingTitle = titleRepository.findByTitleUrl(titleUrl);
				
				if (existingTitle != null) {
					continue;
				}
				
				Title newTitle = new Title(titleHeader, titleUrl, titleImgUrl, laNacionConfig);
				
				titleRepository.save(newTitle);
				
				titles.add(newTitle);
				
				LOG.info("Saved new title from La Nacion to database");
			}
			
			return titles;
			
		} catch (Exception e) {
			LOG.error("Error while trying to obtain La Nacion titles ", e);
			return new ArrayList<Title>();
		}
		
	}

}
