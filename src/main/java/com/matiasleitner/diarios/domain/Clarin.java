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
public class Clarin implements NewspaperStrategy {
	
	private static final Logger LOG = LoggerFactory.getLogger(Clarin.class);
	
	@Autowired
	private NewspaperRepository newspaperRepository;
	
	@Autowired
	private TitleRepository titleRepository;
	
	@Override
	public List<Title> getTitles() {
		
		Newspaper clarinConfig = newspaperRepository.findByName("Clarin");
		LOG.info("Getting titles from Clarin with url: " + clarinConfig.getTitlesUrl());
		
		List<Title> titles = new ArrayList<Title>();
		
		try {
			Document doc = Jsoup.connect(clarinConfig.getTitlesUrl()).get();
			Elements titlesCards = doc.select("li.list-format.list");
			
			for (Element title : titlesCards) {
				Element aTag = title.selectFirst("a.link-new");
				Element h2Tag = title.selectFirst("div.mt").selectFirst("h2");
				Element imgTag = title.selectFirst("div.wrap-figure").selectFirst("img");
				
				if (aTag == null || h2Tag == null || imgTag == null) {
					
					String infoString = "Tags: " + aTag + " " + h2Tag + " " + imgTag;
							
					LOG.warn("Skipping title because we couldn't find certain elements | " + infoString);
					
					continue;
				}
				
				
				String titleUrl = aTag.attr("href");
				String titleHeader = h2Tag.text();
				String titleImgUrl = imgTag.attr("data-big");
				
				
				Title existingTitle = titleRepository.findByTitleUrl(titleUrl);
				
				if (existingTitle != null) {
					continue;
				}
				
				Title newTitle = new Title(titleHeader, titleUrl, titleImgUrl, clarinConfig);
				
				titleRepository.save(newTitle);
				
				titles.add(newTitle);
				
				LOG.info("Saved new title from Clarin to database");
				
			}
			
			return titles;
			
		} catch (Exception e) {
			LOG.error("Error while trying to obtain Clarin titles ", e);
			return new ArrayList<Title>();
		}
	}

}
