package com.project.jobbackenddeveloper.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.project.jobbackenddeveloper.model.News;
import com.project.jobbackenddeveloper.repository.NewsRepository;
import com.project.jobbackenddeveloper.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService{
	private static final Logger LOGGER = LoggerFactory.getLogger(NewsServiceImpl.class);
	private NewsRepository  newsRepository;
	
	@Autowired
	public NewsServiceImpl(NewsRepository newsRepository) {
		this.newsRepository = newsRepository;
		//Mock de dados a serem exibidos para o usuário ao entrar na home
		for(int indexNews = 1; indexNews <= 10; indexNews++) {
			News news = new News(); 
			news.setNewsDate(LocalDate.now().plusDays(indexNews));
			news.setDescription("Artigo " + indexNews);
			newsRepository.save(news);
		}
	}
	
	@Override
	public News create(News news) {		
		return newsRepository.save(news);
	}

	@Override
	public News update(News news) throws ResourceNotFoundException {
		return newsRepository.findById(news.id)
			.map(n -> newsRepository.save(n))
			.orElseThrow(() -> new ResourceNotFoundException("Registro não encontrado"));
	}

	@Override
	public News findById(Long id) throws ResourceNotFoundException {
		return newsRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Registro não encontrado"));
	}

	@Override
	@Cacheable("news")// Recurso de cache, que será efetivado depois do primeiro acesso ao recurso
	public List<News> findAll() {
		LOGGER.debug("Find All");
		List<News> newsList = null;
		try {
			Thread.sleep(5000L);// <-- Simulando acesso demorado ao recurso de 5 seg
			newsList =  newsRepository.findAll();
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
		return newsList;
	}

	@Override
	public void deleteById(Long id) throws ResourceNotFoundException {
		newsRepository.deleteById(id);
	}
}
