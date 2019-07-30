package com.project.jobbackenddeveloper.service;

import java.util.List;

import com.project.jobbackenddeveloper.model.News;

import javassist.NotFoundException;

public interface NewsService {
	public News create(News news);
	public News update(News news) throws NotFoundException;
	public News findById(Long id) throws NotFoundException;
	public List<News> findAll();
}
