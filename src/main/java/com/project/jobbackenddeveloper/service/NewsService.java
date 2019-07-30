package com.project.jobbackenddeveloper.service;

import java.util.List;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import com.project.jobbackenddeveloper.model.News;

public interface NewsService {
	public News create(News news);
	public News update(News news) throws ResourceNotFoundException;
	public News findById(Long id) throws ResourceNotFoundException;
	public void deleteById(Long id) throws ResourceNotFoundException;
	public List<News> findAll();
}
