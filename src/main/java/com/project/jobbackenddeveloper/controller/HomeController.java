package com.project.jobbackenddeveloper.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.jobbackenddeveloper.model.News;
import com.project.jobbackenddeveloper.service.NewsService;

@Controller
@RequestMapping(path = "/")
public class HomeController {	
	
	private NewsService newsService;
	
	@Autowired
	public HomeController(NewsService newsService) {
		this.newsService = newsService;
		for(int indexNews = 1; indexNews <= 10; indexNews++) {
			News news = new News(); 
			news.setNewsDate(LocalDate.now().plusDays(indexNews));
			news.setDescription("Artigo " + indexNews);
			newsService.create(news);
		}
	}
	
	@GetMapping()
	public String index(Model model) {	
		model.addAttribute("newsList", newsService.findAll());
		return "/home.html";
	}
}
