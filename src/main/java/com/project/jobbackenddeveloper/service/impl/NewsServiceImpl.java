package com.project.jobbackenddeveloper.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.jobbackenddeveloper.model.News;
import com.project.jobbackenddeveloper.repository.NewsRepository;
import com.project.jobbackenddeveloper.service.NewsService;

import javassist.NotFoundException;

@Service
public class NewsServiceImpl implements NewsService{
	private NewsRepository  newsRepository;
	
	@Autowired
	public NewsServiceImpl(NewsRepository newsRepository) {
		this.newsRepository = newsRepository;
	}
	
	@Override
	public News create(News news) {		
		return newsRepository.save(news);
	}

	@Override
	public News update(News news) throws NotFoundException {
		return newsRepository.findById(news.id)
			.map(n -> newsRepository.save(n))
			.orElseThrow(() -> new NotFoundException("Registro não encontrado"));
	}

	@Override
	public News findById(Long id) throws NotFoundException {
		return newsRepository.findById(id)
			.orElseThrow(() -> new NotFoundException("Registro não encontrado"));
	}

	@Override
	public List<News> findAll() {
		return newsRepository.findAll();
	}
}
