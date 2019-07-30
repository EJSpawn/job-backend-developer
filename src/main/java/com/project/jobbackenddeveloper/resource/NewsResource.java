package com.project.jobbackenddeveloper.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.jobbackenddeveloper.model.News;
import com.project.jobbackenddeveloper.service.NewsService;
import com.project.jobbackenddeveloper.util.JobBackendDeveloperConstants;

@RestController
@RequestMapping(path = JobBackendDeveloperConstants.RESOURCE_INTELIPOST_V1_NEWS)
public class NewsResource {	  
	  NewsService newsService;
	  
	  @Autowired
	  public NewsResource(NewsService newsService) {
		this.newsService = newsService;
	  }
	  
	  @GetMapping("/{id}")
	  public News get(@PathVariable Long id) throws ResourceNotFoundException {
		  return newsService.findById(id);
	  }
	   
	  @GetMapping
	  public List<News> get() {
	    return newsService.findAll();
	  }
	  
	  @PostMapping
	  public News put(@RequestBody News aluno) {
	    return newsService.create(aluno);
	  }   
	  
	  @PutMapping
	  public News post(@RequestBody News news) throws ResourceNotFoundException {
		return newsService.update(news);
	  }
	 
	  @DeleteMapping("/{id}")
	  public void delete(@PathVariable Long id) {
		  newsService.deleteById(id);
	  }
}
