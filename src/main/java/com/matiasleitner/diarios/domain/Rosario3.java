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
public class Rosario3 implements NewspaperStrategy {
	
	private static final Logger LOG = LoggerFactory.getLogger(Rosario3.class);
	
	@Autowired
	private NewspaperRepository newspaperRepository;
	
	@Autowired
	private TitleRepository titleRepository;
	
	@Override
	public List<Title> getTitles() {
		Newspaper rosario3Config = newspaperRepository.findByName("Rosario3");
		
		LOG.info("Getting titles from Rosario3 with url: " + rosario3Config.getTitlesUrl());
		
		List<Title> titles = new ArrayList<Title>();
		
		try {
			Document doc = Jsoup.connect(rosario3Config.getTitlesUrl()).get();
			Elements titlesCards = doc.select("article");
			
			for (Element title : titlesCards) {
				Element aTag = title.selectFirst("a.cover-link");
				Element h2Tag = title.selectFirst("h2.title");
				Element imgTag = title.selectFirst("figure").selectFirst("img");
				
				if (aTag == null || h2Tag == null || imgTag == null) {
					String infoString = "Tags: " + aTag + " " + h2Tag + " " + imgTag;
					
					LOG.warn("Skipping title because we couldn't find certain elements | " + infoString);
					
					continue;
				}
				
				String titleUrl = rosario3Config.getBaseUrl() + aTag.attr("href");
				String titleHeader = h2Tag.text();
				String titleImgUrl = rosario3Config.getBaseUrl() + imgTag.attr("src");
				
				Title existingTitle = titleRepository.findByTitleUrl(titleUrl);
				
				if (existingTitle != null) {
					continue;
				}
				
				Title newTitle = new Title(titleHeader, titleUrl, titleImgUrl, rosario3Config);
				
				titleRepository.save(newTitle);
				
				titles.add(newTitle);
				
				LOG.info("Saved new title from Rosario3 to database");
				
			}
			
			return titles;
			
		} catch (Exception e) {
			LOG.error("Error while trying to obtain Rosario3 titles ", e);
			return new ArrayList<Title>();
		}
		
	}

}
