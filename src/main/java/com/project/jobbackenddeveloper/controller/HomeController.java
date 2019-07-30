package com.project.jobbackenddeveloper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.jobbackenddeveloper.service.NewsService;

@Controller
@RequestMapping(path = "/")
public class HomeController {	
	
	private NewsService newsService;
	
	@Autowired
	public HomeController(NewsService newsService) {
		this.newsService = newsService;
	}
	
	@GetMapping()
	public String index(Model model) {	
		model.addAttribute("newsList", newsService.findAll());
		return "/home.html";
	}
}
